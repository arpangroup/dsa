package graph.bipertite;

import graph.CycleDetection;
import graph.bfs_dfs.DFSTraversal;
import graph.cycle.CycleDetectionUndirected;

import java.util.*;

public class BipartiteGraph {

    /**
     * Every graph with even cycle length is a Bipartite graph
     */
    public static boolean isBipartiteGraph_usingCycleLength(List<List<Integer>> graph) {
        int cycleLength = new CycleDetectionUndirected().cycleLength(graph);
        if (cycleLength == 0) return false; // no cycle at all
        return cycleLength % 2 == 0; // every graph with even cycle length is a Bipartite graph
    }

    public static boolean isBipartiteGraph_usingGraphColoring(List<List<Integer>> graph) {
        // -1: Not colored yet or not visited yet
        // 0: 1st color
        // 1: 2nd color
        int[] visited = new int[graph.size()];
        Arrays.fill(visited, -1); // initially no nodes are visited yet
        int startingColor = 0; // let's choose color-0 <------same like visited array

        for (int i=0; i< graph.size(); i++) {
            if (visited[i] == -1) { // if not yet visited
                if (!dfsGraphColoring(graph, i, visited, startingColor)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfsGraphColoring(List<List<Integer>> graph, int src, int[] colors, int currentColor) {
        colors[src] = currentColor;

        for (int nei : graph.get(src)) {
            if (colors[nei] == -1) { // Neighbor not yet visited, color with opposite color
                if(!dfsGraphColoring(graph, nei, colors,1 - currentColor)) { // 1-x ==> is same as ==> (currentColor == 1) ? 0 : 1
                    return false; // If coloring fails, the graph is not bipartite
                }
            } else if (colors[nei] == currentColor) {
                // If the neighbor has the same color as the current node, the graph is not bipartite
                return false;
            }
        }
        return true;
    }

    public static List<Integer> bipartiteSets(List<List<Integer>> graph) {
        return List.of();
    }
}
