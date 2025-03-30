package recursion;


import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 131
    Leetcode Link               : https://leetcode.com/problems/palindrome-partitioning/
*/
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String str, int idx, List<String> curr, List<List<String>> result) {
        if (idx >= str.length()) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i=idx; i< str.length(); i++) {
            if (!isPalindrome(str, idx, i)) continue;

            curr.add(str.substring(idx, i + 1));
            backtrack(str, i + 1, curr, result);
            curr.removeLast();
        }
    }

    private boolean isPalindrome(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return isPalindrome(str, left+1, right-1);
    }

    public static void main(String[] args) {
        PalindromePartitioning solution = new PalindromePartitioning();
        System.out.println(solution.partition("aab")); // [["a","a","b"],["aa","b"]]
        System.out.println(solution.partition("a")); // [["a"]]
    }
}
