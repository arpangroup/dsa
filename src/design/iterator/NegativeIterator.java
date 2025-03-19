package design.iterator;

public class NegativeIterator implements CustomIterator<Integer>{
    private CustomIterator<Integer> dependencyIterator;

    public NegativeIterator(CustomIterator<Integer> dependencyIterator) {
        this.dependencyIterator = dependencyIterator;
    }

    @Override
    public boolean hasNext() {
        return true; // Infinite stream
    }

    @Override
    public Integer next() {
       return -dependencyIterator.next(); // Negate the value from dependency iterator
    }
}
