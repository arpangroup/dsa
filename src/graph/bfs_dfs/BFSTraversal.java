package graph.bfs_dfs;

import java.util.*;

public class BFSTraversal {

    /**
     * bfsPrint will work only for graph without cycle (eg: DAG)
     */
    public static void bfsPrintForDAG(List<List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {
            Integer current = q.poll();
            System.out.print(current + " ");

            for(Integer neighbor : graph.get(current)) {
                q.add(neighbor);
            }
        }
    }

    public static List<Integer> bfs(List<List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // visited = new boolean[graph.size()]

        q.add(0);
        visited.add(0); // visited[src] = true

        while (!q.isEmpty()) {
            Integer current = q.poll();
            System.out.print(current + " ");

            for(Integer neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) { // !visited[neighbor]
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited.stream().toList();
    }

    /**
     * https://www.geeksforgeeks.org/count-number-nodes-given-level-using-bfs/
     * https://www.geeksforgeeks.org/level-order-tree-traversal/
     */
    public static List<List<Integer>> getNodesInEachLevel(List<List<Integer>> graph, int root) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        List<List<Integer>> levels = new ArrayList<>();

        q.add(root);
        visited[root] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i= 0; i< size; i++) {
                int current = q.poll();
                level.add(current);

                for(Integer nei : graph.get(current)) {
                    if(!visited[nei]) {
                        q.add(nei);
                        visited[nei] = true;
                    }
                }
            }
            levels.add(level);
        }
        return levels;
    }
}
