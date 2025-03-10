package heap;

import java.util.*;

/*
    Company Tags                : will soon update
    LeetCode                    : 632
    Leetcode Link               : https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists
*/
public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        return new int[]{};
    }

    public static void main(String[] args) {
        SmallestRangeCoveringElementsFromKLists solution = new SmallestRangeCoveringElementsFromKLists();
        List<List<Integer>> nums1 = List.of(
                List.of(4,10,15,24,26),
                List.of(0,9,12,2),
                List.of(5,18,22,30)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums1))); // [20,24]

        List<List<Integer>> nums2 = List.of(
                List.of(1,2,3),
                List.of(1,2,3),
                List.of(1,2,3)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums2))); // [1,1]

    }
}
