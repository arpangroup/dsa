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


    public void addEdge(T src, T destination) {
        adjList.get(src).add(destination);
    }

    public void removeVertex(T vertex) {
        adjList.remove(vertex);

        // Remove the vertex from the neighbour of other vertex
        for (List<T> neighbours : adjList.values()) {
            neighbours.remove(vertex);
        }
    }

    public void removeEdge(T src, T destination) {
        if (isDirectedGraph) {
            adjList.get(src).remove(destination);
        } else {
            adjList.get(destination).remove(src);
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

    public void dfsUsingRecursion(T src) {
       System.out.println(src);
       getNeighbours(src).forEach(this::dfsUsingRecursion);
    }

    public void bfs(T src) {
        Queue<T> queue = new ArrayDeque<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            System.out.println(current);
            queue.addAll(getNeighbours(current));
        }
    }

    public boolean hasPath(T src, T destination) {
        if (src.equals(destination)) return true;
        boolean isPathFound = false;

        for (T neighbour : getNeighbours(src)) {
            if (hasPath(neighbour, destination)) {
                isPathFound =  true;
                break;
            }
        }
        return isPathFound;
    }

    public boolean hasPathUsingBFS(T src, T destination) {
        if (src.equals(destination)) return true;

        Queue<T> q = new ArrayDeque<>();
        q.add(src);

        while (!q.isEmpty()) {
            T current = q.poll();
            if (current.equals(destination)) {
                return true;
            } else {
                for (T neighbour : getNeighbours(current)) {
                    q.add(neighbour);
                }
            }
        }

        return false;
    }


    void printPath(T source, T destination) {
        List<T> path = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        printPathHelper(source, destination, path, visited);
    }

    private void printPathHelper(T current, T destination, List<T> path, Set<T> visited) {
        path.add(current);
        visited.add(current);

        if (current.equals(destination)) {
            System.out.println(path);
        } else {
            for (T neighbor : getNeighbours(current)) {
                if (!visited.contains(neighbor)) {
                    printPathHelper(neighbor, destination, path, visited);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public boolean hasCycle(T source) {
        return false;
    }




}
