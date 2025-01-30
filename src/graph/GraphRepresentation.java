package graph;

public class GraphRepresentation {

    public static int[][] buildGraph(int n, int[][] edges) {
        int[][] graph = new int[n+1][n+1];

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            graph[node1][node2] = 1;
            graph[node2][node1] = 1;
        }
        return graph;
    }

    public static void printGraph(int n, int[][] graph) {
        int length = graph.length;
        System.out.println("GraphLength: " + length);
        for (int i = 0; i<=n; i++) {
            for (int j=0; j<=n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2}, {3,1}, {5,3}, {3,4}, {7,6}};
        int[][] graph =  GraphRepresentation.buildGraph(7, edges);
        printGraph(7, graph);
    }


}
