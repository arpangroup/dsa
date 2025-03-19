package design.iterator;

public class OddIterator implements CustomIterator<Integer>{
    private int current;

    public OddIterator() {
        this.current = 1; // Start with the first odd number
    }

    @Override
    public boolean hasNext() {
        return true; // Infinite stream of numbers
    }

    @Override
    public Integer next() {
        int nextOdd = current;
        current += 2; // Move to the next number
        return nextOdd;
    }
}
