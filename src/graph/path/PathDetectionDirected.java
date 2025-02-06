package graph.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathDetectionDirected<T> implements PathDetection<T> {
    @Override
    public boolean hasPath(T src, T dst) {
        return false;
    }

    private static boolean hasPathDFS(List<List<Integer>> graph, int src, int dst, boolean[] visited) {
        if (src == dst) return true;
        visited[src] = true;

        for (int nei : graph.get(src)) {
            if (!visited[nei]) {
                if (hasPathDFS(graph, nei, dst, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<T> printPath(T src, T dst) {
        return new ArrayList<>();
    }

    private static boolean printPathDFS(List<List<Integer>> graph, int src, int dst, Set<Integer> visited, List<Integer> path) {
        if (src == dst) return true;
        path.add(src);
        visited.add(src);

        for (int nei : graph.get(src)) {
            if (!visited.contains(nei)) {
                if (printPathDFS(graph, nei, dst, visited, path)) {
                    return true;
                }
            }
        }

        // Backtrack if no path found
        path.remove(path.size() - 1);
        return false;
    }

    @Override
    public List<T> allPossiblePath(T src, T dst) {
        return new ArrayList<>();
    }
}
