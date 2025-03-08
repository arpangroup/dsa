package heap;

import java.util.*;

/**
 * LeetCode-347
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        //return topKFrequentV1__usingMapAndHeap(nums, k);
        return topKFrequentV2__usingBucketSort(nums, k);
    }

    public int[] topKFrequentV1__usingMapAndHeap(int[] nums, int k) { // O(n) + O(n) + o(klogn)
        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int val : nums) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1); // O(n)

        // Step 2: Use a max heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(freqMap.entrySet()); // O(n)

        // Step 3: Extract the top K elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) { // O(k)
            result[i] = maxHeap.poll().getKey(); // log(n)
        }

        return result;
    }

    public int[] topKFrequentV2__usingBucketSort(int[] nums, int k) {
        /*
            nums: [1,1,1,2,2,100]
            i->count-> | 0     |   1   |   2   |   3   |   4   |    5  |    6  |
                       |-------+-------+-------+-------+-------+-------+-------|
            values->   |       | [100] |  [2]  |  [1]  |       |       |       |
                       |-------+-------+-------+-------+-------+-------+-------|
         */

        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        // Step 2: Bucket sort (array of lists)
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        // Step 3: Collect top K frequent elements
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[][] input = new int[][]{
                {1,1,1,2,2,3},
                {1}
        };
        int[] k = new int[]{
            2,
            1
        };
        int[][] output = new int[][]{
                {1,2},
                {1}
        };

        for (int i=0; i< input.length; i++) {
            System.out.println(Arrays.toString(solution.topKFrequent(input[i], k[i])));
        }
    }
}
