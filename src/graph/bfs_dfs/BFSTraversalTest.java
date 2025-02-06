package graph.bfs_dfs;

import graph.GraphUtil;

import java.util.List;

// https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
public class BFSTraversalTest {
    static List<List<Integer>> graph_directed_1 = List.of(
            List.of(1, 2),   //  a: ['b', 'c'],
            List.of(3),  //  b: ['d'],
            List.of(4),  //  c: ['e'],
            List.of(5),  //  d: ['f'],
            List.of(),       //  e: [],
            List.of()        //  f: []
    );

    static List<List<Integer>> graph_directed_2 = List.of(
            List.of(1, 2),   //  a: ['b', 'c'],
            List.of(3),  //  b: ['d'],
            List.of(4),  //  c: ['e'],
            List.of(5),  //  d: ['f'],
            List.of(0),  //  e: ['a'], <-----creating cycle
            List.of()        //  f: []
    );

    static List<List<Integer>> graph_undirected_1 = GraphUtil.buildGraph(new int[][]{
            {0, 2},
            {0, 3},
            {0, 1},
            {2, 4}
    });

    static List<List<Integer>> graph_undirected_2 = List.of( // same as above graph
            List.of(2, 3, 1), // {0,2}, {0,3}, {0,1}
            List.of(0),   // {1,0}
            List.of(0, 4),    // {2,0}, {2,4}
            List.of(0),   // {3,0}
            List.of(2)    // {4,2}
    );

    public static void main(String[] args) {

//        test_simpleDirectedGraph_without_cycle();
//        test_simpleDirectedGraph_with_cycle();
//        test_countHeight();

    }

    private static void test_simpleDirectedGraph_without_cycle() {
        GraphUtil.printGraph(graph_directed_1);
        BFSTraversal.bfsPrintForDAG(graph_directed_1);
    }

    private static void test_simpleDirectedGraph_with_cycle() {
        GraphUtil.printGraph(graph_directed_2);
        //BFSTraversal.bfsPrintForDAG(graph_directed_2); // goes in infinite loop

        List<Integer> path = BFSTraversal.bfs(graph_directed_2);
    }

    private static void test_countHeight() {
        GraphUtil.printGraph(graph_directed_2);
        //BFSTraversal.bfsPrintForDAG(graph_directed_2); // goes in infinite loop

        List<List<Integer>> path = BFSTraversal.getNodesInEachLevel(graph_directed_2, 0);
        System.out.print(path);
    }
}
