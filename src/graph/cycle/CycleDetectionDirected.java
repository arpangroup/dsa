package graph.cycle;

import java.util.*;

public class CycleDetectionDirected implements CycleDetection {
    @Override
    public boolean hasCycle(List<List<Integer>> graph) {
        return false;
    }

    private boolean hasCycleHelper(List<List<Integer>> graph, int src, boolean[] visited, boolean[] recStack) {
        visited[src] = true;
        recStack[src] = true;

        for (int neighbour : graph.get(src)) {
            if (!visited[src]) {
                if (hasCycleHelper(graph, neighbour, visited, recStack)) {
                    return true; // Cycle detected in the subtree
                }
            } else if (recStack[neighbour]) {
                return true; // Cycle detected (node is already in the recursion stack)
            }
        }
        recStack[src] = false;
        return false;
    }

    @Override
    public int cycleLength(List<List<Integer>> graph) {
        return 0;
    }

    @Override
    public List<Integer> cyclePath(List<List<Integer>> graph) {
        return new ArrayList<>();
    }

    @Override
    public List<List<Integer>> detectAllCycles(List<List<Integer>> graph) {
        return new ArrayList<>();
    }
}
