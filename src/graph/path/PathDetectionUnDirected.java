package graph.path;

import java.util.ArrayList;
import java.util.List;

public class PathDetectionUnDirected<T> implements PathDetection<T> {
    @Override
    public boolean hasPath(T src, T dst) {
        return false;
    }

    @Override
    public List<T> printPath(T src, T dst) {
        return new ArrayList<>();
    }

    @Override
    public List<T> allPossiblePath(T src, T dst) {
        return new ArrayList<>();
    }
}
