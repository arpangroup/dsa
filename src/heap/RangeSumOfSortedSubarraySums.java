package heap;

import java.util.Arrays;

/*
    LeetCode       : 1508
    Leetcode Link  : https://leetcode.com/problems/range-sum-of-sorted-subarray-sums
 */
public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        return rangeSumV1__bruteForce(nums, n, left, right);
    }

    // Brute Force: O(n^2) + O(n^2 log n^2) + O(right-left) ==> O(n^2 + n^2 logn)
    public int rangeSumV1__bruteForce(int[] nums, int n, int left, int right) {
        int[] arr = new int[n * (n+1)/2];
        int idx = -1;
        int mod = 1_000_000_007;

        for(int i=0; i< nums.length; i++) {
            int sum = 0;
            for(int j= i; j< nums.length; j++) {
                sum = (sum + nums[j]) % mod;
                arr[++idx] = sum;
            }
        }

        Arrays.sort(arr);
        int sum = 0;
        for(int i= left-1; i< right; i++) {
            sum = (sum + arr[i]) % mod;
        }
        return sum;
    }

    public int rangeSumV2(int[] nums, int n, int left, int right) {
        return 0;
    }

    public static void main(String[] args) {
        RangeSumOfSortedSubarraySums solution = new RangeSumOfSortedSubarraySums();
        int[] nums = new int[] {1,2,3,4};
        int n = 4;
        int left= 1;
        int right = 5;
        System.out.println(solution.rangeSum(nums, n, left, right)); // 13

        nums = new int[] {1,2,3,4};
        n = 4;
        left= 3;
        right = 4;
        System.out.println(solution.rangeSum(nums, n, left, right)); // 6

        nums = new int[] {1,2,3,4};
        n = 4;
        left= 1;
        right = 10;
        System.out.println(solution.rangeSum(nums, n, left, right)); // 30
    }
}
