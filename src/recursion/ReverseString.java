package recursion;

import java.util.Arrays;

public class ReverseString {
    /* Approach1: Return result from recursive function */
    public String reverse(String input) {
        if (input.equals("")) return "";
        int n = input.length();
        return input.charAt(n-1) + reverse(input.substring(0, n-1));
    }


    /* Approach2: Input-Output Approach */
    public void reverse(String input, MutableString output) { // abc
        if (input.equals("")) return;
        int n = input.length();
        output.value = output.value + input.charAt(n-1);
        reverse(input.substring(0, n-1), output);
    }

    /* Approach3: inplace reverse with 2 pointer approach */
    public void reverse(int[] arr, int left, int right) {
        if (left > arr.length/2) {
            return;
        }
        swap(arr, left, right);
        reverse(arr, left+1, right-1);
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    public static void main(String[] args) {
        //String result = new ReverseString().reverse("abc");

        /*MutableString result = new MutableString("");
        new ReverseString().reverse("abc", result);
        System.out.println(result.value);*/

        int[] arr = new int[]{1, 2, 3, 4, 5};
        new ReverseString().reverse(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));


    }

    static class MutableString {
        public String value;
        public MutableString(String value){
            this.value = value;
        }
        public int length() {
            return value.length();
        }
    }
}
