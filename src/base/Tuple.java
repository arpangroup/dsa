package base;

public class Tuple<K, V, W> {
    public K first;
    public V second;
    public W third;

    public Tuple(K first, V second, W third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
