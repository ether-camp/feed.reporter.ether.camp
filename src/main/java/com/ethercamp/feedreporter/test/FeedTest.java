package com.ethercamp.feedreporter.test;

import com.ethercamp.feedreporter.service.MarketAsset;
import com.ethercamp.feedreporter.service.MarketData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anton Nashatyrev on 03.09.2015.
 */
public class FeedTest {
    public static void main(String[] args) throws Exception {
        List<MarketAsset> publishAssetList;
        publishAssetList = MarketAsset.createAllList();

        System.out.println("Publishing: " + publishAssetList);

        Map<MarketAsset.Exchange, List<MarketAsset>> publishAssets = new HashMap<>();
        for (MarketAsset asset : publishAssetList) {
            publishAssets.computeIfAbsent(asset.getExchange(),e -> new ArrayList<>()).add(asset);
        }
        List<MarketData> lastData = new ArrayList<>();
        for (Map.Entry<MarketAsset.Exchange, List<MarketAsset>> entry : publishAssets.entrySet()) {
            System.out.println("Receiving data from " + entry.getKey() + ": " + entry.getValue());
            lastData.addAll(entry.getKey().getFeed().getLastData(entry.getValue()));
        }
        System.out.println("Received data: " + lastData);
    }
}
