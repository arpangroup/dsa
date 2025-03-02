package sliding_window;

import java.util.HashMap;
import java.util.Map;

public class CountAnagram {
    public int countOccurences(String str1, String pattern) {
        int ansCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : pattern.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);
        System.out.println(map);

        int distinct = map.size();

        int left = 0;
        int right = left;
        while (right < str1.length()) {
            int windowSize = right - left + 1;
            char ch = str1.charAt(right);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) distinct--;
            }

            right++;

            if (windowSize == pattern.length()) {
                 if (distinct == 0) ansCount ++;

                 char removeChar = str1.charAt(left);
                 if (map.containsKey(removeChar)) {
                     map.put(removeChar, map.get(removeChar) + 1);
                     if (map.get(removeChar) == 1) distinct++;
                 }
                 left++;
            }
        }

        return ansCount;
    }

    public static void main(String[] args) {
        CountAnagram solution = new CountAnagram();
        String str1 = "faq Xxoffxfofrxfox";
        String str2 = "fox";
        System.out.println(solution.countOccurences(str1, str2));
    }
}
