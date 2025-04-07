package recursion;

public class DecimalToBinary {
    public String decimalToBinary(int decimal, String rem) {
        if (decimal == 0) return rem;

        rem = decimal % 2 + rem; // prepend
        return decimalToBinary(decimal / 2, rem);
    }

    public String base2(int n) {
        if(n == 0) return "";

        int rem = n % 2;
        return  base2(n/2) + rem;
    }


    // In base -2, we want to represent a number using only digits 0 and 1
    public String baseNeg2(int n) {
        if(n == 0) return "0";

        // n % -2 can result in negative remainders,
        // which are not valid in binary representation.
        // In base -2, remainders must be either 0 or 1.
        // For example, 3 % -2 equals -1, which is invalid in base -2.
        /*int rem = n % (-2);
        return rem + baseNeg2(n/(-2));*/

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int rem = n % -2;
            n = n / -2;

            // To ensure the remainder is non-negative, you need to adjust n and the remainder when rem < 0
            if (rem < 0) {
                rem += 2; // Adjusting the remainder into the valid range (0 or 1) by adding 2 → rem += 2
                n += 1; // But now we've increased the "total value" by 2, so we must compensate for it in the quotient by increasing n → n += 1
            }

            sb.append(rem);
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
//        String binary = new DecimalToBinary().decimalToBinary(2, "");
//        System.out.println("Binary: " + binary);

        System.out.println(new DecimalToBinary().base2(5));

        System.out.println(-1 % -2);
    }
}
