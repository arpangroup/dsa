package sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKUniqueCharacter {
    public int lengthOfLongestSubstring(String s, int k) {
        int left=0;
        int right = 0;
        int[] freq = new int[26];
        Map<Character, Integer> map = new HashMap<>();


        int longest = 0;
        while (right < s.length() && left < s.length()) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            if (map.size() < k) right++;
            if (map.size() == k) {
                System.out.println("MAP: " + map +  ", left: " + left +  ", right: " + right + ", size: " + (right-left+1));
                longest = Math.max(longest, right-left+1);
                right++;
            } else if (map.size() > k) {
                char removeChar = s.charAt(left);
                if (map.containsKey(removeChar)) map.put(removeChar, map.get(removeChar) -1);
                if(map.getOrDefault(removeChar, 0) == 0) map.remove(removeChar);
                left ++;
                right++;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestSubstringWithKUniqueCharacter solution = new LongestSubstringWithKUniqueCharacter();
        String input = "aabacbebebe";
        int k = 3;


        System.out.println(solution.lengthOfLongestSubstring(input, k));
    }
}
