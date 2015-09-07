package com.ethercamp.feedreporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {

        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");
        System.out.println("  GOOD GOOD GOOD  ");



        SpringApplication.run(Application.class, args);
    }

}
