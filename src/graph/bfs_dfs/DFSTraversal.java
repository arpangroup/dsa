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

    public static List<Integer> dfs(List<List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(graph, 0, visited);
        return visited.stream().toList();
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

    public static List<List<Integer>> allPossibleDfsPath(List<List<Integer>> graph, int src) {
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>(); // Stores all possible paths
        List<Integer> currentPath = new ArrayList<>(); // Current path

        // Start DFS from node 0 (or iterate over all nodes for disconnected graphs)
        allPossibleDfsPathHelper(graph, src, visited, currentPath, result);
        return result;
    }

    private static void allPossibleDfsPathHelper(List<List<Integer>> graph, Integer src, Set<Integer> visited, List<Integer> currentPath, List<List<Integer>> result) {
        // Mark the current node as visited and add it to the path
        visited.add(src);
        currentPath.add(src);

        // If the current path includes all nodes, add it to the result
        /*if (currentPath.size() == graph.size()) {
            result.add(new ArrayList<>(currentPath)); // Add a copy of the current path
        } else {
            // Recursively explore all unvisited neighbors
            for (int nei : graph.get(src)) {
                if (!visited.contains(nei)) {
                    allPossibleDfsPathHelper(graph, nei, visited, currentPath, result);
                }
            }
        }*/

        // ********************************************************
        // Add the current path to the result (even if it doesn't include all nodes)
        // In the given graph, not all nodes are reachable from the starting node (0)
        // Since no single path includes all nodes, the condition currentPath.size() == graph.size() is never satisfied, and the result remains empty.
        result.add(new ArrayList<>(currentPath));

        // Recursively explore all unvisited neighbors
        for (int nei : graph.get(src)) {
            if (!visited.contains(nei)) {
                allPossibleDfsPathHelper(graph, nei, visited, currentPath, result);
            }
        }
        // ********************************************************

        // Backtrack: Unmark the current node and remove it from the path
        visited.remove(src);
        currentPath.remove(currentPath.size() - 1); // Remove the last element
    }

    public static int maxDepth(List<List<Integer>> graph) {
        return 0;
    }
}
