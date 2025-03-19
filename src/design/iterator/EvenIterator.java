package design.iterator;

public class EvenIterator implements CustomIterator<Integer>{
    private OddIterator oddIterator;

    public EvenIterator(OddIterator oddIterator) {
        this.oddIterator = oddIterator; // Start with the first odd number
    }

    @Override
    public boolean hasNext() {
        return true; // Infinite stream of even numbers
    }

    @Override
    public Integer next() {
       return oddIterator.next() + 1; // Even numbers are just oddNumber + 1
    }
}
