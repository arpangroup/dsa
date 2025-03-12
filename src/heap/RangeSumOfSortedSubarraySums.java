package heap;

import java.util.Arrays;

/*
    LeetCode       : 1508
    Leetcode Link  : https://leetcode.com/problems/range-sum-of-sorted-subarray-sums
 */
public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        //return rangeSumV1__bruteForce(nums, n, left, right); // TC: O(n^2 logn) ; SC: O(n^2)
        return rangeSumV2_minHeap(nums, n, left, right);       // TC: O(n^2 logn) ; SC: O(n)
    }

    // Approach1: Brute Force: Generate all subsets and store in a array + Sorting
    // TC: O(n^2) + O(n^2 log n^2) + O(right-left) ==> O(n^2 + n^2 logn)
    // SC: O(n * (n+1)/) ==> O(n^2)
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

    // Approach2: Use a min heap to get the smallest number first(non-decreasing)
    // TC: O(n) + O(n^2 logn) ==> O(n^2 logn)
    // SC: O(n) for PQ
    public int rangeSumV2_minHeap(int[] nums, int n, int left, int right) {
        int MOD = 1_000_000_007;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]); // [num, index]

        // add all the elements in nums with (num, index) pair
        for(int i=0; i< nums.length; i++) {
            pq.offer(new int[]{nums[i], i}); // O(n) using heapify internally in one go
        }

        int count = 0;
        int sum = 0;
        // size of PQ will never increase more than N, because we are adding(offer) a new sum after
        // removing(poll) its previous ==> it maintains the size as N
        // O(n^2) <----because for total subarray = n(n+1)/2 ==> O(n^2) time operations (poll.offer)
        // Total Time: O(n^2) * O(logn + logn) ==> O(n^2 logn)
        while(!pq.isEmpty() && count < right) { // <-------O(n^2)
            // peek the ith element and try to expand
            int[] pair = pq.poll(); //<---------O(log(sizeOfPQ))
            int num = pair[0];
            int idx = pair[1];
            count++;

            // Add to final sum if within range
            if(count >= left){
                sum = (sum + num) % MOD ;
            }

            // Expand by adding the next element in sequence
            if(idx + 1 < n) {
                pq.offer(new int[]{num + nums[idx+1], idx+1}); //<---------O(log((sizeOfPQ)))
            }
        }
        return sum;
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
