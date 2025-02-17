package recursion;

public class Recursion_03_Palindrome {
    boolean isPalindrome(String str) {
        if (str.length() == 0 || str.length() == 1) return true;

        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrome(str.substring(1, str.length()-1));
        }
        return false;
    }

    public static void main(String[] args) {
        boolean result = new Recursion_03_Palindrome().isPalindrome("dad");
        System.out.println("isPalindrome: " + result);
    }
}
