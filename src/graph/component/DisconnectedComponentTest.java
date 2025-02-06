package graph.component;

import graph.GraphUtil;
import graph.bfs_dfs.BFSTraversal;

import java.util.HashSet;
import java.util.List;

import static graph.bfs_dfs.DFSTraversal.dfsHelper;

// https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
public class DisconnectedComponentTest {
    static List<List<Integer>> graph_directed_1 = List.of(
            List.of(1, 2),   //  a: ['b', 'c'],
            List.of(3),  //  b: ['d'],
            List.of(4),  //  c: ['e'],
            List.of(5),  //  d: ['f'],
            List.of(),       //  e: [],
            List.of(),       //  f: []
            List.of(7),  //  g: ['h'] <-------Disconnected component
            List.of()   //  g: ['h'] <-------Disconnected component
    );


    public static void main(String[] args) {
        int components = DisconnectedComponent.countConnectedComponents(graph_directed_1);
        System.out.println("Total Components: " + components);

        System.out.println("....................");
        int largestComponent = DisconnectedComponent.largestComponentCount(graph_directed_1);
        System.out.print("largest Components with nodes: " + largestComponent);
    }

}
