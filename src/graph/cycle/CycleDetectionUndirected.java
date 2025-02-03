package graph.cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CycleDetectionUndirected<T> implements CycleDetection<T> {
    @Override
    public boolean hasCycle(Map<T, List<T>> graph) {
        return false;
    }

    @Override
    public int cycleLength(Map<T, List<T>> graph) {
        return 0;
    }

    @Override
    public List<Integer> cyclePath(Map<T, List<T>> graph) {
        return new ArrayList<>();
    }

    @Override
    public List<List<Integer>> detectAllCycles(Map<T, List<T>> graph) {
        return new ArrayList<>();
    }
}
