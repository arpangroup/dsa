package recursion;

public class Power {
    private static final double mod = 1e9; //1000000007

    public double myPow(double x, int n) {
        //System.out.println("EXP: " + n);
        if(n == 0) return 1;

         // Special case for Integer.MIN_VALUE, since -Integer.MIN_VALUE overflows
//         if (n == Integer.MIN_VALUE) {
//             return myPow(x, Integer.MAX_VALUE) / x;  // equivalent to x^(-2147483648) = (x^(2147483647)) / x
//         }

        // Use long to handle negative exponents safely, we dont need to handle above special case
//        int N = (n < 0) ? -n : n; // important, otherwise it will pass -ve to myPow() in next line, and it will cause int overflow
        int N = n;
        System.out.println("EXP: " + N);

        double ans = myPow(x, N / 2);
        ans = ans * ans; // we cant use modulo on floating point number in java like: (ans * ans) % mod;

        if (N % 2 != 0) ans *= x;  // If n is odd, multiply by x
        if (n < 0) ans = 1 / ans;

        return ans;
    }

    public double myPowV1(double x, long n) {
        if(n == 0) return 1;

        long N = n;  // Use long to handle negative exponents safely

        if (n < 0) {
            N = -N;  // Take absolute value
        }

        double ans = 1;
        while (N > 0) {
            if (N % 2 != 0) {  // If N is odd
                ans *= x;
            }
            x *= x;  // Square x for each iteration
            N /= 2;  // Halve N
        }

        return (n < 0) ? 1 / ans : ans;  // If n is negative, return 1 / ans
    }

    public static void main(String[] args) {
        // #########################################################################
        // ############# Do not change the Input ####################################
        double[] input   = new double[]{2.00000,   2.10000, 2.00000, 2.00000};
        int[] exp        = new int[]{   10,        3,       -2,      -2147483648};
        double[] outputs = new double[]{1024.0000, 9.26100, 0.25000, 0.00000};
        // #########################################################################
        // #########################################################################

       /* for (int i = 0; i< input.length; i++) {
            if (i == 2) continue;
            System.out.println("RESULT: " + new Power().myPow(input[i], exp[i]));
        }*/
        System.out.println("RESULT: " + new Power().myPow(input[3], exp[3]));





    }
}
