package com.ethercamp.feedreporter.ethereum;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class EthereumBean {

    private static final Logger log = LoggerFactory.getLogger("general");


    Ethereum ethereum;
    EthereumListener ethereumListener;

    public void start(){

        log.info(" *                   * ");
        log.info(" * Starting ethereum * ");
        log.info(" *                   * ");


        this.ethereum = EthereumFactory.createEthereum();
        ethereumListener = new EthereumListener(ethereum);
        this.ethereum.addListener(ethereumListener);
    }


    public String getBestBlock(){
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }

    public Ethereum getEthereum() {
        return ethereum;
    }

    public EthereumListener getEthereumListener() {
        return ethereumListener;
    }
}
