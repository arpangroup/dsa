package heap;

import java.util.*;

/*
    Company Tags                : will soon update
    LeetCode                    : 632
    Leetcode Link               : https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists
*/
public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        //return smallestRangeV1__BruteForce(nums);      // O(N^2 * KlogN)
        //return smallestRangeV2__using_K_pointer(nums); // O(NK)
        return smallestRangeV3__using_MinHeap(nums);     // O(N logK)
    }

    // Approach1: Bruteforce: TLE
    // Total Time Complexity:
    //      Flattening & Sorting: O(N log N)
    //      Checking all (i, j) pairs: O(N²)
    //      For each pair, checking if it covers all lists: O(K log N)
    //      total complexity is: O(N^2 * KlogN)+O(NlogN  ===> O(N^2 * KlogN)
    public int[] smallestRangeV1__BruteForce(List<List<Integer>> nums) {
        if(nums.size() == 1) return new int[]{nums.get(0).get(0), nums.get(0).get(0)};

        List<Integer> flattened = nums.stream()
                .flatMap(List::stream)
                .sorted() // sorted is need because after flatten we may get element like {24,20} which is incorrect, correct one is {20,24}
                .toList();

        // Check for smallest pair from all possible pair
        int[] smallestPair = new int[]{0, Integer.MAX_VALUE}; // important, dont initialize with {MAX_VALUE, MAX_VALUE}, the difference will be 0, so it will be the smallest
        for(int i=0; i< flattened.size(); i++) {
            for(int j=i+1; j< flattened.size(); j++) {
                int[] currPair = {flattened.get(i), flattened.get(j)};

                if(isCoveringAllLists(nums, currPair)) {
                    if(currPair[1] - currPair[0] < smallestPair[1] - smallestPair[0]) {
                        smallestPair = currPair;
                    }
                }
            }
        }
        return smallestPair;
    }

    private boolean isCoveringAllLists(List<List<Integer>> nums, int[] pair) {
        int start = pair[0], end = pair[1];

        for (List<Integer> list : nums) {
            int pos = Collections.binarySearch(list, start);
            if (pos < 0) pos = -pos - 1; // Convert insertion point to index

            // Ensure there is an element in the range [start, end]
            if (pos >= list.size() || list.get(pos) > end) return false;
        }
        return true;
    }



    /* Approach2:  Using K pointer to track min and max value: TLE
       TC:  (1) Outer While Loop (O(N))
                    - The loop runs until one of the lists is exhausted.
                    - In the worst case, we process every element once → O(N) iterations, where N is the total number of elements across all lists.
            (2) Finding Min & Max (O(K))
        Total TC: O(N×K)
        SC: O(K) (Only ptr[] array of size K is used)

    */
    public int[] smallestRangeV2__using_K_pointer(List<List<Integer>> nums) {
        int k = nums.size();
        int[] resultRange = {0, Integer.MAX_VALUE}; // Stores the smallest range
        int[] ptr = new int[k]; // Pointers for each list

        while (true) { //
            int[] minMax      = findMinMax(nums, ptr);
            int minVal        = minMax[0];
            int maxVal        = minMax[1];
            int minValListIdx = minMax[2];

            // Update the result range if we find a smaller range
            if (maxVal - minVal < resultRange[1] - resultRange[0]) {
                resultRange[0] = minVal;
                resultRange[1] = maxVal;
            }

            // Move the pointer of the list containing the minimum element
            int nextIdx = ptr[minValListIdx] + 1;
            if(nextIdx >= nums.get(minValListIdx).size()) break;
            ptr[minValListIdx] = nextIdx;
        }
        return resultRange;
    }

    private int[] findMinMax(List<List<Integer>> nums, int[] ptr) { // O(K)
        int minVal        = Integer.MAX_VALUE;
        int maxVal        = Integer.MIN_VALUE;
        int minValListIdx = 0; // Index of the list containing the minimum element

        for (int i = 0; i < nums.size(); i++) { // O(K)
            int listIdx      = i;
            int elementIdx   = ptr[i];
            int element      = nums.get(listIdx).get(elementIdx); // nums.get(i).get(ptr[i]);

            if (element < minVal) {
                minVal        = element;
                minValListIdx = i;
            }
            maxVal = Math.max(maxVal, element);
        }

        return new int[]{minVal, maxVal, minValListIdx}; // Return min, max, and the index of the min element
    }


    /*Approach3:  Using MinHeap (O(logK) instead of a linear scan O(K)
    Instead of scanning all K lists to find minIdx, we push the next element from minIdx into a heap, making the update O(log K) instead of O(K).
        TC: Heap Operations (Insertion & Removal) → O(log K)
            Each element is processed once → O(N)
            Total Complexity → O(N log K)
    */
    public int[] smallestRangeV3__using_MinHeap(List<List<Integer>> nums) {
        int k = nums.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // Min-Heap: [value, listIdx, elementIdx]
        int maxVal = Integer.MIN_VALUE;
        int[] resultRange = {0, Integer.MAX_VALUE};

        // Initialize the heap with the first element from each list
        for (int i = 0; i < k; i++) {
            int value = nums.get(i).get(0);
            minHeap.offer(new int[]{value, i, 0});
            maxVal = Math.max(maxVal, value);
        }

        // Process elements using the heap
        while (minHeap.size() == k) {
            int[] minElement = minHeap.poll(); // Extract smallest element
            int minVal = minElement[0], listIdx = minElement[1], elementIdx = minElement[2];

            // Update result range if found smaller
            if (maxVal - minVal < resultRange[1] - resultRange[0]) {
                resultRange[0] = minVal;
                resultRange[1] = maxVal;
            }

            // Move to next element in the same list
            if (elementIdx + 1 < nums.get(listIdx).size()) {
                int nextVal = nums.get(listIdx).get(elementIdx + 1);
                minHeap.offer(new int[]{nextVal, listIdx, elementIdx + 1});
                maxVal = Math.max(maxVal, nextVal); // Update maxVal
            } else {
                break; // Stop if any list is exhausted
            }
        }

        return resultRange;
    }


    public static void main(String[] args) {
        SmallestRangeCoveringElementsFromKLists solution = new SmallestRangeCoveringElementsFromKLists();
        List<List<Integer>> nums1 = List.of(
                List.of(4,10,15,24,26),
                List.of(0,9,12,20),
                List.of(5,18,22,30)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums1))); // [20,24]

        List<List<Integer>> nums2 = List.of(
                List.of(1,2,3),
                List.of(1,2,3),
                List.of(1,2,3)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums2))); // [1,1]

        List<List<Integer>> nums3 = List.of(
                List.of(1)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums2))); // [1,1]

    }
}
