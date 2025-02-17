package recursion;

public class Atoi {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        return helper(s.trim(), 0, 0, 1);
    }

    private int helper(String s, int index, long result, int sign) {
        if (index >= s.length()) {
            return (int) result * sign;
        }

        char ch = s.charAt(index);

        // Handle sign at the first position
        if (index == 0 && (ch == '-' || ch == '+')) {
            return helper(s, index + 1, result, ch == '-' ? -1 : 1);
        }

        // If it's a non-digit, stop recursion
        if (!Character.isDigit(ch)) {
            return (int) result * sign;
        }

        // Convert char to digit
        result = result * 10 + (ch - '0');

        // Handle integer overflow
        if (sign == 1 && result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (sign == -1 && -result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        // Continue recursion
        return helper(s, index + 1, result, sign);
    }


    public static void main(String[] args) {

    }
}
