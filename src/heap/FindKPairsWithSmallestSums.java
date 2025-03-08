package heap;

import base.Pair;

import java.util.*;

/**
 * LeetCode-373
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //return kSmallestPairsV1__bruteForce(nums1, nums2, k);
        //return kSmallestPairsV2__optimal(nums1, nums2, k);
        return kSmallestPairsV3__minHeap_and_bfs(nums1, nums2, k);
    }

    // Approach-1: Complete Brute Force using MinHeap - TIME COMPLEXITY < O(m*n*log(k)) - ACCEPTED
    public List<List<Integer>> kSmallestPairsV1__bruteForce(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) -> ((int)b.first + (int)b.second) - ((int)a.first + (int)a.second) // Max-Heap: Larger sums come first
        );

        for(int i=0; i< nums1.length; i++) {
            for(int j=0; j< nums2.length; j++) {
                //maxHeap.offer(new Pair(nums1[i], nums2[j]));;

                int sum = nums1[i] + nums2[j];

                if (maxHeap.size() < k) {
                    maxHeap.offer(new Pair(nums1[i], nums2[j]));
                } else if (sum < ((int)maxHeap.peek().first + (int)maxHeap.peek().second)) {
                    maxHeap.poll(); // Remove the largest sum
                    maxHeap.offer(new Pair(nums1[i], nums2[j]));
                } else {
                    break; // No need to check further (since nums2 is sorted)
                }
            }
        }

        while (!maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();
            result.add(List.of((int)pair.first, (int)pair.second));
        }
        return result;
    }

    // Approach-2 (Slight Better approach) - O(klog(k))
    public List<List<Integer>> kSmallestPairsV2__optimal(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> minHeap = new PriorityQueue<>((a, b) -> b.first - a.first);
        Set<Pair<Integer, Integer>> visited = new HashSet<>();


        // Add the first pair (0,0)
        int sum = nums1[0] + nums2[0];
        minHeap.offer(new Pair<>(sum, new Pair<>(0, 0))); // arr1[0] + arr2[0] --> will be the most smaller element as arrays are sorted
        visited.add(new Pair<>(0, 0));

        while (k-- > 0 && !minHeap.isEmpty()) { // O(K * log(m*n)) or O(K * logK)
            Pair<Integer, Pair<Integer, Integer>> temp = minHeap.poll();
            int i = temp.second.first;
            int j = temp.second.second;
            result.add(List.of(nums1[i], nums2[j]));

            // Push (i, j+1) if within bounds and not visited
            if (j+1 < nums2.length && !visited.contains(new Pair<>(i, j+1))) {
                minHeap.offer(new Pair<>(nums1[i] + nums2[j+1], new Pair<>(i, j+1)));
                visited.add(new Pair<>(i, j+1));
            }


            // Push (i+1, j) if within bounds and not visited
            if (i+1 < nums1.length && !visited.contains(new Pair<>(i+1, j))) {
                minHeap.offer(new Pair<>(nums1[i+1] + nums2[j], new Pair<>(i+1, j)));
                visited.add(new Pair<>(i+1, j));
            }
        }
        return result;
    }

    // Approach-3 without Set : O(klogk) since at most k elements are extracted from the heap.
    public List<List<Integer>> kSmallestPairsV3__minHeap_and_bfs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );

        // Initialize heap with (nums1[i], nums2[0]) for first k elements of nums1
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{i, 0});
        }

        while (!minHeap.isEmpty() && k-- > 0) {
            int[] idx = minHeap.poll();
            int i = idx[0], j = idx[1];
            result.add(List.of(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindKPairsWithSmallestSums solution = new FindKPairsWithSmallestSums();
        int[] nums1 = new int[]{1,7,11};
        int[] nums2 = new int[]{2,4,6};
        int k = 3;
        int[][] output1 = new int[][]{{1,2},{1,4},{1,6}};
        System.out.println(solution.kSmallestPairs(nums1, nums2, k)); // [[1,2],[1,4],[1,6]]

        nums1 = new int[]{1,1,2};
        nums2 = new int[]{1,2,3};
        k = 2;
        int[][] output2 = new int[][]{{1,1},{1,1}};
        System.out.println(solution.kSmallestPairs(nums1, nums2, k)); // [[1,1],[1,1]]
    }
}
