package com.ethercamp.feedreporter.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton on 16.08.2015.
 */
public class MarketAsset {
    private static Map<String, MarketAsset> map = new HashMap<>();

    public static final MarketAsset USD_ETH = getBySymbol("USDT_ETH");
    public static final MarketAsset GLD = getBySymbol("GLD");

    private String tickerSymbol;

    private MarketAsset(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public static MarketAsset getBySymbol(String sym) {
        return map.computeIfAbsent(sym, (s -> new MarketAsset(s)));
    }

    @Override
    public String toString() {
        return getTickerSymbol();
    }

    public static void main(String[] args) {
        System.out.println(MarketAsset.USD_ETH);
    }
}
