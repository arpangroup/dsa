package recursion;

import java.util.HashSet;
import java.util.Set;

public class Permutations__String {
    public void permutationUsingRecursion(String input, String output) {
        if (input.length() == 0) {
            System.out.println(output);
            return;
        }

        for (char choice = 0; choice < input.length(); choice ++) {
            String ch = input.charAt(choice) + "";
            String newInput = input.substring(0, choice) + input.substring(choice+1);
            permutationUsingRecursion(newInput, ch + output);
        }
    }
    public void permutationUsingRecursionWithoutDuplicate(String input, String output) {
        if (input.length() == 0) {
            System.out.println(output);
            return;
        }

        Set<Character> visited = new HashSet<>();
        for (char choice = 0; choice < input.length(); choice ++) {
            char ch = input.charAt(choice);
            if (!visited.contains(ch)) {
                visited.add(ch);
                String newInput = input.substring(0, choice) + input.substring(choice+1);
                permutationUsingRecursionWithoutDuplicate(newInput, ch + output);
            }
        }
    }




    public static void main(String[] args) {
        //new PermutationOfString().permutationUsingRecursion("aa", "");
        new Permutations__String().permutationUsingRecursionWithoutDuplicate("aba", "");
    }
}
