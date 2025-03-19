package design.iterator;

import java.util.NoSuchElementException;

public class RangeIterator implements CustomIterator<Integer>{
    private int current;
    private final int end;

    public RangeIterator(int start, int end) {
        this.current = start;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return current <= end;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return current++;
    }
}
