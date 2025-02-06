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
}
