package com.ethercamp.feedreporter.scheduler;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.stereotype.Component;

public class FeedConfig {
    public final Config CONFIG;

    public FeedConfig() {
        Config defaultConf = ConfigFactory.load("pricefeed.conf");
        Config userConf = ConfigFactory.load("pricefeed.user.conf");
        CONFIG = userConf.withFallback(defaultConf);
    }
}
