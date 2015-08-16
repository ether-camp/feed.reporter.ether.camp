package com.ethercamp.feedreporter.model;

public class PoloniexInstrument {

    /*
    "USDT_ETH": {
        "last": "1.12000001",
        "lowestAsk": "1.76999990",
        "highestBid": "1.06000000",
        "percentChange": "-0.25333332",
        "baseVolume": "248.54148468",
        "quoteVolume": "214.53958550",
        "isFrozen": "0",
        "high24hr": "1.77000000",
        "low24hr": "1.11000000"
    }
     */

    String last;
    String lowestAsk;
    String highestBid;
    String percentChange;
    String baseVolume;
    String quoteVolume;
    String isFrozen;
    String high24hr;
    String low24hr;

    public PoloniexInstrument() {
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(String high24hr) {
        this.high24hr = high24hr;
    }

    public String getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(String low24hr) {
        this.low24hr = low24hr;
    }
}
