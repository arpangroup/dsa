package heap;

/*
    Company Tags                : MICROSOFT
    LeetCode                    : 502
    Leetcode Link               : https://leetcode.com/problems/ipo/description/
*/
public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        return 0;
    }

    public static void main(String[] args) {
        IPO solution = new IPO();
        int k          =2;
        int w          = 0;
        int[] profits  = new int[]{1, 2, 3};
        int[] capitals = new int[]{0, 1, 1};
        int output     = 4;
        System.out.println(solution.findMaximizedCapital(k, w, profits, capitals)); // 4

        k        = 3;
        w        = 0;
        profits  = new int[]{1, 2, 3};
        capitals = new int[]{0, 1, 2};
        output   = 6;
        System.out.println(solution.findMaximizedCapital(k, w, profits, capitals)); // 6
    }
}
