package heap;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 846
    Leetcode Link               : https://leetcode.com/problems/hand-of-straights/
*/
public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        //return isNStraightHandV1__treeMap(hand, groupSize); // O(N log N)<-----Better than MinHeap
        return isNStraightHandV2__minHeap(hand, groupSize);   // O(N log N)
    }


    /*Approach1:
        1. Initial Check: If the total number of cards in hand is not a multiple of groupSize, return false immediately as it is impossible to partition the cards into groups of the desired size.
        2. Frequency Count: Use a hashmap to count the occurrences of each card.
        3. Sorting: Extract the unique cards from the hashmap and sort them.
        4. Group Formation: Iterate through the sorted unique cards. For each card, if its frequency is greater than zero, attempt to form a group starting from this card and extending for groupSize consecutive cards. If at any point, a required card is not available in the needed quantity, return false.
        5. Frequency Update: Decrease the frequency of cards used to form each group.

     TC:
        Sorting (TreeMap insertions): O(NlogN)
        Processing elements (Each element handled once): O(N)
        Final Complexity: O(NlogN)
    */
    public boolean isNStraightHandV1__treeMap(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0)return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for(int val : hand) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        while(!freqMap.isEmpty()) {
            int first = freqMap.firstKey(); // Get smallest number
            for (int i = 0; i < groupSize; i++) {
                int curr = first + i;
                if (!freqMap.containsKey(curr)) return false; // Missing number in sequence

                // Decrement frequency
                freqMap.put(curr, freqMap.get(curr) - 1);
                if (freqMap.get(curr) == 0) freqMap.remove(curr); // Remove if count is 0
            }
        }
        return true;
    }

    /*Approach2:
        Building frequency map: O(N)
        Adding elements to Min Heap: O(NlogN)
        Processing elements in groups: O(NlogN)
        Final Complexity: O(NlogN)
    */
    public boolean isNStraightHandV2__minHeap(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0)return false;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int val : hand) freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b)-> a.getKey()-b.getKey()); // minHeap []
        pq.addAll(freqMap.entrySet());

        while(!pq.isEmpty()) {
            Map.Entry<Integer, Integer> first = pq.peek(); // Get the smallest number

            // Try to form a valid group
            for (int i = 0; i < groupSize; i++) {
                int curr = first.getKey() + i;
                if (!freqMap.containsKey(curr)) return false; // Missing number in sequence

                // Decrement frequency
                freqMap.put(curr, freqMap.get(curr) - 1);
                if (freqMap.get(curr) == 0) {
                    if (curr != pq.peek().getKey()) return false; // Ensure removal in order
                    pq.poll(); // Remove from heap
                    freqMap.remove(curr);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HandOfStraights solution = new HandOfStraights();
        int[] hand = new int[]{1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        System.out.println(solution.isNStraightHand(hand, groupSize)); // true
        hand = new int[]{1,2,3,4,5};
        groupSize = 4;
        System.out.println(solution.isNStraightHand(hand, groupSize)); // false
    }
}
