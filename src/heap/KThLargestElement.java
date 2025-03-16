package heap;

import java.util.*;

/**
 * LeetCode-215
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KThLargestElement {
    public int findKthLargest(int[] nums, int k) {
        //return findKthLargestV1__sorting(nums, k);                    // O(nlogn)
        //return findKthLargestV2__maxHeap(nums, k);                    // O(n + klogn)
        //return findKthLargestV3__minHeap_stepDownApproach(nums, k);   // O(nlogk)
        //return findKthLargestV4__bucketSort(nums, k);                 // O(n); Space: O(n)
        return findKthLargestV4__quickSelect(nums, 0, nums.length - 1, nums.length - k);  // Avg: O(n); Worst: O(n^2)
    }

    public int findKthLargestV1__sorting(int[] nums, int k) { // O(nlogn)
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestV2__maxHeap(int[] nums, int k) { // O(n) + O(klogn)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a); // Max Heap

        for(int num : nums) pq.offer(num); // O(n)

        int largest = nums[0];
        for(int i=0; i< k ; i++) {  // O(k)
            largest = pq.poll(); // O(logn)
        }
        return largest;
    }

    public int findKthLargestV3__minHeap_stepDownApproach(int[] nums, int k) { // O(n) + O(klogn)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a-b); // min Heap

        // insert first k elements
        for (int i=0; i< k; i++) { // O(k)
            minHeap.add(nums[i]);
        }

        // Process remaining elements
        for (int i=k; i< nums.length; i++) { // If larger than min, replace
            if (nums[i] > minHeap.peek()) {
                minHeap.poll(); // Remove the smallest element <===O(logK)
                minHeap.offer(nums[i]); // Insert the new element
            }
        }
        return minHeap.poll();
    }

    public int findKthLargestV4__bucketSort(int[] nums, int k) { // Avg: O(n); Worst: O(n^2)
        int max = Arrays.stream(nums).max().getAsInt(); // Find max element

        // Create buckets (frequency array)
        int[] bucket = new int[max + 1];

        // Fill the bucket with frequencies
        for (int num : nums) bucket[num]++;

        // Traverse bucket from right (largest elements) to left
        int count = 0;
        for (int i = max; i >= 0; i--) {
            count += bucket[i];
            if (count >= k) {
                return i; // Found kth largest element
            }
        }
        return -1; // Should never reach here
    }

    public int findKthLargestV4__quickSelect(int[] arr, int left, int right, int k) { // Avg: O(n); Worst: O(n^2)
        int pivot = arr[right];
        int p = left;

        for (int i=left; i< right; i++) {
            if (arr[i] < pivot){
                swap(arr, i, p);
                p ++;
            }
        }
        swap(arr, right, p); // Move pivot to correct position

        if (p > k) return findKthLargestV4__quickSelect(arr, left, p - 1, k); // Search left
        if (p < k) return findKthLargestV4__quickSelect(arr, p + 1, right, k); // Search right
        return arr[p]; // Found kth largest element
    }

    private void swap(int[]arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        KThLargestElement solution = new KThLargestElement();
        int[][] input = new int[][]{
                {3,2,1,5,6,4},
                {3,2,3,1,2,4,5,5,6}
        };
        int[] k = new int[]{
                2,
                4
        };
        int[] output = new int[]{
                5,
                4
        };

        for (int i=0; i< input.length; i++) {
            System.out.println(solution.findKthLargest(input[i], k[i]));
        }
    }
}
