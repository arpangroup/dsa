package recursion;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : GFG
    Leetcode Link               : https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1
    Given a string s, you need to print all possible strings that can be made by placing spaces (zero or one) in between them. The output should be printed in sorted increasing order of strings.
    Input: s = "ABC"
    Output: (A B C)(A BC)(AB C)(ABC)

    Input: s = "BBR"
    Output: (B B R)(B BR)(BB R)(BBR)
*/
public class PermutationWithSpaces {
    public ArrayList<String> permutation(String s) {
        ArrayList<String> result = new ArrayList<>();
        backtrack(s, 0, "", result);
        Collections.sort(result);
        return result;
    }

    private void backtrack(String str, int idx, String temp, ArrayList<String> result) {
        if(idx == str.length()) {
            result.add(temp);
            return;
        }

        // take only the letter
        backtrack(str, idx + 1, temp + str.charAt(idx), result);

        // add space *after* current letter, only if it's not the last character
        if (idx < str.length() - 1) {
            backtrack(str, idx + 1, temp + str.charAt(idx) + " ", result);
        }
    }

    public static void main(String[] args) {
        PermutationWithSpaces solution = new PermutationWithSpaces();
        System.out.println(solution.permutation("ABC")); // ["A B C", "A BC", "AB C", "ABC"]
        System.out.println(solution.permutation("BBR")); // ["B B R", "B BR", "BB R", "BBR"]
    }
}
