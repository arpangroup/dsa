package sliding_window;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {

    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
        String[] inputs = new String[]{
                "ABAB",
                "AABABBA",
        };
        int[] k = new int[]{
                2,
                1
        };
        int[] outputs = new int[]{
                4,
                4
        };

        for (int i=0; i< inputs.length; i++) {
            System.out.println(solution.characterReplacement(inputs[i], k[i]));
        }
    }
}
