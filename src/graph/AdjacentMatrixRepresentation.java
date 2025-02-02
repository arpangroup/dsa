package graph;

import java.util.*;

public class AdjacentMatrixRepresentation {
    public int[][] graph;

    public AdjacentMatrixRepresentation(int nodes) {
        this.graph = new int[nodes+1][nodes+1];
    }

    public void addEdge(int u, int v) {
        this.graph[u][v] = 1;
        this.graph[v][u] = 1;
    }

    public int[][] buildGraph(int[][] edges) {
        for (int[] edge : edges) {
            this.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    public void printGraph() {
        for (int i = 0; i<graph.length; i++) {
            for (int j=0; j<graph[0].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int[][] graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");

        // if some node is adjacent to the current node and
        // it has not already been visited
        for (int neighbor = 0; neighbor < graph[start].length; neighbor++) {
            if (graph[start][neighbor] == 1 && !visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    public void bfs(int start, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        visited[start] = true; // set source as visited
        //System.out.print(start + " ");

        while (!q.isEmpty()) {
            int current = q.remove();
            System.out.print(current + " ");

            // if some node is adjacent to the current node and
            // it has not already been visited
            for (int neighbor = 0; neighbor < graph[start].length; neighbor++) {
                if (graph[start][neighbor] == 1 && !visited[neighbor]) {
                     bfs(neighbor, visited);
                }
            }
        }


    }


    public static void main(String[] args) {
        int nodes = 7;
        int[][] edges = new int[][]{
                {1,2},
                {3,1},
                {5,3},
                {3,4},
                {7,6}
        };
        AdjacentMatrixRepresentation graph =  new AdjacentMatrixRepresentation(7);
        graph.buildGraph(edges);
        graph.printGraph();
        //graph.dfs(1, new boolean[7]);
        System.out.println();
        graph.bfs(1, new boolean[7]);

       /* int[][] adjMatrix = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0}
        };
        boolean[] visited = new boolean[adjMatrix.length];
        dfs(adjMatrix, 0, visited);*/
    }

}
