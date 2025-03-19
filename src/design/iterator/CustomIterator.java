package design.iterator;

import java.util.Iterator;

public interface CustomIterator<T> extends Iterator<T> {

    @Override
    boolean hasNext();

    @Override
    T next();
}
