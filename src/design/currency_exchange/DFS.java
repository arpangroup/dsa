package design.currency_exchange;

import java.util.*;

public class DFS {
    public static double findBestExchangeRate(String startCurrency, String endCurrency, Map<String, List<Edge>> graph) {
        Set<String> visited = new HashSet<>();
        double bestRate = dfs(startCurrency, endCurrency, graph, visited, 1.0);
        return bestRate == -1 ? -1 : bestRate;
    }

    //
    private static double dfs(String current, String endCurrency, Map<String, List<Edge>> graph, Set<String> visited, double currentProduct) {
        // If we've reached the target currency, return the current product
        if (current.equals(endCurrency)) {
            return currentProduct;
        }

        // Mark the current currency as visited
        visited.add(current);

        double maxProduct = -1; // store the best product found so far

        // Explore all neighbour currencies
        for(Edge edge : graph.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(edge.toCurrency)) {
                // Calculate the new product including the current exchange rate
                double newProduct = currentProduct * edge.rate;

                // Recursively call the DFS for the next currency
                double result = dfs(edge.toCurrency, endCurrency, graph, visited, newProduct);

                // Track the maximum product obtained
                if (result > maxProduct) {
                    maxProduct = result;
                }
            }
        }

        // Backtrack: unmark the current currency before returning
        visited.remove(current);

        return maxProduct;
    }
}
