package recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursion_01_PrintN {

    public void printNumbersAsc(int n) { // 1, 2, 3, 4, 5
        if(n < 1) return;
        printNumbersAsc(n-1);
        System.out.println(n);
    }

    public void printNumbersDesc(int n) { // 5, 4, 3, 2, 1
        if(n < 1) return;
        System.out.println(n);
        printNumbersDesc(n-1);
    }

    public List<Integer> getNumbersAsc(int n) {
        if(n < 1) return new ArrayList<>();
        List<Integer> list = getNumbersAsc(n-1);
        list.addFirst(n);
        return list;
    }

    public void getNumbersDec(int n, List<Integer> list) {
        if(n < 1) return;
        list.addLast(n);
        getNumbersDec(n-1, list);
    }

    public static void main(String[] args) {
//        new PrintN().printNumbersAsc(5);

        List<Integer> list = new ArrayList<>();
        new Recursion_01_PrintN().getNumbersDec(5, list);
        System.out.println(list);
    }
}
