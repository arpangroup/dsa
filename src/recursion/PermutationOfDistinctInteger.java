package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationOfDistinctInteger {
    List<List<Integer>> findPermutation(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        permute(arr, new ArrayList<>(), visited, result);
        return result;
    }

    void permute(int[] arr, List<Integer> recStack, boolean[] visited, List<List<Integer>> result) {
        if (recStack.size() == arr.length) {
            result.add(new ArrayList<>(recStack));
            return;
        }

        for (int i=0; i< arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                recStack.add(i);
                permute(arr, recStack, visited, result);
            }
        }

    }

    List<String> findPermutation(String str) {
        List<String> result = new ArrayList<>();
        permute(str, "", result);
        return result;
    }

    void permute(String input, String recStr, List<String> result) {
        if (input.length() == 0) {
            result.add(recStr);
            return;
        }

        for (int i=0; i< input.length(); i++) {
            //String ch = src.charAt(i) + "";
            String newSrc = input.substring(0, i) + input.substring(i+1);
            String newOutput = recStr + input.charAt(i) + "";
            permute(newSrc, newOutput, result);
        }

    }


    public static void main(String[] args) {
        PermutationOfDistinctInteger solution = new PermutationOfDistinctInteger();

        /*var result1 = solution.findPermutation(new int[]{1, 2, 3}); // [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
        var result2 = solution.findPermutation(new int[]{0,1}); // [[0,1], [1,0]]

        System.out.println("RESULT1: " + result1);
        System.out.println("RESULT2: " + result2);*/

        var result3 = solution.findPermutation("abc");
        System.out.println("RESULT: " + result3);
    }
}
