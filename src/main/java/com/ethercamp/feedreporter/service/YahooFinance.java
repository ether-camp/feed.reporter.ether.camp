package com.ethercamp.feedreporter.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Anton on 16.08.2015.
 */
@Service
public class YahooFinance implements DataFeed {
    private static final DateFormat TIME = new SimpleDateFormat("dd/MM/yyyy h:mma");

    @Override
    public List<MarketData> getLastData(Collection<MarketAsset> assets) {
        List<MarketData> ret = new ArrayList<>();
        String s = assets.stream().map((a) -> a.getTickerSymbol()).collect(Collectors.joining(","));
        try {
            URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=" + URLEncoder.encode(s) + "&f=sl1d1t1&e=.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            while(true) {
                String rs = br.readLine();
                if (rs == null) break;

                StringTokenizer st = new StringTokenizer(rs, ",");
                MarketAsset asset = MarketAsset.getBySymbol(trim(st.nextToken()));
                double price = Double.parseDouble(trim(st.nextToken()));
                Date t = TIME.parse(trim(st.nextToken()) + " " + trim(st.nextToken()));
                ret.add(new MarketData(asset, t, price));
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String trim(String s) {
        s = s.trim();
        if (s.startsWith("\"")) s = s.substring(1);
        if (s.endsWith("\"")) s = s.substring(0, s.length() - 1);
        return s;
    }

    public static void main(String[] args) throws Exception {
        List<MarketAsset> s = new ArrayList<>();
        s.add(MarketAsset.getBySymbol("GLD"));
        YahooFinance yf = new YahooFinance();
        List<MarketData> lastData = yf.getLastData(s);
        System.out.println(lastData);
    }
}
