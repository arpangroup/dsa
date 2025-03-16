package heap;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 692
    Leetcode Link               : https://leetcode.com/problems/top-k-frequent-words/
*/
public class TopKFrequentWords {
    // NOTE: Sorting-based approach is faster when k is large, since O(N log N) is better than O(N log k) when k ≈ N.
    // NOTE: Use the Min-Heap method when k is very small compared to N (e.g., k << N). Otherwise, sorting is more efficient.
    public List<String> topKFrequent(String[] words, int k) {
        //return topKFrequent_maxHeap(words, k);  // O(NlogN); SC: O(N)
        //return topKFrequent_minHeap(words, k);    // O(NlogK); SC: O(M+K)
        return topKFrequent_bucketSort(words, k); // O(N log N)
    }

    /*Approach1: Using MinHeap*/
    public List<String> topKFrequent_maxHeap(String[] words, int k) {
        // Step 1: Count frequencies
        Map<String, Integer> freqMap = new HashMap<>();
        for (String val : words) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        // Step 2: Use a max heap based on frequency
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> {
            if (b.getValue() == a.getValue()) return a.getKey().compareTo(b.getKey()); // if same frequency check lexicographical order
            return b.getValue() - a.getValue();
        });
        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Extract the top K elements
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(maxHeap.poll().getKey());
        }

        return result;
    }

    /* Approach2: Using MinHeap
       TC:  O(N): Count Frequencies
            O(M log k): Insert into Min-Heap
                -  There are at most M unique words (size of freqMap).
                - Each insertion into the heap takes O(log k) time.
                - Since we keep the heap size at most k, at most M insertions and removals happen.
            O(k log k): Extract Elements
            O(K): Collections.reverse(result) takes O(k).
        Total: O(N) + O(M log k) + O(k log k) + O(k)
               ==> O(N log k) + O(k log k) ≈ O(N log k)   [as M ≤ N]
               ≈=> O(N log k)
    */
    public List<String> topKFrequent_minHeap(String[] words, int k) {
        // Step 1: Count frequencies
        Map<String, Integer> freqMap = new HashMap<>();
        for (String val : words) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1); // O(N)

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> {
            if(a.getValue() == b.getValue()) return b.getKey().compareTo(a.getKey());
            return a.getValue() - b.getValue();
        });

        // Step 3: Populate the heap
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }


        // Step 4: Extract elements in correct order
        List<String> result = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }

    /* Approach3: Using Bucket sort + sorting
        Count word frequencies → O(N)
        Sort words by frequency & lexicographical order → O(M log M), where M is the number of unique words
        Return the top k words → O(k)

        Total Complexity:
           O(N) + O(M log M) + O(k)
        ==>O(N) + O(N log N) [As in worst case (all words are unique)]
         = O(N log N)

         NOTE: Sorting-based approach is faster when k is large, since O(N log N) is better than O(N log k) when k ≈ N.
         NOTE: Use the Min-Heap method when k is very small compared to N (e.g., k << N). Otherwise, sorting is more efficient.
     */
    public List<String> topKFrequent_bucketSort(String[] words, int k) {
        // Step 1: Count word frequencies
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Sort words based on frequency, and lexicographically if same frequency
        List<String> wordList = new ArrayList<>(freqMap.keySet());
        wordList.sort((a, b) -> {
            if (freqMap.get(a).equals(freqMap.get(b))) return a.compareTo(b); // Lexicographical order
            return freqMap.get(b) - freqMap.get(a); // Descending frequency order
        });

        // Step 3: Return top k words
        return wordList.subList(0, k);
    }
}
