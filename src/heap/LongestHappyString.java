package heap;

import java.util.*;

/*
    Company Tags                : will soon update
    LeetCode                    : 1405
    Leetcode Link               : https://leetcode.com/problems/longest-happy-string
*/
public class LongestHappyString {

    // TC: O(N log 3) ≈ O(N)
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p,q) -> q.count-p.count); // maxHeap;
        if (a > 0) pq.offer(new Pair('a', a));
        if (b > 0) pq.offer(new Pair('b', b));
        if (c > 0) pq.offer(new Pair('c', c));

        StringBuilder output = new StringBuilder();
        while(!pq.isEmpty()) { // O(a+b+c) ==> O(N)
            Pair curr = pq.poll(); // O(logK) ==> O(log3) ==> O(1)

            int len = output.length();
            if (len >= 2 && output.charAt(len - 1) == curr.ch && output.charAt(len - 2) == curr.ch) {
                if(pq.isEmpty()) break;

                // use next available  character
                Pair next = pq.poll(); // we need the next element
                output.append(next.ch);
                next.count--;
                if (next.count > 0) pq.offer(next);
                pq.offer(curr);
            } else {
                output.append(curr.ch);
                curr.count --;
                if(curr.count > 0) pq.offer(curr);
            }
        }
        return output.toString();
    }

    static class Pair {
        public char ch;
        public int count;
        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        LongestHappyString solution = new LongestHappyString();
        System.out.println(solution.longestDiverseString(1, 1, 7)); // "ccaccbcc"
        System.out.println(solution.longestDiverseString(7, 1, 0)); // "aabaa"
    }
}
