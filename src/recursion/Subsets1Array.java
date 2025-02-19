package recursion;

import java.util.*;

// LeetCode-78. Subsets
// https://leetcode.com/problems/subsets/
public class Subsets1Array {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //backtrackV1(nums, 0, result, new ArrayList<>());
        backtrackV2(nums, 0, result, new ArrayList<>());
        return result;
    }

    // using for loop
    private void backtrackV1(int[] arr, int index, List<List<Integer>> result, List<Integer> current) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);
            backtrackV1(arr, i + 1, result, current);
            current.removeLast(); // backtrack
        }
    }

    // without for loop, cleaner code
    private void backtrackV2(int[] arr, int index, List<List<Integer>> result, List<Integer> current) {
        if(index >= arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // take current element and move on
        current.add(arr[index]);
        backtrackV2(arr, index+1, result, current);

        // not take current element and move on
        current.removeLast();
        backtrackV2(arr, index+1, result, current);
    }

    public static void main(String[] args) {
        Subsets1Array solution = new Subsets1Array();
        var result = solution.subsets(new int[] {1, 2, 3});
        System.out.println(result);
    }
}
