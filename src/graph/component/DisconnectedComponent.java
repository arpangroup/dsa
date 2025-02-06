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

    public int largestComponentCount(List<List<Integer>> graph) {
        return 0;
    }
}
