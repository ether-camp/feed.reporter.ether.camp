package com.ethercamp.feedreporter;

import com.ethercamp.feedreporter.bean.BeanOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


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
