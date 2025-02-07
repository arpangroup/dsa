package graph;

import java.util.List;

public class WeightedGraphMain {
    public static void main(String[] args) {
        /*
            0 -> {1, 4} {2, 4}
            1 -> {0, 4} {2, 2}
            2 -> {0, 4} {1, 2} {2, 3} {4, 1} {5, 6}
            3 -> {2, 3} {5, 2}
            4 -> {2, 1} {5, 3}
            5 -> {2, 6} {3, 2} {4, 3}
         */
        List<List<List<Integer>>> weightedGraph = List.of(
          List.of(List.of(1, 4), List.of(2, 4)),
          List.of(List.of(0, 4), List.of(2, 2)),
          List.of(List.of(0, 4), List.of(1, 2), List.of(2, 3), List.of(4, 1), List.of(5, 6)),
          List.of(List.of(2, 3), List.of(5, 2)),
          List.of(List.of(2, 1), List.of(5, 3)),
          List.of(List.of(2, 6), List.of(3, 2), List.of(4, 3))
        );

        GraphUtil.printWeightedGraph(weightedGraph);
    }
}
