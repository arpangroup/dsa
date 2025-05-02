package recursion;

import java.util.*;

/*

    Company Tags                :
    LeetCode                    : 1079. Letter Tile Possibilities
    Leetcode Link               : https://leetcode.com/problems/letter-tile-possibilities/
    Similar Questions           :
    Description: You have n tiles, where each tile has one letter tiles[i] printed on it.
                 Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

    Input: tiles = "AAB"
    Output: 8
    Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
*/
public class LetterTilePossibilities {
    int count = 0;

    public int numTilePossibilities(String tiles) {
        /*
        Set<String> result = new HashSet<>();
        boolean[] used = new boolean[tiles.length()];
        backtrack(tiles, used, "", result);
        return result.size() - 1; // ignore empty string
        */

        int[] freq = new int[26];
        for (char ch : tiles.toCharArray()) {
            int pos = ch - 'A';
            freq[pos]++;
        }
        backtrackV1(freq);
        return count - 1; // Don't Count this non-empty sequence
    }

    private void backtrackV1(int[] freq) {
        count ++;

        for(int i=0; i < 26; i++) {
            if(freq[i] == 0) continue;

            freq[i]--;
            backtrackV1(freq);
            freq[i]++;
        }
    }

    private void backtrack(String tiles, boolean[] used, String curr, Set<String> result) {
        result.add(curr);

        for(int i=0; i< tiles.length(); i++) {
            if(used[i]) continue;

            used[i] = true;
            backtrack(tiles, used, curr + tiles.charAt(i), result);

            used[i] = false;
        }
    }

    public static void main(String[] args) {
        LetterTilePossibilities solution = new LetterTilePossibilities();

        System.out.println(solution.numTilePossibilities("AAB")); // 8 <== ["A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"]
        System.out.println(solution.numTilePossibilities("AAABBC")); // 188
        System.out.println(solution.numTilePossibilities("V")); // 1
    }
}
