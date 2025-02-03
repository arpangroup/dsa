package graph.toposort;

import graph.DirectedGraph;

import java.util.*;

public class TopologicalSortingDFS<T> {

    public List<T> topologicalSort(Map<T, List<T>> graph) {
        Set<T> visited = new HashSet<>();
        Set<T> recPath = new HashSet<>();
        LinkedList<T> sortedPath = new LinkedList<>();

        for (T node : graph.keySet()) {
            if (!visited.contains(node) && dfs(node, graph, visited, recPath, sortedPath)) {
                throw new IllegalArgumentException("Graph contains a cycle, topological sort is not possible");
            }
        }
        return sortedPath;
    }

    private boolean dfs(T node, Map<T, List<T>> graph, Set<T> visited, Set<T> recPath, LinkedList<T> sortedPath) {
        if (recPath.contains(node)) return true; // Cycle detected
        if (visited.contains(node)) return false;

        visited.add(node);
        recPath.add(node);

        for (T neighbor:  graph.getOrDefault(node,new ArrayList<>())) {
            if (dfs(neighbor, graph, visited, recPath, sortedPath)) {
                return true;
            }
        }

        recPath.remove(node);
        sortedPath.addFirst(node);
        return false;
    }

    /*
        Instead of using a separate recPath set, we can merge it into visited by using three states
        0 → Unvisited
        1 → Visiting (In recursion stack)
        2 → Visited (Processed)
        This eliminates the need for an extra set.
     */
    public List<T> topologicalSortV1(Map<T, List<T>> graph) {
        Map<T, Integer> state = new HashMap<>(); // 0 = unvisited, 1 = visiting, 2 = visited
        LinkedList<T> sortedPath = new LinkedList<>();

        for (T node : graph.keySet()) {
            if (!state.containsKey(node) && dfsV1(node, graph, state, sortedPath)) {
                throw new IllegalArgumentException("Graph contains a cycle, topological sort is not possible");
            }
        }
        return sortedPath;
    }
    private boolean dfsV1(T node, Map<T, List<T>> graph, Map<T, Integer> state, LinkedList<T> sortedPath) {
        if (state.getOrDefault(node, 0) == 1) return true;  // Cycle detected
        if (state.getOrDefault(node, 0) == 2) return false; // Already processed

        state.put(node, 1); // Mark as visiting

        for (T neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfsV1(neighbor, graph, state, sortedPath)) {
                return true;
            }
        }

        state.put(node, 2); // Mark as visited
        sortedPath.addFirst(node);
        return false;
    }




    public static void main(String[] args) {
        DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.addEdges(new Integer[][]{ //topological_sort.png
                {5, 0},
                {4, 0},
                {5, 2},
                {2, 3},
                {3, 1},
                {4, 1}
        });
        /*
            The valid topological orderings are:
            [5, 4, 2, 3, 1, 0]
            [4, 5, 2, 3, 1, 0]
            [5, 4, 0, 2, 3, 1]
         */
        var graph = directedGraph.getGraph();
        directedGraph.printGraph();
        List<Integer> nodes = new TopologicalSortingDFS<Integer>().topologicalSort(graph);
        System.out.print(nodes);

    }
}
