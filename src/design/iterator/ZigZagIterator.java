package design.iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZagIterator {
    List<List<Integer>> combinedList;
    Deque<Pair> deque;

    public ZigZagIterator(List<Integer> l1, List<Integer> l2) {
        combinedList = new ArrayList<>();
        deque = new ArrayDeque<>();
        combinedList.add(l1);
        combinedList.add(l2);

        for (int i=0; i< combinedList.size(); i++) {
            if (combinedList.get(i).size() > 0) {
                deque.add(new Pair(i, 0));
            }
        }
    }

    public int next() {
        Pair pair = deque.pollFirst();
        List<Integer> curList = combinedList.get(pair.index);
        int res = curList.get(pair.offset);
        if (pair.offset + 1 < curList.size()) {
            deque.addLast(new Pair(pair.index, pair.offset + 1));
        }
        return res;
    }

    public boolean hasNext() {
        return deque.size() > 0;
    }
}
