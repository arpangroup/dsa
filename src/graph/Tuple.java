package graph;

public class Tuple<P, Q, R> {
    public P first;
    public Q second;
    public R third;

    public Tuple(P first, Q second, R third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
