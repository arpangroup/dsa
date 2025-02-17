package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortList {
    public void sort(List<Integer> list) {
        doSort(list);
    }

    private void doSort(List<Integer> list) {
        if (list.size() <= 1) return;
        int last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        doSort(list);
        merge(list, last);
    }

    private void merge(List<Integer> list, int element) {
        if (list.isEmpty() || list.get(list.size() - 1) <= element) {
            list.add(element);
            return;
        }

        int last = list.remove(list.size() - 1); // Remove last element
        merge(list, element);
        list.add(last); // Reinsert last element at correct position
    }

    public static void main(String[] args) {
        /**
         * Arrays.asList() returns a fixed-size list, which does not allow remove() or add() operations.
         * When list.remove(list.size() - 1); is called inside doSort(), it throws UnsupportedOperationException.
         */
        //List<Integer> list = Arrays.asList(0, 1, 5, 2); // Arrays.asList() returns a fixed-size list, which does not allow remove() or add() operations.
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 5, 2));

        new SortList().sort(list);
        System.out.println(list);
    }
}
