package graph.toposort;

import java.util.*;

public class AllTopologicalSortsDFS<T> {
    public List<List<T>> allTopologicalSorts(Map<T, List<T>> graph) {
        Map<T, Integer> inDegree = new HashMap<>();
        List<List<T>> result = new ArrayList<>();
        List<T> order = new ArrayList<>();
        Set<T> visited = new HashSet<>();

        // Initialize in-degree
        for (T node : graph.keySet()) {
            inDegree.put(node, 0);
        }
        for (T node : graph.keySet()) {
            for (T neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Start DFS backtracking
        dfs(graph, inDegree, visited, order, result);
        return result;
    }

    private void dfs(Map<T, List<T>> graph, Map<T, Integer> inDegree, Set<T> visited, List<T> order, List<List<T>> result) {
        boolean found = false;

        for (T node : graph.keySet()) {
            if (!visited.contains(node) && inDegree.get(node) == 0) { // Choose node with no dependencies
                visited.add(node);
                order.add(node);

                // Reduce in-degree of neighbors
                for (T neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                }

                // Recur for next choices
                dfs(graph, inDegree, visited, order, result);

                // Backtrack: Undo choices
                visited.remove(node);
                order.remove(order.size() - 1);
                for (T neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    inDegree.put(neighbor, inDegree.get(neighbor) + 1);
                }

                found = true;
            }
        }

        // If no more nodes can be chosen, we found one valid ordering
        if (!found) {
            result.add(new ArrayList<>(order));
        }
    }
}
