package graph.mst;


import graph.Pair;
import graph.Tuple;

import java.util.*;

/**
 * Step1: Choose any starting vertex, look at all edges connecting to the vertex & choose one with lowest weight & add it to the tree
 * Step2: Look ar all edges connected to the tree, choose the one with the lowest weight & add it to the tree
 * Step3: Repeat Step2 until all vertices are in the tree.
 *
 */
/*
verices.forEach(v => {
    cost[v] = INF
    parent[v] = NULL // arrays to store constructed MST

    key[v] = INFINITY; // key value used to pick a min weight edge in cut
    bool[] visited; to represent set of vertices not yet included in MST

    cost[0] = 0;
    parent[0] = -1; // first node is always root node

    q.add(v);
    while(!q.isempty()) {
          curr = q.poll();
          visited[curr] = true // add picked vertex to MST

          for(nei : neighbour(curr)) {
             if (cost[z] > w[v][z]) {
                cost[z] = w(v,z)
                parent[z] = curr
             }
          }
    }
});
 */
public class PrimsMST {
    List<Edge> primMST(List<List<List<Integer>>> graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight)); // {src, dest, weight}
        boolean[] visited = new boolean[graph.size()];
        List<Edge> mst = new ArrayList<>();

        pq.add(new Edge(-1, 0, 0));

        int sum = 0;
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();

            if (!visited[currentEdge.dest]) { //If the destination vertex is not yet included in MST
                mst.add(currentEdge); // add the edge to current MST
                visited[currentEdge.dest] = true; // Mark the destination vertex as included in MST
                sum += currentEdge.weight;

                for (List<Integer> nei : graph.get(currentEdge.dest)) {
                    int parent = nei.get(0);
                    int node = nei.get(1);
                    int weight = nei.get(2);
                    if (!visited[node]) {
                        pq.add(new Edge(parent, node, weight));
                    }
                }
            }
        }
        System.out.println("MSTWeight: " + sum);
        return mst;
    }

    public void printMST(List<Edge> mst) {
        for (Edge edge : mst) {
            if (edge.src == -1) continue;
            System.out.println("Edge: " + edge.src + " - "
                    + edge.dest + " Weight: " + edge.weight);
        }
    }

    static class Edge {
        int src;
        int dest;
        int weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> graph = List.of(
          List.of(List.of(0, 1, 1)),
          List.of(List.of(1, 2, 2)),
          List.of(List.of(2, 3, 3)),
          List.of(List.of(0, 3, 4))
        );
        List<Edge> edges = new PrimsMST().primMST(graph);
        new PrimsMST().printMST(edges);
    }


}
