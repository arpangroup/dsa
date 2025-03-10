package heap;

/*
    Company Tags                : Google, Miscrosoft, Netflix, Salesforce, Meta
    LeetCode                    : 1962
    Leetcode Link               : https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 */
public class RemoveStonesToMinimizeTheTotal {
    public int minStoneSum(int[] piles, int k) {
        return 0;
    }

    public static void main(String[] args) {
        RemoveStonesToMinimizeTheTotal solution = new RemoveStonesToMinimizeTheTotal();
        int[] piles = new int[]{5, 4, 9};
        int k = 2;
        System.out.println(solution.minStoneSum(piles, k)); // 12

        piles = new int[]{4, 3, 6, 7};
        k = 3;
        System.out.println(solution.minStoneSum(piles, k)); // 12
    }
}
