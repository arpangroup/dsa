package design.iterator;

import java.util.*;

public class ZigZagIteratorV1 {
    private Queue<Iterator<Integer>> iteratorQueue;

    // Constructor to initialize the Queue with the iterator of the list
    public ZigZagIteratorV1(List<List<Integer>> lists) {
        iteratorQueue = new LinkedList<>();
        for (List<Integer> list : lists) {
            if (!list.isEmpty()) {
                iteratorQueue.add(list.iterator());
            }
        }
    }

    // Returns true if more element is to iterate
    public boolean hasNext() {
        return !iteratorQueue.isEmpty();
    }

    // Returns the next element in the zigzag iteration
    public int next() {
        // Get the iterator from the front of the queue
        Iterator<Integer> currentIterator = iteratorQueue.poll();
        int result = currentIterator.next();

        if (currentIterator.hasNext()) {
            iteratorQueue.add(currentIterator);
        }

        return result;
    }
}
