package graph.shortest_path;
import java.util.*;

public class CheapestFlightWithKStops {
    public static int cheapestFLight(int[][] flights, int src, int dst, int k) {
        if (src == dst) return 0;

        List<List<Pair>> graph = buildGraph(flights);
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.price));
        int[] dist = new int[flights.length];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        pq.offer(new Tuple(src, 0, 0));

        while (!pq.isEmpty()) {
            Tuple tuple = pq.poll();
            int currentCity = tuple.city;
            int currentPrice = tuple.price;
            int currentStops = tuple.stops;


            if (currentCity == dst) return currentPrice; // If the destination is reached, return the price
            if (currentStops > k) continue;

            for (Pair nei : graph.get(currentCity)) {
                int newCity = nei.city;
                int price = nei.price;

                int newPrice = currentPrice + price;
                if (newPrice < dist[newCity]) {
                    dist[newCity] = newPrice;
                    pq.offer(new Tuple(newCity, newPrice, currentStops + 1));
                }

            }
        }
        if (dist[dst] == Integer.MAX_VALUE) return -1;
        return dist[dst];
    }

    private static List<List<Pair>> buildGraph(int[][] flights) {
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i=0; i< flights.length; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] it : flights) {
            int fromCity = it[0];
            int toCity = it[1];
            int price = it[2];
            adjList.get(fromCity).add(new Pair(toCity, price));
        }
        return adjList;
    }

    public static void main(String[] args) {
        int[][] flights = new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };
        int price = cheapestFLight(flights, 0, 3, 1);
        System.out.println("PRICE: " + price);
    }


    static class Pair {
        int city;
        int price;

        public Pair(int city, int price) {
            this.city = city;
            this.price = price;
        }
    }
    static class Tuple {
        int city;
        int price;
        int stops;

        public Tuple(int city, int price, int stops) {
            this.city = city;
            this.price = price;
            this.stops = stops;
        }
    }
}
