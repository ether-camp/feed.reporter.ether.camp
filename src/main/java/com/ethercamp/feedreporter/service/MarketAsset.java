package com.ethercamp.feedreporter.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 16.08.2015.
 */
public class MarketAsset {
    public static class Exchange {
        public static final Exchange YAHOO = new Exchange("Yahoo", new YahooFinance());
        public static final Exchange POLONIEX = new Exchange("Poloniex", new PoloniexService());

        String name;
        DataFeed feed;

        public Exchange(String name, DataFeed feed) {
            this.name = name;
            this.feed = feed;
        }

        public DataFeed getFeed() {
            return feed;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static Map<String, MarketAsset> symbolMap = new HashMap<>();
    private static Map<String, MarketAsset> exchSymbolMap = new HashMap<>();

    public static final MarketAsset USD_ETH = createBySymbol("USDT_ETH", Exchange.POLONIEX);
    public static final MarketAsset USD_BTC = createBySymbol("USDT_BTC", Exchange.POLONIEX);
    public static final MarketAsset BTC_ETH = createBySymbol("BTC_ETH", Exchange.POLONIEX);

    public static final MarketAsset EURUSD = createBySymbol("EURUSD", "EURUSD=X", Exchange.YAHOO);
    public static final MarketAsset GBPUSD = createBySymbol("GBPUSD", "GBPUSD=X", Exchange.YAHOO);
    public static final MarketAsset USDJPY = createBySymbol("USDJPY", "USDJPY=X", Exchange.YAHOO);
    public static final MarketAsset XAUUSD = createBySymbol("XAUUSD", "XAUUSD=X", Exchange.YAHOO); // Gold
    public static final MarketAsset XAGUSD = createBySymbol("XAGUSD", "XAGUSD=X", Exchange.YAHOO); // Silver

    public static final MarketAsset SP500 = createBySymbol("SP500", "^GSPC", Exchange.YAHOO);
//    public static final MarketAsset DOWJONES = createBySymbol("DOWJONES", "^DJI", Exchange.YAHOO); // N/A received from Yahoo
    public static final MarketAsset NASDAQ = createBySymbol("NASDAQ", "^IXIC", Exchange.YAHOO);

//    public static final MarketAsset GLD = createBySymbol("GLD", Exchange.YAHOO);
    public static final MarketAsset AAPL = createBySymbol("AAPL", Exchange.YAHOO); // Apple
    public static final MarketAsset GOOG = createBySymbol("GOOG", Exchange.YAHOO); // Google
    public static final MarketAsset MSFT = createBySymbol("MSFT", Exchange.YAHOO); // Microsoft
    public static final MarketAsset GM = createBySymbol("GM", Exchange.YAHOO);   // General Motors
    public static final MarketAsset GE = createBySymbol("GE", Exchange.YAHOO);   // General Electric
    public static final MarketAsset WMT = createBySymbol("WMT", Exchange.YAHOO); // WalMart
    public static final MarketAsset F = createBySymbol("F", Exchange.YAHOO); // Ford Motor
    public static final MarketAsset T = createBySymbol("T", Exchange.YAHOO); // AT&T


    public static List<MarketAsset> createAllList() {
        List<MarketAsset> ret = new ArrayList<MarketAsset>();
        Field[] fields = MarketAsset.class.getFields();
        try {
            for (Field f: fields) {
                if (MarketAsset.class.isAssignableFrom(f.getType())) {
                    Object val = f.get(null);
                    if (val instanceof MarketAsset) {
                        ret.add((MarketAsset) val);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private String symbol;
    private String exchangeSymbol;
    private Exchange exchange;

    private MarketAsset(String symbol, Exchange exch) {
        this(symbol, symbol, exch);
    }

    public MarketAsset(String symbol, String exchangeSymbol, Exchange exch) {
        this.symbol = symbol;
        this.exchangeSymbol = exchangeSymbol;
        this.exchange = exch;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getExchangeSymbol() {
        return exchangeSymbol;
    }

    public Exchange getExchange() {
        return exchange;
    }

    private static MarketAsset createBySymbol(String sym, String exchSym, Exchange exch) {
        MarketAsset asset = new MarketAsset(sym, exchSym, exch);
        symbolMap.put(sym, asset);
        exchSymbolMap.put(exchSym, asset);
        return asset;
    }

    private static MarketAsset createBySymbol(String sym, Exchange exch) {
        return createBySymbol(sym, sym, exch);
    }

    public static MarketAsset getBySymbol(String sym) {
        return symbolMap.get(sym);
    }

    public static MarketAsset getByExchangeSymbol(String sym) {
        return exchSymbolMap.get(sym);
    }

    @Override
    public String toString() {
        return getSymbol();
    }

}
