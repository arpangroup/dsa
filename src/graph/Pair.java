package graph;

public class Pair<T, R> {
    public T first;
    public R last;

    public Pair(T first, R last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + last + ")";
    }
}
