package com.ethercamp.feedreporter.scheduler;


import com.ethercamp.feedreporter.service.MarketAsset;
import com.ethercamp.feedreporter.service.PoloniexService;
import com.ethercamp.feedreporter.service.YahooFinance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;


public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger("general");

    @Autowired
    PoloniexService poloniexService;

    @Autowired
    YahooFinance yahoo;


    @Scheduled(cron = "0/5 * * * * ?")
    public void doSome(){
        System.out.println(poloniexService.getLastData(Arrays.asList(MarketAsset.USD_ETH)));
        System.out.println(yahoo.getLastData(Arrays.asList(MarketAsset.GLD)));


    }

}