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

        for (int nei : graph.get(src)) {
            if (!visited[nei]) {
                if (hasCycleHelper(graph, nei, visited, recStack)) {
                    return true; // Cycle detected in the subtree
                }
            } else if (recStack[nei]) {
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

    private int cycleLengthHelper(List<List<Integer>> graph, int src, boolean[] visited, boolean[] recStack, int[] depth, int currentDepth) {
        visited[src] = true; // Mark the current node as visited
        recStack[src] = true; // Add the current node to the recursion stack
        depth[src] = currentDepth; // Store the depth of the current node

        for (int nei : graph.get(src)) {
            if (!visited[nei]) {
                int cycleLength = cycleLengthHelper(graph, nei, visited, recStack, depth, currentDepth + 1);
                if (cycleLength > 0) {
                    return cycleLength; // Propagate the cycle length back to the caller
                }
            } else if (recStack[nei]) {
                // Cycle detected: calculate the length of the cycle
                int cycleStartIndex = depth[nei];
                int cycleLength = currentDepth - cycleStartIndex + 1; // +1 to include both start and end nodes
                return cycleLength;
            }
        }
        recStack[src] = false; // Remove the current node from the recursion stack
        return 0; // No cycle found in this subtree
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
