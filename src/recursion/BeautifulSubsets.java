package recursion;

import base.MutableInteger;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 131
    Leetcode Link               : https://leetcode.com/problems/palindrome-partitioning/
    Description: A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
*/
public class BeautifulSubsets {
    public int beautifulSubsets(int[] nums, int k) {
        MutableInteger count = new MutableInteger(0);
        //backtrackV1(nums, 0, k, new HashSet<>(), count); // TC: O(2 ^ n); SC: O(n) <== Not work for Duplicate
        backtrackV2(nums, 0, k, new HashMap<>(), count);
        return count.value;
    }

    /*  Below Approach with hashSet will not work for duplicate elements
        Why The "not take" case need to be before "take" case?
         - 1. Avoid Double Counting:
                - If we handled the "take" case first, we might count subsets that include an element before we explore the option of excluding it.
                - By prioritizing the "not take" case, we ensure that every subset is considered in a structured way.
         - 2. Ensuring Proper Backtracking Order:
                - When we exclude an element first, we explore all possible subsets without it.
                - Then, we include it and explore subsets with it.
                - This avoids interference between the two paths, making backtracking cleaner.

          Recursive Tree (Correct Order)

          backtrack(0, {})    // Start with an empty subset
             ├── backtrack(1, {})    // Not take 1
             │    ├── backtrack(2, {})  ✅ Count this subset
             │    ├── backtrack(2, {2}) ✅ Count this subset
             ├── backtrack(1, {1})  // Take 1
                  ├── backtrack(2, {1})  ✅ Count this subset


           If "Take" Came First (Incorrect Order)

           backtrack(0, {})
             ├── backtrack(1, {1}) // Take 1 first
             │    ├── backtrack(2, {1}) ✅ Count this subset
             ├── backtrack(1, {}) // Not take 1
                  ├── backtrack(2, {})  ✅ Count this subset
                  ├── backtrack(2, {2}) ✅ Count this subset

        TC: Each element has two choices: Take / Non-Take
            O(2 ^ n)
        SC: O(n) for Hash Set
     */
    private void backtrackV1(int[] arr, int idx, int k, Set<Integer> subsets, MutableInteger count) {
        if (idx == arr.length) {
            if (!subsets.isEmpty()) { // Empty subset should not be counted
                count.value += 1;
            }
            return;
        }

        // Not take current element
        // IMPORTANT: "not take" before the "take" case to ensure correct counting.
        backtrackV1(arr, idx + 1, k, subsets, count);

        // Take current element if it maintains the beauty condition
        if (!subsets.contains(arr[idx] + k) && !subsets.contains(arr[idx] - k)) {
            subsets.add(arr[idx]);
            backtrackV1(arr, idx + 1, k, subsets, count);
            subsets.remove(arr[idx]);  // Backtrack (important step)
        }

    }

    private void backtrackV2(int[] arr, int idx, int k, Map<Integer, Integer> map, MutableInteger count) {
        if (idx == arr.length) {
            if (!map.isEmpty()) { // Empty subset should not be counted
                count.value += 1;
            }
            return;
        }

        // Not take current element
        backtrackV2(arr, idx + 1, k, map, count);

        // Take current element if it maintains the beauty condition
        if (!map.containsKey(arr[idx] + k) && !map.containsKey(arr[idx] - k)) {
            map.put(arr[idx], map.getOrDefault(arr[idx], 0) + 1);
            backtrackV2(arr, idx + 1, k, map, count);

            // Restore state
            if (map.get(arr[idx]) == 1) map.remove(arr[idx]);
            else map.put(arr[idx], map.get(arr[idx]) - 1);
        }
    }

    public static void main(String[] args) {
        BeautifulSubsets solution = new BeautifulSubsets();

        System.out.println(solution.beautifulSubsets(new int[]{2, 4, 6}, 2)); // 4 <==[2], [4], [6], [2, 6].
        System.out.println(solution.beautifulSubsets(new int[]{1}, 1)); // 1
        System.out.println(solution.beautifulSubsets(new int[]{10,4,5,7,2,1}, 3)); // 23
        System.out.println(solution.beautifulSubsets(new int[]{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000}, 1)); // 262143
        System.out.println(solution.beautifulSubsets(new int[]{18,12,10,5,6,2,18,3}, 8)); // 143 <== Duplicate
        System.out.println(solution.beautifulSubsets(new int[]{2, 4, 4, 6}, 2));
    }
}
