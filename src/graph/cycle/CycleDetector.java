package graph.cycle;

import java.util.List;

public interface CycleDetector {
    boolean hasCycle(List<List<Integer>> graph);
    int cycleLength(List<List<Integer>> graph);
    List<Integer> cyclePath(List<List<Integer>> graph);
    List<List<Integer>> detectAllCycles(List<List<Integer>> graph);
}
