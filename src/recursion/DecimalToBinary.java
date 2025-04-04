package recursion;

public class DecimalToBinary {
    public String decimalToBinary(int decimal, String rem) {
        if (decimal == 0) return rem;

        rem = decimal % 2 + rem; // prepend
        return decimalToBinary(decimal / 2, rem);
    }


    public static void main(String[] args) {
        String binary = new DecimalToBinary().decimalToBinary(257, "");
        System.out.println("Binary: " + binary);
    }
}
