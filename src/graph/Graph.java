package graph;

import java.util.*;

public class Graph<T> {
    private Map<T, List<T>> adjList;
    private boolean isDirectedGraph;


    public Graph() {
        adjList = new HashMap<>();
    }


    public void addVertex(T vertex) {
        adjList.put(vertex, new ArrayList<>());
    }

    /*public void add(T source, T... destinations) {
        addVertex(source);
        for (T destination : destinations) {
            addEdge(source, destination);
        }
    }*/
    public void add(T source, List<T> destinations) {
        addVertex(source);
        if (destinations != null) destinations.forEach(dest -> addEdge(source, dest));
    }


    public void addEdge(T source, T destination) {
        adjList.get(source).add(destination);
    }

    public void removeVertex(T vertex) {
        adjList.remove(vertex);

        // Remove the vertex from the neighbour of other vertex
        for (List<T> neighbours : adjList.values()) {
            neighbours.remove(vertex);
        }
    }

    public void removeEdge(T source, T destination) {
        if (isDirectedGraph) {
            adjList.get(source).remove(destination);
        } else {
            adjList.get(destination).remove(source);
        }

    }

    public List<T> getNeighbours(T vertex) {
        return adjList.get(vertex);
    }

    public void printGraph() {
        for (Map.Entry<T, List<T>> entry: adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            entry.getValue().forEach(neighbour -> System.out.print(neighbour + " "));
            System.out.println();
        }
    }

    public void dfs(T root) {
        Stack<T> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            System.out.println(current);
            getNeighbours(current).forEach(stack::push);
        }
    }

    public void dfsUsingRecursion(T source) {
       System.out.println(source);
       getNeighbours(source).forEach(this::dfsUsingRecursion);
    }

    public void bfs(T source) {
        Queue<T> queue = new ArrayDeque<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            System.out.println(current);
            queue.addAll(getNeighbours(current));
        }
    }
}
