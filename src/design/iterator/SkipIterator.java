package design.iterator;

import java.util.NoSuchElementException;

public class SkipIterator<T> implements CustomIterator<T>{
    private final CustomIterator<T> baseIterator;
    private final int skip;

    public SkipIterator(CustomIterator<T> baseIterator, int skip) {
        this.baseIterator = baseIterator;
        this.skip = skip;
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public T next() {
        /*for (int i = 0; i < skip; i++) {
            if (baseIterator.hasNext()) {
                baseIterator.next();
            } else {
                throw new NoSuchElementException();
            }
        }
        return baseIterator.next();*/

        if (!hasNext()) throw new NoSuchElementException();
        T result = baseIterator.next(); // Fetch the next valid element

        // Skip the next (skip - 1) elements
        for (int i = 1; i < skip && baseIterator.hasNext(); i++) {
            baseIterator.next();
        }

        return result;
    }
}
