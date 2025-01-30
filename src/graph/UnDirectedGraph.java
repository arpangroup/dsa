package graph;

import java.util.*;

public class UnDirectedGraph<T> {
    private Map<T, List<T>> graph;

    public UnDirectedGraph() {
        this.graph = new HashMap<>();
    }
    
    public void buildGraph(List<List<T>> edges) {
        for (List<T> edge : edges) {
            T node1 = edge.get(0);
            T node2 = edge.get(1);

            this.addEdge(node1, node2);
        }
    }

    public void buildGraph(T[][] edges) {
        for (T[] edge : edges) {
            this.addEdge(edge[0], edge[1]);
        }
    }

    public void addEdge(T node1, T node2) {
        // if a node is not in graph, add it
        if (!graph.containsKey(node1)) graph.put(node1, new ArrayList<>());
        if (!graph.containsKey(node2)) graph.put(node2, new ArrayList<>());

        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
    }

    public void removeVertex(T vertex) {
        graph.remove(vertex);

        for (List<T> neighbours : graph.values()) {
            neighbours.remove(vertex);
        }
    }

    public void removeEdge(T node1, T node2) {
        removeVertex(node1);
        removeVertex(node2);
    }

    public List<T> getNeighbours(T vertex) {
        List<T> neighbours = new ArrayList<>();
        if (graph.containsKey(vertex)) {
            neighbours = graph.get(vertex);
        }
        return neighbours;
    }


    public void printGraph() {
        graph.forEach((key, value) -> {
            System.out.print(key + " -> ");
            value.forEach(neighbour -> System.out.print(neighbour + " "));
            System.out.println();
        });
    }


    public boolean hasCycle() {
        return false;
    }


    public void dfs(T root) {

    }


    public void bfs(T src) {

    }


    public boolean hasPath(T src, T dst) {
       return hasPathHelper(src, dst, new HashSet<>(), new ArrayList<>());
    }

    private boolean hasPathHelper(T curr, T dst, Set<T> visited, List<T> path) {
        if (visited.contains(curr)) {
            return false; // if already  visited, no need to traverse to avoid infinite recursion
        }

        visited.add(curr);
        path.add(curr);

        if (curr.equals(dst)) {
            System.out.println(path);
            return true;
        }


        for (T neighbour : getNeighbours(curr)) {
            if (hasPathHelper(neighbour, dst, visited, path)) return true; // Return immediately when path is found
        }


        path.removeLast(); // Backtrack only when no path is found
        return false;
    }



    public void printPath(T source, T destination) {

    }
}
