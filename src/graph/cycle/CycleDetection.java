package graph.cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CycleDetection<T> {
    boolean hasCycle(Map<T, List<T>> graph);
    int cycleLength(Map<T, List<T>> graph);
    List<Integer> cyclePath(Map<T, List<T>> graph);
    List<List<Integer>> detectAllCycles(Map<T, List<T>> graph);
}
