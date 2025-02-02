package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleDetection {
    public boolean hasCycleUndirected(int vertices, List<List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        for (int i=0; i< vertices; i++) {
            if (!visited.contains(i) && dfsUndirected(i, -1, visited, adj)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsUndirected(int node, int parent, Set<Integer> visited, List<List<Integer>> adj) {
        visited.add(node);

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;  // Skip the parent node
            if (visited.contains(neighbor) || dfsUndirected(neighbor, node, visited, adj)) {
                return true;  // Cycle detected
            }
        }
        return false;
    }

    public int getCycleLengthUndirected(int vertices, List<List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        int[] depth = new int[vertices];
        for (int i=0; i< vertices; i++) {
            if (!visited.contains(i)) {
                int cycleLength = dfsCycleLengthUndirected(i, -1, visited, adj, depth, 0);
                if (cycleLength > 0) {
                    return cycleLength;
                }
            }
        }
        return -1;
    }
    private int dfsCycleLengthUndirected(int node, int parent, Set<Integer> visited, List<List<Integer>> adj, int[] depth, int currentDepth) {
        visited.add(node);
        depth[node]= currentDepth;

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;  // Skip the parent node
            if (visited.contains(neighbor)) {
                // Cycle detected: Cycle length = Current depth - depth of visited node + 1
                return currentDepth - depth[neighbor] + 1;
            }

            int cycleLength = dfsCycleLengthUndirected(neighbor, node, visited, adj, depth, currentDepth + 1);
            if (cycleLength > 0) return cycleLength; // Return first found cycle length
        }
        return -1;
    }

    /*public int dfsCycleLengthUndirected(int vertices, List<List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();

    }*/

    /*private boolean dfsUDirected(int node, int parent,Set<Integer> visited, List<List<Integer>> adj) {

    }*/

    public int getCycleLengthDirected(int vertices, List<List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recPath = new HashSet<>();
        int[] depth = new int[vertices];
        for (int i=0; i< vertices; i++) {
            if (!visited.contains(i)) {
                int cycleLength = dfsCycleLengthDirected(i, visited, recPath, adj, depth, 0);
                if (cycleLength > 0) {
                    return cycleLength;
                }
            }
        }
        return -1;
    }
    private int dfsCycleLengthDirected(int node, Set<Integer> visited, Set<Integer> recPath, List<List<Integer>> adj, int[] depth, int currentDepth) {
        if (recPath.contains(node)) return currentDepth - depth[node] + 1; // cycle found

        recPath.add(node);
        visited.add(node);
        depth[node] = currentDepth;

        for (int neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                int cycleLength = dfsCycleLengthDirected(neighbor, visited, recPath, adj, depth, currentDepth + 1);
                if (cycleLength > 0 ) return cycleLength;
            }
        }
        recPath.remove(node);
        return -1;
    }

    public static void main(String[] args) {
        CycleDetection graph = new CycleDetection();
        int verticesUndirected = 5;

        List<List<Integer>> adjUndirected = new ArrayList<>();
        for (int i = 0; i < verticesUndirected; i++) {
            adjUndirected.add(new ArrayList<>());
        }
        adjUndirected.get(0).add(1);
        adjUndirected.get(1).add(0);
        adjUndirected.get(1).add(2);
        adjUndirected.get(2).add(1);
        adjUndirected.get(2).add(3);
        adjUndirected.get(3).add(2);
        adjUndirected.get(3).add(4);
        adjUndirected.get(4).add(3);
        adjUndirected.get(4).add(1); // Creating a cycle

        System.out.println("Undirected Graph has cycle: " + graph.getCycleLengthUndirected(verticesUndirected, adjUndirected));
    }
}
