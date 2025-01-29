package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphMain {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.add("a", List.of("b", "c"));
        graph.add("b", List.of("d"));
        graph.add("c", List.of("e"));
        graph.add("d", List.of("f"));
        graph.add("e", null);
        graph.add("f", null);
        //graph.printGraph();

        //graph.dfs("a"); // [a,c,e,b,d,f] or [a,b,d,f,c,e]
        //graph.dfsUsingRecursion("a");
        //graph.bfs("a"); //[a,b,c,d,e,f]
        graph.printPath("a", "e");


        /*Graph<String> graph = new Graph<>();
        graph.add("a", List.of("b", "c"));
        graph.add("b", List.of("d"));
        graph.add("c", List.of("e"));
        graph.add("d", List.of("f"));
        graph.add("e", List.of("a"));
        graph.add("f", null);
        graph.dfs("a"); // infini loop*/

    }
}
