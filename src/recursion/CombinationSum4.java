package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

    Company Tags                : Google
    LeetCode                    : 377. Combination Sum IV
    Leetcode Link               : https://leetcode.com/problems/combination-sum-iv/
    Similar Questions           : LeetCode 39 / 40 / 216
    Description: Return the number of possible combinations that add up to target.
    Input: nums = [1,2,3], target = 4
    Output: 7
    Explanation: The possible combination ways are:
        (1, 1, 1, 1)
        (1, 1, 2)
        (1, 2, 1)
        (1, 3)
        (2, 1, 1) <======== This O/P makes the problem unique
        (2, 2)
        (3, 1)
*/
public class CombinationSum4 {
    public int[][] cache = new int[201][1001];

    public int combinationSum4(int[] nums, int target) {
        // initialize the cache
        for (int[] arr : cache) Arrays.fill(arr, -1);

        return backtrack__template1_withoutForLoop(nums, 0, target, new ArrayList<>());
        //return backtrack__template2_withForLoop(nums, 0, target, new ArrayList<>());
    }

    private int backtrack__template1_withoutForLoop(int[] arr, int idx, int target, List<Integer> subsets) {
        if (target == 0) return 1;
        if (target < 0 || idx >= arr.length) return 0;

        if (cache[idx][target] != -1) return cache[idx][target];

        int take_idx   = backtrack__template1_withoutForLoop(arr, 0, target-arr[idx], subsets);
        int reject_idx = backtrack__template1_withoutForLoop(arr, idx+1, target, subsets);

        return cache[idx][target] = take_idx + reject_idx;
    }


    private int backtrack__template2_withForLoop(int[] arr, int idx, int target, List<Integer> subsets) {
        if (target == 0) {
            //result.add(new ArrayList<>(subsets));
            return 1;
        }

        if (cache[idx][target] != -1) return cache[idx][target];

        int count = 0;
        for (int i=0; i< arr.length; i++) {
            if (target - arr[i] < 0) continue;

            subsets.add(arr[i]);
            count += backtrack__template2_withForLoop(arr, 0, target - arr[i], subsets);
            subsets.remove(subsets.size() - 1);
        }
        return cache[idx][target] = count;
    }

    public static void main(String[] args) {
        CombinationSum4 solution = new CombinationSum4();

        System.out.println(solution.combinationSum4(new int[]{1,2,3}, 4)); // 7
        System.out.println(solution.combinationSum4(new int[]{9}, 3)); // 0
        System.out.println(solution.combinationSum4(new int[]{4,2,1}, 32)); // 39882198 <==TLE
    }
}
