package recursion;

/*
    Company Tags                :
    LeetCode                    : 1922
    Leetcode Link               : https://leetcode.com/problems/count-good-numbers/description/
*/
public class CountGoodNumbers {
    private static final int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        return countGoodNumbers__bruteforce(n); // O()
        //return countGoodNumbers__optimized(n); // O(N)
    }

    /* Below code doesn't work
            - Actual # of GoodNumber = 16 (22, 23, 25, 27, 42, 43, 45, 47, 62, 63, 65, 67, 82, 83, 85, 87)
            - But LeetCode answer is expecting 20.
            - Because  "02, 03, 05, 07" are also valid 2 digit string
        02, 03, 05, 07,
        22, 23, 25, 27,
        42, 43, 45, 47,
        62, 63, 65, 67,
        82, 83, 85, 87

        FIX:  digitString = String.format("%0" + n + "d", num); // Ensure correct length with leading zeros
        OR,
        for(long num = start; num <= end; num++) {
            long temp = num;
            for (int i = (int) n - 1; i >= 0; i--) { // extract digits from the end
                int digit = (int) (temp % 10);
                temp /= 10;
            }
        }


     */
    public int countGoodNumbers__bruteforce(long n) {
        if(n == 1) return 5;

        int count = 0;
        //long start = (long) Math.pow(10, n - 1);
        long start = 0;
        long end = (long) Math.pow(10, n) - 1;

        for(long num = start; num <= end; num++) { // iterate through each number in range
            boolean isValid = true;
            //String digitString = num + "";
            String digitString = String.format("%0" + n + "d", num); // Ensure correct length with leading zeros
            //System.out.println("DIGIT_STRING........: " + digitString);

            for(int i = 0; i < n; i++){ //iterate each digit in digitString
                int digit = digitString.charAt(i) - '0';
                //System.out.println("DIGIT_" + i + ": " + digit);

                if(i % 2 == 0 && digit % 2 != 0) { // even position but NOT evenNumber
                    isValid = false;
                    break;
                }
                if(i % 2 == 1 && !isPrime(digit)) { // odd position but NOT prime
                    isValid = false;
                    break;
                }
            }
            if(isValid) {
                count++;
                //System.out.println("GOOD_NUMBER: " + digitString + " == > " + count);
            }
        }
        return count;
    }

    private boolean isPrime(int number){
        if (number < 2) return false;
        if (number == 2) return true;  // 2 is the only even prime number
        for(int i=2 ; i<number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

    public int countGoodNumbers__optimized(long n) {
        // As n <= 10^15, so we cant iterate the the loop till such a big number n-1
        // We know for each even position possibility = 5 (0,2,4,6,8) and
        // for each odd position possibility = 4 (2,3,5,7)
        // lets calculate the number of even and odd position
        // let n=1, even = 1, odd=0
        // let n=2, even = 1, od=1
        // let n=3, even = 2, od=1
        // let n=4, even = 2, odd=2
        // let n=5, even = 3, odd=2

        long noOfEvenPosition = n/2;
        long noOfOddPosition = n/2;
        if (n % 2 == 1) noOfEvenPosition +=  1;

        long evenPossobilities = pow(5, noOfEvenPosition);
        long oddPossibilities  = pow(4, noOfOddPosition);

        return (int) ((evenPossobilities * oddPossibilities) % MOD);

    }

    private static long pow(long  base, long exp) {
        if(exp == 0) return 1;

        long ans = pow(base, exp/2);
        ans = (ans * ans) % MOD;

        if ( exp % 2 == 1) {
            ans = (ans * (base % MOD)) % MOD;
        }

        return ans;
    }

    public static void main(String[] args) {
        CountGoodNumbers solution = new CountGoodNumbers();
        int[] inputs = new int[]{
                1,
                2,
                3,
                4,
                5,
                50
        };
        int[] outputs = new int[]{
                5,          // n = 1
                20,         // n = 2
                100,        // n = 3
                400,        // n = 4
                2000,       // n = 5
                564908303   // n = 50
        };
        for (int n : inputs) {
//            if (n != 2) continue;
            System.out.println(solution.countGoodNumbers(n));
        }
    }
}
