package graph;

public class Pair {
    public int node;
    public int distance;

    public Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "(" + node + ", " + distance + ")";
    }
}
