package recursion;

import java.util.*;

// LeetCode-90. Subsets II - Unique subsets
// https://leetcode.com/problems/subsets-ii/
public class Subsets2Array {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrackV2(nums, 0, new ArrayList<>(), result);
        return result;
    }

    // inefficient, we are traversing the result list and checking whether current list is present or not in each recursive call
    private void backtrackV1(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        if (result.contains(current)) return; // avoid duplicate entries

        result.add(new ArrayList<>(current));

        for (int i = index; i< arr.length; i++) {
            if (i > index && arr[i] == arr[i - 1]) continue; // Skip duplicates

            // take current element
            current.add(arr[i]);
            backtrackV1(arr, i+1, current, result);
            current.removeLast();
        }
    }

    private void backtrackV2(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        if(index >= arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // take current element
        current.add(arr[index]);
        backtrackV2(arr, index+1, current, result);
        current.removeLast();

        // not take current element
        while(index + 1 < arr.length && arr[index+1] == arr[index]) index++; // skip all duplicate elements
        backtrackV2(arr, index+1, current, result);
    }

    public static void main(String[] args) {
        Subsets2Array solution = new Subsets2Array();
        var result = solution.subsetsWithDup(new int[] {1, 2, 2});
        System.out.println(result);
    }
}
