package com.ethercamp.feedreporter.service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Anton on 16.08.2015.
 */
public interface DataFeed {
    List<MarketData> getLastData(Collection<MarketAsset> assets);
}
