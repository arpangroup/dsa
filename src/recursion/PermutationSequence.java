package recursion;

import base.MutableString;

import java.util.LinkedList;
import java.util.List;

/*
    Company Tags                :
    LeetCode                    : 60
    Leetcode Link               : https://leetcode.com/problems/permutation-sequence/
*/
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        k = k-1; // we will use 0-based indexing
        List<Integer> availableNumbers = new LinkedList<>();
        for(int i=0; i<n; i++) availableNumbers.addLast(i+1);

        // Using Call By Reference:
        /*MutableString result = new MutableString("");
        permuteV1__callByRef(nums, n, k, result);
        return result.value;*/

        return permuteV2(availableNumbers, n, k);
    }

    public void permuteV1__callByRef(List<Integer> nums, int n, int k, MutableString result) {
        if (n == 0) return;

        int groupSize  = fact(n-1);
        int selectedIndex = k / groupSize;
        result.value += nums.get(selectedIndex);
        nums.remove(selectedIndex);
        permuteV1__callByRef(nums, n-1, k % groupSize, result);
    }

    public String permuteV2(List<Integer> nums, int n, int k) {
        if (n == 0) return ""; // if remainingNumbers == 0

        int groupSize      = fact(n-1);
        int selectedIndex  = k / groupSize;
        int selectedNumber = nums.get(selectedIndex);
        nums.remove(selectedIndex);

        return selectedNumber + permuteV2(nums, n-1, k % groupSize);
    }

    private int fact(int n) {
        if (n == 0) return 1;
        return n * fact(n - 1);
    }

    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        System.out.println(solution.getPermutation(3, 3)); // 213
        System.out.println(solution.getPermutation(4, 9)); // 2314
        System.out.println(solution.getPermutation(3, 1)); // 123
    }
}
