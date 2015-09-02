package com.ethercamp.feedreporter;

import com.ethercamp.feedreporter.ethereum.EthereumBean;
import com.ethercamp.feedreporter.scheduler.Scheduler;
import com.ethercamp.feedreporter.service.PoloniexService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public Scheduler scheduler(){
        return new Scheduler();
    }

    @Bean
    public PoloniexService poloniexService(){
        return new PoloniexService();
    }

    @Bean
    EthereumBean ethereumBean() throws Exception {
        EthereumBean ethereumBean = new EthereumBean();
        Executors.newSingleThreadExecutor().
                submit(ethereumBean::start);

        return ethereumBean;
    }

}
