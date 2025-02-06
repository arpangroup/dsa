package graph.bfs_dfs;

import graph.GraphUtil;

import java.util.List;

// https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
public class DFSTraversalTest {
    static List<List<Integer>> graph_directed_1 = List.of(
            List.of(1, 2),   //  a: ['b', 'c'],
            List.of(3),  //  b: ['d'],
            List.of(4),  //  c: ['e'],
            List.of(5),  //  d: ['f'],
            List.of(),       //  e: [],
            List.of()        //  f: []
    );


    public static void main(String[] args) {

        var path = DFSTraversal.allPossibleDfsPath(graph_directed_1, 0);
        System.out.println(path); // [[0], [0, 1], [0, 1, 3], [0, 1, 3, 5], [0, 2], [0, 2, 4]]

    }
}
