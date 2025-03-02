package sliding_window;

public class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        return 0;
    }

    public static void main(String[] args) {
        NumberOfSubstringsContainingAllThreeCharacters solution = new NumberOfSubstringsContainingAllThreeCharacters();
        String[] inputs = new String[]{
                "abcabc",
                "aaacb",
                "abc"
        };
        int[] outputs = new int[]{
                10,
                3,
                1
        };

        for (String str : inputs) {
            System.out.println(solution.numberOfSubstrings(str));
        }
    }
}
