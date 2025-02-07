package graph.cycle;

import java.util.*;

public class CycleDetectionUndirected implements CycleDetector {
    @Override
    public boolean hasCycle(List<List<Integer>> graph) {
        return false;
    }

    @Override
    public int cycleLength(List<List<Integer>> graph) {
        return 0;
    }

    @Override
    public List<Integer> cyclePath(List<List<Integer>> graph) {
        return new ArrayList<>();
    }

    @Override
    public List<List<Integer>> detectAllCycles(List<List<Integer>> graph) {
        return new ArrayList<>();
    }

}
