package graph.shortest_path;

import graph.Pair;

import java.util.*;

public class DijkstraWithPriorityQueue {
    public static int[] shortestDistances(List<List<List<Integer>>> graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0; // from src -> src distance is always 0.
        pq.add(new Pair(0, src));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int distance = current.distance;
            int node = current.node;

            for (List<Integer> nei : graph.get(node)) {
                int adjNode = nei.get(0);
                int edgeWeight = nei.get(1);

                int newDistance = distance + edgeWeight;
                if (newDistance < dist[adjNode]) {
                    dist[adjNode] = newDistance;
                    pq.add(new Pair(newDistance, adjNode));
                }
            }
        }
        return dist;
    }

    public static List<Integer> shortestPath(List<List<List<Integer>>> graph, int src, int dst) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);

        dist[src] = 0; // from src -> src distance is always 0.
        pq.add(new Pair(0, src));
        parent[src] = src; // <---------------- parent

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int distance = current.distance;
            int node = current.node;

            for (List<Integer> nei : graph.get(node)) {
                int adjNode = nei.get(0);
                int edgeWeight = nei.get(1);

                int newDistance = distance + edgeWeight;
                if (newDistance < dist[adjNode]) {
                    dist[adjNode] = newDistance;
                    pq.add(new Pair(newDistance, adjNode));
                    parent[adjNode] = node; // <---------------- parent
                }
            }
        }
        return getShortestPath(parent, src, dst, new ArrayList<>());
    }

    public static List<Integer> getShortestPath(int[] parent, int src, int dst, List<Integer> path) {
        if (src == dst) {
            path.add(src);
            return path;
        }

        int parentNode = parent[dst];
        getShortestPath(parent, src, parentNode, path);
        path.add(dst);

        return path;
    }


}



