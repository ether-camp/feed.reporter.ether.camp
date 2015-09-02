package com.ethercamp.feedreporter.ethereum;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;


public class EthereumBean {

    Ethereum ethereum;
    EthereumListener ethereumListener;

    public void start(){
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
