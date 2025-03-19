package design.iterator;

import java.util.NoSuchElementException;

public class LimitIterator<T> implements CustomIterator<T> {
    private CustomIterator<T> baseIterator;
    private int limit;
    private int count;

    public LimitIterator(CustomIterator<T> baseIterator, int limit) {
        this.baseIterator = baseIterator;
        this.limit = limit;
        this.count = 0;
    }

    @Override
    public boolean hasNext() {
        return count < limit && baseIterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        count++;
        return baseIterator.next();
    }
}
