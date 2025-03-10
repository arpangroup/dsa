package heap;

/*
    Company Tags                : Google, Amazon
    LeetCode                    : 1046
    Leetcode Qn Link            : https://leetcode.com/problems/last-stone-weight/
*/

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        int[] stones = new int[]{2,7,4,1,8,1};
        System.out.println(solution.lastStoneWeight(stones)); // 1

        stones = new int[]{1};
        System.out.println(solution.lastStoneWeight(stones)); // 1
    }
}
