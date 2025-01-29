package graph;

import java.util.*;

public class DirectedGraph<T> implements Graph<T>{
    private Map<T, List<T>> graph;
    private boolean isDirectedGraph;


    public DirectedGraph() {
        graph = new HashMap<>();
    }


    @Override
    public void addVertex(T vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    @Override
    public void add(T source, List<T> destinations) {
        addVertex(source);
        if (destinations != null) destinations.forEach(dest -> addEdge(source, dest));
    }


    @Override
    public void addEdge(T src, T destination) {
        graph.get(src).add(destination);
    }

    @Override
    public void removeVertex(T vertex) {
        graph.remove(vertex);

        // Remove the vertex from the neighbour of other vertex
        for (List<T> neighbours : graph.values()) {
            neighbours.remove(vertex);
        }
    }

    @Override
    public void removeEdge(T src, T destination) {
        if (isDirectedGraph) {
            graph.get(src).remove(destination);
        } else {
            graph.get(destination).remove(src);
        }

    }

    @Override
    public List<T> getNeighbours(T vertex) {
        return graph.get(vertex);
    }

    @Override
    public void printGraph() {
        for (Map.Entry<T, List<T>> entry: graph.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            entry.getValue().forEach(neighbour -> System.out.print(neighbour + " "));
            System.out.println();
        }
    }


    @Override
    public boolean hasCycle() {
        Set<T> visited = new HashSet<>();
        Set<T> recStack = new HashSet<>();

        for (T node : graph.keySet()) {
            if (hasCycleHelper(node, visited, recStack)) {
                return true; // Cycle found
            }
        }
        return false; // No cycle
    }

    private boolean hasCycleHelper(T node, Set<T> visited, Set<T> recStack) {
        if (recStack.contains(node)) return true; // Cycle detected
        if (visited.contains(node)) return false; // Already processed

        visited.add(node);
        recStack.add(node);

        for (T neighbor : getNeighbours(node)) {
            if (hasCycleHelper(neighbor, visited, recStack)) {
                return true;
            }
        }

        recStack.remove(node); // Backtrack
        return false;
    }

    @Override
    public void dfs(T root) {
        Stack<T> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            System.out.println(current);
            getNeighbours(current).forEach(stack::push);
        }
    }

    private void dfsUsingRecursion(T src) {
       System.out.println(src);
       getNeighbours(src).forEach(this::dfsUsingRecursion);
    }

    @Override
    public void bfs(T src) {
        Queue<T> queue = new ArrayDeque<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            System.out.println(current);
            queue.addAll(getNeighbours(current));
        }
    }

    @Override
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

    @Override
    public void printPath(T source, T destination) {
        List<T> path = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        printPathHelper(source, destination, path, visited);
    }


    /*
        DAG → No visited
        Cyclic Graph → Use visited to prevent infinite loops
     */
    private void printPathHelper__Directed(T current, T destination, List<T> path, Set<T> visited) {
        if (visited.contains(current)) return; // Avoid cycles

        path.add(current);
        visited.add(current); // not required for DAG

        if (current.equals(destination)) {
            System.out.println(path);
        } else {
            for (T neighbor : getNeighbours(current)) {
                printPathHelper__Directed(neighbor, destination, path, visited);

            }
        }
        path.remove(path.size() - 1); // Backtrack
        visited.remove(current); // not required for DAG
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





}
