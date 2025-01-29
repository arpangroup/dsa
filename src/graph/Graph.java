package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Graph<T> {
    void addVertex(T vertex);
    void add(T source, List<T> destinations);
    void addEdge(T src, T destination);
    void removeVertex(T vertex);
    void removeEdge(T src, T destination);
    List<T> getNeighbours(T vertex);
    void printGraph();
    boolean hasCycle();
    void dfs(T root);
    void bfs(T src);
    boolean hasPath(T src, T destination);
    void printPath(T source, T destination);
}
