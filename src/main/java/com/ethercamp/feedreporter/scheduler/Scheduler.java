package com.ethercamp.feedreporter.scheduler;


import com.ethercamp.feedreporter.service.PoloniexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;



public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger("general");

    @Autowired
    PoloniexService poloniexService;


    @Scheduled(cron = "0/5 * * * * ?")
    public void doSome(){

        poloniexService.getUSDETHLastPrice();


    }

}