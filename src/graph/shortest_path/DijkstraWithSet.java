package graph.shortest_path;

import java.util.*;

public class DijkstraWithSet {
    static class Pair {
        public int node;
        public int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static int[] shortestDistances(List<List<List<Integer>>> graph, int src) {
        TreeSet<Pair> set = new TreeSet<>();
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        set.add(new Pair(src, 0));

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
                    set.remove(new Pair(adjNode, dist[adjNode]));

                    // Update the shortest distance
                    dist[adjNode] = newDistance;
                    set.add(new Pair(adjNode, newDistance));
                }
            }
        }
        return dist;
    }


}
