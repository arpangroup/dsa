package design.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class CycleIterator<T> implements CustomIterator<T>{
    private final Supplier<Iterator<T>> iteratorSupplier;
    private Iterator<T> currentIterator;

    public CycleIterator(Supplier<Iterator<T>> iteratorSupplier) {
        this.iteratorSupplier = iteratorSupplier;
        this.currentIterator = iteratorSupplier.get(); // Get initial iterator
    }

    @Override
    public boolean hasNext() {
        if (!currentIterator.hasNext()) {
            currentIterator = iteratorSupplier.get(); // Reset iterator when finished
        }
        return currentIterator.hasNext();
    }

    @Override
    public T next() {
       if (!hasNext()) throw new NoSuchElementException("Iterator has no more elements");
       return currentIterator.next();
    }
}
