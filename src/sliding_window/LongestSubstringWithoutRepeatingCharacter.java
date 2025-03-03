package sliding_window;

public class LongestSubstringWithoutRepeatingCharacter {
    public int lengthOfLongestSubstring(String s) {
        int left=0;
        int right = 0;
        int[] freq = new int[26];


        int longest = 0;
        while (right < s.length() && left < s.length()) {
            char ch = s.charAt(right);
            int pos = ch - 'a';
            freq[pos] = freq[pos] + 1;

            if (freq[pos] == 0) right++;
            if (freq[pos] == 1) {
                longest = Math.max(longest, right-left+1);
                right++;
            } else if (freq[pos] > 1) {
                left ++;
                right++;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacter solution = new LongestSubstringWithoutRepeatingCharacter();
        String[] inputs = new String[]{
                "abcabcbb",
                "bbbbb",
                "pwwkew"
        };
        int[] outputs = new int[]{
         3,
         1,
         3
        };

        for (String str : inputs) {
            System.out.println(solution.lengthOfLongestSubstring(str));
        }
    }
}
