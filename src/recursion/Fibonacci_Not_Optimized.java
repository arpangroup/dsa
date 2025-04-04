package recursion;

public class Fibonacci_Not_Optimized {
    public long fib(int n) {
        if (n == 0 || n == 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci_Not_Optimized solution = new Fibonacci_Not_Optimized();

        for (int i=0; i < 7; i++) {
            System.out.print(solution.fib(i) + ", ");
        }
    }
}
