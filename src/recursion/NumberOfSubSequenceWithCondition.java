package recursion;

import java.util.*;

// LeetCode: 1498
// https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition */
public class NumberOfSubSequenceWithCondition {
    private static final int MOD = 1_000_000_007;
    //private List<List<Integer>> output = new ArrayList<>();

    public int numSubseqUsingRecursion(int[] nums, int target) {
        Arrays.sort(nums);
        MutableInteger result = new MutableInteger(0);
        backtrack(nums, 0,  target, new ArrayList<>(), result);
        return result.value;
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);  // Sort the array
        int n = nums.length;
        int[] power = new int[n];
        power[0] = 1;

        // Precompute powers of 2 modulo MOD
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        int left = 0, right = n - 1;
        int count = 0;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + power[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    private void backtrack(int[] arr, int index, int target, List<Integer> current, MutableInteger result) {
        if(!current.isEmpty()) {
            int minVal = current.get(0);
            int maxVal = current.get(current.size() - 1);
            int sum = (minVal + maxVal) % MOD;

            if (sum > target) {
                return; // Prune recursion if sum exceeds target
            }

            //output.add(new ArrayList<>(current));
            result.value = (result.value + 1) % MOD;
            //System.out.println("OUTPUT: " + current);

        }

        if (index >= arr.length) return;

        /*
        // take current element and move on
        current.add(arr[index]);
        backtrack(arr, index + 1, target, current, result);
        current.remove(current.size() - 1);


        // not take current element and move on
        while(index + 1 < arr.length && arr[index+1] == arr[index]) index++;
        backtrack(arr, index + 1, target, current, result);
        */

        for (int i = index; i < arr.length; i++) {
            // Allow duplicates by **not skipping** elements
            current.add(arr[i]);
            backtrack(arr, i + 1, target, current, result);
            current.remove(current.size() - 1);
        }
    }

    private int powerOfTwo(int exp) {
        long result = 1, base = 2;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) result;
    }

    static class MutableInteger {
        public int value;
        public MutableInteger(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NumberOfSubSequenceWithCondition solution = new NumberOfSubSequenceWithCondition();
        int[][] nums = new int[][] {
                {3,5,6,7},      // target: 9
                {3,3,6,8},      //target: 10
                {2,3,3,4,6,7},  // target: 61
                {14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14} // target: 22
        };

        int[] targets = new int[]{
                9,
                10,
                12,
                22
        };

        int[] expectedOutputs = new int[]{
                4,
                6,
                61,
                272187084
        };


        for (int i=0; i< nums.length; i++) {
            //if (i == 3) continue; // TLE for Recursive solution
            System.out.println("Result"+ i + ": " + solution.numSubseq(nums[i], targets[i]));
        }
    }

}
