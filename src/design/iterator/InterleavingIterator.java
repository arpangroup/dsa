package design.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class InterleavingIterator<T> implements CustomIterator<T> {
    private List<Iterator<T>> iterators;
    private int currentIndex;

    public InterleavingIterator(List<Iterator<T>> iterators) {
        this.iterators = new LinkedList<>(iterators);
        this.currentIndex = 0;
    }


    @Override
    public boolean hasNext() {
        // Remove exhausted iterators
        iterators.removeIf(it -> !it.hasNext());
        return !iterators.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        CustomIterator<T> currentIterator = (CustomIterator<T>) iterators.get(currentIndex);
        T result = currentIterator.next();
        currentIndex = (currentIndex + 1) % iterators.size();
        return result;
    }
}
