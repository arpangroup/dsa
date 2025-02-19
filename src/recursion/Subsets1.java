package recursion;

import java.util.*;

// LeetCode-78. Subsets
// https://leetcode.com/problems/subsets/
public class Subsets1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        solve(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void solve(int[] nums, int index, List<List<Integer>> result, List<Integer> current) {
        if(index >= nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // take current elemt and move on
        current.add(nums[index]);
        solve(nums, index+1, result, current);

        // not take current elemt and move on
        current.remove(current.size()-1);
        solve(nums, index+1, result, current);
    }

    public static void main(String[] args) {
        Subsets1 solution = new Subsets1();
        var result = solution.subsets(new int[] {1, 2, 3});
        System.out.println(result);
    }
}
