package design.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        // 1. Odd Iterator example:
        OddIterator oddIterator = new OddIterator();
        System.out.println("First 5 Odd Numbers:");
        for (int i = 0; i < 5; i++) {
            System.out.print(oddIterator.next() + " ");
        }
        System.out.println();


        // 2. Even Iterator example:
        EvenIterator evenIterator = new EvenIterator(oddIterator);
        System.out.println("First 5 Even Numbers:");
        for (int i = 0; i < 5; i++) {
            System.out.print(evenIterator.next() + " ");
        }
        System.out.println();

        // 3.Negative Iterator example (based on OddIterator)
        NegativeIterator negativeIterator = new NegativeIterator(new OddIterator());
        System.out.println("First 5 Negative Odd Numbers:");
        for (int i = 0; i < 5; i++) {
            System.out.print(negativeIterator.next() + " ");
        }
        System.out.println();

        // 4.Interleaving Iterator example
        OddIterator odd = new OddIterator();
        EvenIterator even = new EvenIterator(new OddIterator());
        InterleavingIterator<Integer> interleavingIterator = new InterleavingIterator(List.of(odd, even));
        System.out.println("First 10 Interleaved Odd/Even Numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.print(interleavingIterator.next() + " ");
        }
        System.out.println();

        // 5.Limit Iterator example
        LimitIterator<Integer> limitIterator = new LimitIterator(new OddIterator(), 5);
        System.out.println("First 5 odd numbers using limitIterator:");
        while(limitIterator.hasNext()) {
            System.out.print(limitIterator.next() + " ");
        }
        System.out.println();

        // 6.Forward Iterator example
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        ForwardIterator<Integer> forwardIterator = new ForwardIterator<>(list);
        System.out.println("Forward iteration over lists:");
        while(forwardIterator.hasNext()) {
            System.out.print(forwardIterator.next() + " ");
        }
        System.out.println();

        // 7. Backward Iterator example
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);
        System.out.println("Backward iteration over lists:");
        while(backwardIterator.hasNext()) {
            System.out.print(backwardIterator.next() + " ");
        }
        System.out.println();

        // 8. Range Iterator example
        RangeIterator rangeIterator = new RangeIterator(10, 15);
        System.out.println("Iterating over range 10 to 15:");
        while(rangeIterator.hasNext()) {
            System.out.print(rangeIterator.next() + " ");
        }
        System.out.println();

        // 9. Skip Iterator example
        SkipIterator<Integer> skipIterator = new SkipIterator<>(new RangeIterator(1, 20), 3);
        System.out.println("Skipping every 2nd element in range 1 to 20:");
        while(skipIterator.hasNext()) {
            System.out.print(skipIterator.next() + " ");
        }
        System.out.println();

        // 10. Cycle Iterator example
        Supplier<Iterator<Integer>> rangeSupplier = () -> new RangeIterator(0, 4);
        CycleIterator<Integer> cycleIterator = new CycleIterator<>(rangeSupplier);
        System.out.println("Output the first 10 elements in cycle:");
        for (int i = 0; i < 10; i++) {
            System.out.print(cycleIterator.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

}
