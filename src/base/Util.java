package base;

public class Util {

    public static char[][] mapToCharArray(String[][] input) {
        char[][] output = new char[input.length][input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                output[i][j] = input[i][j].charAt(0); // extract the char from the string
            }
        }
        return output;
    }
}
