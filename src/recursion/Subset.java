package recursion;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public void subsetV1(String input, String output) {
        if (input.length() <= 0) {
            System.out.println(output.length() > 0 ? output : "{}");
            return;
        }

        String ch = input.charAt(0) + "";
        String remainStr = input.substring(1);

        // Include the current character and move to the next
        subsetV1(remainStr, output+ch);


        // Exclude the current character and move to the next
        subsetV1(remainStr, output);
    }


    public void printSubset(int[] arr, List<Integer> result, int index){
        if (index >= arr.length) {
            System.out.println(result);
            return;
        }
        // include current index & move to next
        result.addLast(arr[index]);
        printSubset(arr, result, index+1);


        // exclude current index & move to next
        result.removeLast();
        printSubset(arr, result, index+1);
    }


    public void printSubsets(String input, String output) {
        if (input.length() == 0) {
            System.out.println(output.length() > 0 ? output : "{}");
            return;
        }

        String output1 = output;
        String output2 = output;
        output2 += input.charAt(0);

        String newInput = input.substring(1);
        printSubsets(newInput, output1);
        printSubsets(newInput, output2);
        return;
    }

    public void printSubsets(String input, String current, int index) {
        if (index == input.length()) {
            System.out.println(current);
            return;
        }
        // Exclude the current character and move to the next
        printSubsets(input, current, index+1);

        // Include the current character and move to the next
        printSubsets(input, current + input.charAt(index), index+1);
    }


    public static void main(String[] args) {
//        new Subset().printSubsets("abc", "");
//        new Subset().printSubsets("abc", "", 0);
//        new Subset().printSubsets("abc", "", 0);
//        new Subset().subsetV1("abc", "");


        new Subset().printSubset(new int[] {1, 2, 3}, new ArrayList<>(), 0);
    }
}
