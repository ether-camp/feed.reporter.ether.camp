import com.ethercamp.feedreporter.model.PoloniexInstrument;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

public class Main {

    public static void main(String[] args) {


        for (int i = 0; i < 100; ++i)
            printTicker();



    }

    public static void printTicker(){
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
        PoloniexInstrument p = output.get("USDT_ETH");
        System.out.println(p.getLast());


    }
}
