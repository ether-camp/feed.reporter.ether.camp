package com.ethercamp.feedreporter.service;

import java.util.Date;

/**
 * Created by Anton on 16.08.2015.
 */
public class MarketData {
    Date time;
    double price;
    MarketAsset asset;

    public MarketData(MarketAsset asset, Date time, double price) {
        this.time = time;
        this.price = price;
        this.asset = asset;
    }

    public Date getTime() {
        return time;
    }

    public double getLastPrice() {
        return price;
    }

    public MarketAsset getAsset() {
        return asset;
    }

    @Override
    public String toString() {
        return asset + " [" + price + " " + time + "]";
    }
}
