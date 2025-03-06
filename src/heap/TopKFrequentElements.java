package heap;

import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int val : nums) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        // Step 2: Use a max heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Extract the top K elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
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
