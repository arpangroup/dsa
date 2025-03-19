package design.iterator;

import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements CustomIterator<T> {
    private List<T> list;
    private int currentIndex;

    public BackwardIterator(List<T> list) {
        this.list = list;
        this.currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return list.get(currentIndex--);
    }
}
