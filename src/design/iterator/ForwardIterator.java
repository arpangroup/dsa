package design.iterator;

import java.util.List;
import java.util.NoSuchElementException;

public class ForwardIterator<T> implements CustomIterator<T> {
    private List<T> list;
    private int currentIndex;

    public ForwardIterator(List<T> list) {
        this.list = list;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return list.get(currentIndex++);
    }
}
