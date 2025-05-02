package recursion;

import java.util.*;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        s = s.toLowerCase();
        List<String> result = new ArrayList<>();
        backtrack(s, 0, "",result);
        return result;
    }

    private void backtrack(String str, int idx, String temp, List<String> result) {
        if(idx >= str.length() || temp.length() ==str.length()) {
            result.add(temp);
            return;
        }

        char ch = str.charAt(idx);
        if(Character.isLetter(ch)) {
            backtrack(str, idx + 1, temp + Character.toLowerCase(ch), result);
            backtrack(str, idx + 1, temp + Character.toUpperCase(ch), result);
        } else { // if isDigit
            backtrack(str, idx + 1, temp + ch, result);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation solution = new LetterCasePermutation();
        System.out.println(solution.letterCasePermutation("a1b2")); // ["a1b2", "a1B2", "A1b2", "A1B2"]
        System.out.println(solution.letterCasePermutation("3z4")); // ["3z4", "3Z4"]
    }
}
