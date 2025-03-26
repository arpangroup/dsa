package recursion;

import java.util.*;

// LeetCode-47. Permutations II - Unique Permutations
// https://leetcode.com/problems/permutations-ii/
public class Permutations2__Array {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //backtrackV1(nums, new ArrayList<>(), result, new boolean[nums.length]);
        backtrackV2(nums, 0, result);
        return result;
    }

    private void backtrackV1(int[] arr, List<Integer> current, List<List<Integer>> result, boolean[] used) {
        if (current.size() == arr.length && !result.contains(current)) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            current.add(arr[i]);
            backtrackV1(arr, current, result, used);

            used[i] = false;
            current.removeLast();
        }
    }

    // Recursion with swapping:
    private void backtrackV2(int[] arr, int index, List<List<Integer>> result) {
        if (index == arr.length) {
            result.add(new ArrayList<>());
            for (int num : arr) result.get(result.size() - 1).add(num);
            return;
        }

        Set<Integer> used = new HashSet<>(); // Track elements at current depth
        for (int i = index; i < arr.length; i++) {
            if (used.contains(arr[i])) continue; // Skip duplicates
            used.add(arr[i]);

            swap(arr, index, i);
            backtrackV2(arr, index + 1, result);
            swap(arr, index, i); // Restore
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Permutations2__Array solution = new Permutations2__Array();

        int[] inputs1 = new int[]{1,1,2};
        int[][] outputs1 = new int[][]{ {1,1,2},{1,2,1}, {2,1,1}};


        System.out.println(solution.permuteUnique(inputs1));
    }
}
