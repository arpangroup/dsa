package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphUtil {
    public static List<List<Integer>> buildGraph(int[][] edges, boolean isDirected) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i=0; i< edges.length + 1; i++) {
            adjList.add(new ArrayList<>());
        }


        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjList.get(u).add(v);
            if (!isDirected) {
                adjList.get(v).add(u);
            }
        }

        return adjList;
    }

    public static List<List<Integer>> buildGraph(int[][] edges) {
        return buildGraph(edges, false);
    }

    public static void printGraph(List<List<Integer>> adjList) {
        for (int i=0; i < adjList.size(); i++) {
            System.out.print(i + " -> ");
            for (int neighbour : adjList.get(i)) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }
    }

    public static void printWeightedGraph(List<List<List<Integer>>> adj) {
        for (int i=0; i< adj.size(); i++) {
            System.out.print(i + " -> ");
            for (List<Integer> neighbor : adj.get(i)) {
                int node = neighbor.get(0);
                int weight = neighbor.get(1);
                System.out.print("(" + node + ", " + weight + ") ");
            }
            System.out.println();
        }
    }
}
