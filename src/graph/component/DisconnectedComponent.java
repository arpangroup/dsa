package graph.component;

import graph.bfs_dfs.DFSTraversal;

import java.util.*;

public class DisconnectedComponent {
    public static int countConnectedComponents(List<List<Integer>> graph) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();

        int vertices = graph.size();
        for (int i=0; i< vertices; i++) {
            if (!visited.contains(i)) {
                System.out.print("Component " + count + ": ");
                DFSTraversal.dfsHelper(graph, i, visited);
                System.out.println();
                count ++;
            }
        }
        return count;
    }

    public static int largestComponentCount(List<List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int vertices = graph.size();
        int maxCount = Integer.MIN_VALUE;

        for (int i=0; i< vertices; i++) {
            if (!visited.contains(i)) {
                int count = DFSTraversal.noOfNodesInDfs(graph, i, visited);
                System.out.println("Count: " + count);
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }
}
