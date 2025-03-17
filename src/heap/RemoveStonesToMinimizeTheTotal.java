package heap;

import java.util.*;

/*
    Company Tags                : Google, Miscrosoft, Netflix, Salesforce, Meta
    LeetCode                    : 1962
    Leetcode Link               : https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 */
public class RemoveStonesToMinimizeTheTotal {
    public int minStoneSum(int[] piles, int k) {
        //return minStoneSumV1(piles, k); // O(N+klogN)
        return minStoneSumV2(piles, k);   // O(N+klogN)
    }

    /*
        TC: Heap initialization: O(N)
            Heap operations (k removals + insertions) O(klogN)
            Final summation: O(N)
        Total TC: O(N+klogN+N)=O(N+klogN)
     */
    public int minStoneSumV1(int[] piles, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0]-a[0]); // [element, index]

        for(int i=0; i<piles.length; i++) {
            pq.offer(new int[]{piles[i], i});
        }

        while(!pq.isEmpty() && k >= 0) {
            if(k == 0) break;
            int[] pile = pq.poll();
            int pileVal = pile[0];
            int pileIdx = pile[1];

            int remain = pileVal - (pileVal / 2);
            pq.offer(new int[]{remain, pileIdx});

            piles[pileIdx] = remain;
            k--;
        }

        int total = 0;
        for(int pile : piles) total += pile;
        return total;
    }

    /*
    TC: Heap initialization: O(N)
        Heap operations O(klogN) (Each operation involves removal and re-insertion)
        Final summation: O(N)
    Total TC: O(N+klogN)
 */
    public int minStoneSumV2(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // Max heap

        int totalSum = 0;
        for (int pile : piles) {
            pq.offer(pile);
            totalSum += pile;
        }

        while (k-- > 0) {
            int maxPile = pq.poll();
            int removed = maxPile / 2;
            totalSum -= removed;  // Directly update the sum
            pq.offer(maxPile - removed);
        }

        return totalSum;
    }

    public static void main(String[] args) {
        RemoveStonesToMinimizeTheTotal solution = new RemoveStonesToMinimizeTheTotal();
        int[] piles = new int[]{5, 4, 9};
        int k = 2;
        System.out.println(solution.minStoneSum(piles, k)); // 12

        piles = new int[]{4, 3, 6, 7};
        k = 3;
        System.out.println(solution.minStoneSum(piles, k)); // 12

        int n = 5;
        System.out.println("N: " + n/2);
    }
}
