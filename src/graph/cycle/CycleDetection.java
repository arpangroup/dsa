package graph.cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CycleDetection {
    boolean hasCycle(List<List<Integer>> graph);
    int cycleLength(List<List<Integer>> graph);
    List<Integer> cyclePath(List<List<Integer>> graph);
    List<List<Integer>> detectAllCycles(List<List<Integer>> graph);
}
