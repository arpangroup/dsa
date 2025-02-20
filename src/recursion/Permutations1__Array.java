package recursion;

import java.util.*;

// LeetCode-46. Permutations
// https://leetcode.com/problems/permutations
public class Permutations1__Array {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //backtrackV1(nums, new ArrayList<>(), result, new HashSet<>());
        backtrackV2(nums, 0, result);
        return result;
    }

    // Recursion with swapping:
    private void backtrackV2(int[] arr, int index, List<List<Integer>> result) {
        if (index == arr.length) {
            result.add(new ArrayList<>());
            for (int num : arr) result.get(result.size() - 1).add(num);
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            backtrackV2(arr, index + 1, result);
            swap(arr, index, i); // Restore
        }
    }

    private void backtrackV1(int[] arr, List<Integer> current, List<List<Integer>> result, Set<Integer> used) {
        if(current.size() == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i=0; i < arr.length; i++) {
            if(!used.contains(arr[i])) {
                used.add(arr[i]);

                current.add(arr[i]);
                backtrackV1(arr,  current, result, used);
                current.removeLast();
                used.remove(arr[i]);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
