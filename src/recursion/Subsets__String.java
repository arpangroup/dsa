package recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets__String {
    public List<String> subsets(String str) {
        List<String> result = new ArrayList<>();
        //backtrackV1(str, "", result);
        backtrackV2(str, "", result, 0);
        return result;
    }

    // inefficient, because we are doing substring operation in every recursive call, which itself takes O(N) time
    private void backtrackV1(String input, String current, List<String> result) {
        if (input.length() <= 0) {
            result.add(current);
            return;
        }

        String ch = input.charAt(0) + "";
        String remainStr = input.substring(1);

        // Include the current character and move to the next
        backtrackV1(remainStr, current+ch, result);


        // Exclude the current character and move to the next
        backtrackV1(remainStr, current, result);
    }


    // Efficient, instead of substring, we are handling with index number
    private void backtrackV2(String input, String current, List<String> result, int index) {
        if (index == input.length()) {
            result.add(current);
            return;
        }

        // Include the current character and move to the next
        backtrackV2(input, current + input.charAt(index), result, index+1);


        // Exclude the current character and move to the next
        backtrackV2(input, current, result, index+1);
    }


    public static void main(String[] args) {
        Subsets__String solution = new Subsets__String();
        System.out.println(solution.subsets("abc"));
    }
}
