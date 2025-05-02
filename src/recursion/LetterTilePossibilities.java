package recursion;

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
    public int numTilePossibilities(String tiles) {
        return 0;
    }

    private void backtrack(String tiles) {
        
    }

    public static void main(String[] args) {
        LetterTilePossibilities solution = new LetterTilePossibilities();

        System.out.println(solution.numTilePossibilities("AAB")); // 8 <== ["A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"]
        System.out.println(solution.numTilePossibilities("AAABBC")); // 188
        System.out.println(solution.numTilePossibilities("V")); // 1
    }
}
