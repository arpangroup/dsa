package recursion;

import base.MutableInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

    Company Tags                :
    LeetCode                    : 1498. Number of Subsequences That Satisfy the Given Sum Condition
    Leetcode Link               : https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
    Description: Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target.
    Input: nums = [3,5,6,7], target = 9
    Output: 4
    Explanation: There are 4 subsequences that satisfy the condition.
        [3] -> Min value + max value <= target (3 + 3 <= 9)
        [3,5] -> (3 + 5 <= 9)
        [3,5,6] -> (3 + 6 <= 9)
        [3,6] -> (3 + 6 <= 9)
*/
public class NoOfSubSequencesSatisfyGivenSumCondition {
    private static final int MOD = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {

        /*
        // Generate all possible Subsets ==> Using Recursion ==> BruteForce ==> O(2^N)
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, target, target, new ArrayList<>(), result);
        System.out.println("SUBSEQUENCES: " + result);
        return result.size(); // as we will not consider empty subset
        */

        return numSubseq__2Pointer(nums, target); // O(n log n)
    }

    private void backtrack(int[] arr, int idx, int remain, int target, List<Integer> curr, List<List<Integer>> result) {
        if (idx >= arr.length) {
            if (curr.isEmpty()) return;
            int min = curr.getFirst();
            int max = curr.getLast();
            int sum = (min + max) % MOD;
            if (sum <= target) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }

        // take current element
        curr.add(arr[idx]);
        backtrack(arr, idx + 1, remain - arr[idx], target, curr, result);

        // not-take current element
        curr.removeLast();
        backtrack(arr, idx + 1, remain, target, curr, result);
    }

    /* Using 2-pointer approach
        TC:
            1. Sort Array               : O(n log n)
            2. Precomputing powers of 2 : O(n)
            3. Two-pointer traversal    : O(n)
            ------------------------------------------
                                   Total: O(n log n)
     */
    private int numSubseq__2Pointer(int[] arr, int target) {
        Arrays.sort(arr);

        // As pow() gives TLE, pre-compute power
        int[] power = new int[arr.length];
        power[0] = 1; // as 2^0 =1
        for(int i=1; i < power.length; i++) {
            power[i] = (power[i-1] * 2) % MOD;
        }

        int count = 0;
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int sum = arr[left] + arr[right];
            if(sum <= target) {
                count = (count + power[right - left]) % MOD;
                left ++;
            } else {
                right --;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NoOfSubSequencesSatisfyGivenSumCondition solution = new NoOfSubSequencesSatisfyGivenSumCondition();
        System.out.println(solution.numSubseq(new int[]{3,5,6,7}, 9)); // 4
        System.out.println(solution.numSubseq(new int[]{3,3,6,8}, 10)); // 6
        System.out.println(solution.numSubseq(new int[]{2,3,3,4,6,7}, 12)); // 61
        System.out.println(solution.numSubseq(new int[]{14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14}, 22)); // 272187084
    }
}
