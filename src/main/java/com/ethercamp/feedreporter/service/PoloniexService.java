package com.ethercamp.feedreporter.service;

import com.ethercamp.feedreporter.model.PoloniexInstrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.HttpMethod.GET;

@Service
public class PoloniexService implements DataFeed {

    private static final Logger log = LoggerFactory.getLogger("general");

    @Override
    public List<MarketData> getLastData(Collection<MarketAsset> assets) {
        List<MarketData> ret = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String rpcEnd = "https://poloniex.com/public?command=returnTicker";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Map<String, PoloniexInstrument>> response = null;
        try {
            response = restTemplate.exchange(rpcEnd, GET, entity,
                    new ParameterizedTypeReference<Map<String, PoloniexInstrument>>(){});
        } catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode().value() >= 400) {

            }

            throw ex;
        }

        Map<String, PoloniexInstrument> output = response.getBody();

        for (MarketAsset asset : assets) {
            PoloniexInstrument pi = output.get(asset.getTickerSymbol());
            ret.add(new MarketData(asset, new Date(), Double.parseDouble(pi.getLast())));
        }

        return ret;
    }
}
