package heap;

/*
    Company Tags                : Google, Amazon
    LeetCode                    : 1046
    Leetcode Qn Link            : https://leetcode.com/problems/last-stone-weight/
*/

import java.util.*;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        //return lastStoneWeightV1__BruteForce(stones); // O(n^2)
        return lastStoneWeightV2__usingMaxHeap(stones); // O(nlogn)
    }

    // Approach1: Bruteforce: Use sorting to fid max stones ==> O(nlogn)
    // TC: O(n) * O(nlogn) ==> O(n^2)
    public int lastStoneWeightV1__BruteForce(int[] stones) {
        // Select last 2 numb elements and compare and add the difference to existing array
        // again sort the array after adding the  difference

        int size = stones.length;
        while(size > 1) {               // O(n)
            Arrays.sort(stones);        // O(nlogn)
            int stone1 = stones[--size];// peek last
            int stone2 = stones[--size];// peek second last

            if(stone1 >= stone2) {
                stones[size++] = stone1 - stone2;
            }
        }
        return stones[0];
    }

    // Aproach2: Use MaxHeap to store max stone==> so that we can peek max elems in O(1) time
    // O(n) + O(nlogn) ==> O(nlogn)
    public int lastStoneWeightV2__usingMaxHeap(int[] stones) {
        if(stones.length == 1) return stones[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a); // maxHeap
        for(int stone : stones) pq.offer(stone); // Using heapify internally (O(n))


        while(!pq.isEmpty()) { //<----------O(n * 3logn) ==> O(nlogn)
            if(pq.size() == 1) break;

            int stone1 = pq.poll(); //<---------- O(logn)
            int stone2 = pq.poll(); //<---------- O(logn)

            if(stone1 >= stone2) {
                pq.offer(stone1 - stone2); //<--- O(logn)
            }
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        int[] stones = new int[]{2,7,4,1,8,1};
        System.out.println(solution.lastStoneWeight(stones)); // 1

        stones = new int[]{1};
        System.out.println(solution.lastStoneWeight(stones)); // 1

        stones = new int[]{1,3};
        System.out.println(solution.lastStoneWeight(stones)); // 2
    }
}
