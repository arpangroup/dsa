package graph.shortest_path;
import graph.Pair;

import java.util.*;

public class DijkstraWithSet {
    public static int[] shortestDistances(List<List<List<Integer>>> graph, int src) {
        TreeSet<Pair> set = new TreeSet<>();
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        set.add(new Pair(0, src));

        while (!set.isEmpty()) {
            Pair current = set.pollFirst(); // Extract the node with the smallest distance
            int distance = current.distance;
            int node = current.node;

            for (List<Integer> nei : graph.get(node)) {
                int adjNode = nei.get(0);
                int edgeWeight = nei.get(1);

                int newDistance = distance + edgeWeight;
                if (newDistance < dist[adjNode]) {
                    // Remove the old entry if it exists
                    set.remove(new Pair(dist[adjNode], adjNode));

                    // Update the shortest distance
                    dist[adjNode] = newDistance;
                    set.add(new Pair(newDistance, adjNode));
                }
            }
        }
        return dist;
    }
}
