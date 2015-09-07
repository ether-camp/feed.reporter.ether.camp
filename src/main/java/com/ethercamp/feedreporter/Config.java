package com.ethercamp.feedreporter;

import com.ethercamp.feedreporter.bean.BeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public BeanOne beanOne() {

        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");

        return new BeanOne();
    }


}
