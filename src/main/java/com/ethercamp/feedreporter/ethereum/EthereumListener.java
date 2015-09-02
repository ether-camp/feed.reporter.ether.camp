package com.ethercamp.feedreporter.ethereum;

import com.ethercamp.feedreporter.scheduler.Scheduler;
import org.ethereum.crypto.HashUtil;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;
import org.ethereum.net.rlpx.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class EthereumListener extends EthereumListenerAdapter {

    Ethereum ethereum;
    boolean syncDone = false;

    public EthereumListener(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    @Override
    public void onSyncDone() {
        System.out.println("========== Sync is Done!");
        syncDone = true;
    }

    public boolean isSyncDone() {
        return syncDone;
    }
}
