package graph.path;

import java.util.List;

public interface PathDetection<T> {
    boolean hasPath(T src, T dst);
    List<T> printPath(T src, T dst);
    List<T> allPossiblePath(T src, T dst);
}
