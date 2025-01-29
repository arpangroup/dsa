package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Graph<T> {
    void addVertex(T vertex);
    void add(T src, List<T> dst);
    void addEdge(T src, T dst);
    void removeVertex(T vertex);
    void removeEdge(T src, T dst);
    List<T> getNeighbours(T vertex);
    void printGraph();
    boolean hasCycle();
    void dfs(T root);
    void bfs(T src);
    boolean hasPath(T src, T dst);
    void printPath(T src, T dst);
}
