package graph.bfs_dfs;

import java.util.*;

public class DFSTraversal {
    public static void dfsPrintIterative(List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];

        stack.push(0);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.print(current + " ");

            for(int nei : graph.get(current)) {
                if (!visited[nei]) {
                    stack.push(nei);
                    visited[nei] = true;
                }
            }
        }
    }

    public static List<Integer> dfs(List<List<Integer>> graph, Integer src) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recPath = new HashSet<>();
        dfsHelper(graph, 0, visited);
        return List.of();
    }

    /**
     * Below code will work for both directed and undirected graphs
     * and it ensures that each node is visited exactly once
     *
     * If this code work, Why we need to track
     *          parent for cycle detection in Undirected graph? and
     *          recPath in directed graph?
     *
     */
    private static void dfsHelper(List<List<Integer>> graph, int src, Set<Integer> visited) {
        if (visited.contains(src)) return;
        System.out.print(src + " ");

        visited.add(src);

        for (int nei : graph.get(src)) {
            if (!visited.contains(nei)) {
                dfsHelper(graph, nei, visited);
            }
        }
    }

    public static List<List<Integer>> allPossibleDfsPath(List<List<Integer>> graph, Integer src) {
        return List.of();
    }

    public static int maxDepth(List<List<Integer>> graph) {
        return 0;
    }
}
