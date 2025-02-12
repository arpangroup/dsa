package graph.representation;

import java.util.*;

public class AdjacentMatrixRepresentation {
    final int SIZE;

    public AdjacentMatrixRepresentation(int size) {
        this.SIZE = size;
    }

    public int[][] buildGraph(int[][] edges) {
        int[][] graph = new int[SIZE+1][SIZE+1];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        return graph;
    }

    public List<Integer> getNeighbours(int[][] graph, int node) {
        List<Integer> neighbours = new ArrayList<>();
        for (int i=0; i< graph.length; i++) {
            if (graph[node][i] == 1) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    public void printGraph(int[][] graph) {
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
        /*for (int neighbor = 0; neighbor < graph[start].length; neighbor++) {
            if (graph[start][neighbor] == 1 && !visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }*/

        for (int neighbor : getNeighbours(graph, start)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    public void bfs(int[][] graph, int start, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        visited[start] = true; // set source as visited
        //System.out.print(start + " ");

        while (!q.isEmpty()) {
            int current = q.remove();
            System.out.print(current + " ");

            // if some node is adjacent to the current node and
            // it has not already been visited
            /*for (int neighbor = 0; neighbor < graph[start].length; neighbor++) {
                if (graph[start][neighbor] == 1 && !visited[neighbor]) {
                     bfs(graph, neighbor, visited);
                }
            }*/

            for (int neighbor : getNeighbours(graph, start)) {
                if (!visited[neighbor]) {
                    bfs(graph, neighbor, visited);
                }
            }
        }


    }


    public static void main(String[] args) {
        AdjacentMatrixRepresentation solution = new AdjacentMatrixRepresentation(7);
        int[][] graph =  solution.buildGraph(new int[][]{
                {1,3},
                {1,2},
                {5,3},
                {3,4},
                {7,6}
        });
        solution.printGraph(graph);
        List<Integer> neighbours = solution.getNeighbours(graph, 3);
        System.out.println("NEIGHBOURS of 3: " + neighbours);

        System.out.println("DFS: ");
        solution.dfs(graph, 1, new boolean[7]); // [1, 2, 3, 4, 5]

        System.out.println("\nBFS: ");
        solution.bfs(graph, 1, new boolean[7]);

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
