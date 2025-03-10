package heap;

/*
    LeetCode       : 1508
    Leetcode Link  : https://leetcode.com/problems/range-sum-of-sorted-subarray-sums
 */
public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
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
