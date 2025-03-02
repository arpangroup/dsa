package sliding_window;

public class LongestSubstringWithoutRepeatingCharacter {
    public int lengthOfLongestSubstring(String s) {
        return 0;
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
