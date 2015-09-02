package com.ethercamp.feedreporter.scheduler;


import com.ethercamp.feedreporter.ethereum.EthereumBean;
import com.ethercamp.feedreporter.service.MarketAsset;
import com.ethercamp.feedreporter.service.MarketData;
import com.ethercamp.feedreporter.service.PoloniexService;
import com.ethercamp.feedreporter.service.YahooFinance;
import org.ethereum.core.CallTransaction;
import org.ethereum.core.Transaction;
import org.ethereum.crypto.ECKey;
import org.ethereum.facade.Ethereum;
import org.ethereum.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.spongycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger("general");

    @Autowired
    PoloniexService poloniexService;

    @Autowired
    YahooFinance yahoo;

    @Autowired
    EthereumBean ethereumBean;

    @Autowired
    FeedConfig feedConfig;

    ECKey userKey;
    private String feedAccount;

    Map<MarketAsset.Exchange, List<MarketAsset>> publishAssets = new HashMap<>();

    @PostConstruct
    void init() {
        String pKey = feedConfig.CONFIG.getString("user.account.privateKey");
        userKey = ECKey.fromPrivate(Hex.decode(pKey));
        feedAccount = feedConfig.CONFIG.getString("feed.account");

        List<MarketAsset> publishAssetList;
        if (feedConfig.CONFIG.hasPath("feed.symbols")) {
            publishAssetList = MarketAsset.createAllList();
        } else {
            publishAssetList = feedConfig.CONFIG.getStringList("feed.symbols").stream()
                    .map(s -> MarketAsset.getBySymbol(s))
                    .collect(Collectors.toList());
        }

        log.info("Publishing: " + publishAssetList);

        for (MarketAsset asset : publishAssetList) {
            publishAssets.getOrDefault(asset.getExchange(), new ArrayList<>()).add(asset);
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void doSome(){
        if (ethereumBean.getEthereumListener() != null && ethereumBean.getEthereumListener().isSyncDone()) {
            List<MarketData> lastData = new ArrayList<>();
            for (Map.Entry<MarketAsset.Exchange, List<MarketAsset>> entry : publishAssets.entrySet()) {
                log.debug("Receiving data from " + entry.getKey() + ": " + entry.getValue());
                lastData.addAll(entry.getKey().getFeed().getLastData(entry.getValue()));
            }
            log.info("======= Received market data: " + lastData);
            //        System.out.println();

            Ethereum ethereum = ethereumBean.getEthereum();

            CallTransaction.Function function = CallTransaction.Function.fromSignature("update",
                    "bytes32[]", "uint[]", "uint[]");

            byte[] accountAddr = userKey.getAddress();

            BigInteger nonce = ethereum.getRepository().getNonce(accountAddr);
            log.info("======= Nonce: " + nonce);
            long t = System.currentTimeMillis();
            String[] symbols = new String[lastData.size()];
            int[] prices = new int[lastData.size()];
            long[] timestamps = new long[lastData.size()];

            for (int i = 0; i < lastData.size(); i++) {
                symbols[i] = lastData.get(i).getAsset().getSymbol();
                prices[i] = (int) (lastData.get(i).getLastPrice() * 1_000_000);
                timestamps[i] = Utils.toUnixTime(lastData.get(i).getTime().getTime());
            }

            Transaction tx = CallTransaction.createCallTransaction(nonce.longValue(),
                    70_000_000_000L, 1_000_000, feedAccount, 1,
                    function, symbols, prices, timestamps);

            tx.sign(userKey.getPrivKeyBytes());

            log.info("======= Sending: " + tx);
            Future<Transaction> future = ethereum.submitTransaction(tx);
        } else {
            log.info("======= Sync is still in progress...");
        }
    }
}