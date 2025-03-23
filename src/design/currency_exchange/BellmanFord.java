package design.currency_exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord {

    // Find the best exchange rate from startCurrency to endCurrency using Bellman-Ford
    public static double findBestExchangeRate(String startCurrency, String endCurrency, Map<String, List<Edge>> graph) {

        // Map to store the min distance (in terms of negative logs) from startCurrency to endCurrency
        Map<String, Double> minLogRates = new HashMap<>();

        // Initialize all distances to infinity, except for the start currency
        for (String currency : graph.keySet()) minLogRates.put(currency, Double.POSITIVE_INFINITY);
        minLogRates.put(startCurrency, 0.0);

        int numCurrencies = graph.size();;

        // Bellman-Ford algorithm: relax edges upto (V-1) times
        for (int i = 0; i < numCurrencies; i++) {
            boolean updated = false;

            for (String currency : graph.keySet()) {
                double currentLogRate = minLogRates.get(currency);

                // If this currency is reachable (has a valid log rate)
                if (currentLogRate != Double.POSITIVE_INFINITY) {
                    for (Edge neighbour : graph.get(currency)) {
                        // Calculate the new log rate (negative log of the exchange rate)
                        double newLogRate = currentLogRate - Math.log(neighbour.rate);

                        // If a smaller distance is found, update it
                        if (newLogRate < minLogRates.get(neighbour.toCurrency)) {
                            minLogRates.put(neighbour.toCurrency, newLogRate);
                            updated = true;
                        }
                    }
                }
            }

            // If no update were made in this iteration, we can stop early
            if (!updated) break;
        }


        // After V-1 relaxations, check for negative cycles (arbitrage opportunities)
        for (String currency : graph.keySet()) {
            double currentLogRate = minLogRates.get(currency);

            if (currentLogRate != Double.POSITIVE_INFINITY) {
                for (Edge neighbour : graph.get(currency)) {
                    double newLogRate = currentLogRate - Math.log(neighbour.rate);

                    // If we can still relax an edge, there is a negative cycle
                    if (newLogRate < minLogRates.get(neighbour.toCurrency)) {
                        throw new IllegalStateException("Negative cycle detected: Arbitrage opportunities exist!");
                    }
                }
            }
        }

        // If no valid path is found , return -1
        return minLogRates.get(endCurrency) == Double.POSITIVE_INFINITY ? -1.0 : Math.exp(-minLogRates.get(endCurrency));

    }
}
