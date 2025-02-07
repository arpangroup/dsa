package graph;

public class Pair {
    public int distance;
    public int node;

    public Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }

    @Override
    public String toString() {
        return "(" + distance + ", " + node + ")";
    }
}
