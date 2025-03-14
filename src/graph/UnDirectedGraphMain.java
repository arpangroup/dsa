package graph;

import java.util.Arrays;
import java.util.List;

public class UnDirectedGraphMain {
    public static void main(String[] args) {
        List<List<String>> edges = List.of(
                List.of("i", "j"),
                List.of("k", "i"),
                List.of("m", "k"),
                List.of("k", "l"),
                List.of("o", "n")
        );
        UnDirectedGraph<String> graph = new UnDirectedGraph<>();
        graph.buildGraph(edges);
        graph.printGraph();

        /*System.out.println("Removing vertex : " + "k");
        graph.removeVertex("k");
        graph.printGraph();*/

        System.out.println("###################");
        System.out.println(graph.hasPath("i", "l"));
    }
}
