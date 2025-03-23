package design.currency_exchange;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, List<Edge>> currencyGraph = new HashMap<>();

        // Bitcoin to USD exchange rate is 0.5
        currencyGraph.put("Bitcoin", Arrays.asList(new Edge("USD", 0.5)));

        // USD to JPY exchange rate is 100
        currencyGraph.put("USD", Arrays.asList(new Edge("JPY", 100.0)));

        // JPY to Bitcoin exchange rate is 0.01
        currencyGraph.put("JPY", Arrays.asList(new Edge("Bitcoin", 0.01)));

        try {
            double bestRate = BellmanFord.findBestExchangeRate("Bitcoin", "JPY", currencyGraph);
            System.out.println("Best exchange rate from Bitcoin to JPY: " + bestRate);
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
