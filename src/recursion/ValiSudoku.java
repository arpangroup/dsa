package recursion;

import java.util.HashSet;
import java.util.Set;

/*
    Company Tags                : Google, Amazon, Microsoft, Uber, Apple, Snapchat
    LeetCode                    : 36
    Leetcode Link               : https://leetcode.com/problems/valid-sudoku/
    Description: A sudoku solution must satisfy all of the following rules:
        1. Each of the digits 1-9 must occur exactly once in each row.
        2. Each of the digits 1-9 must occur exactly once in each column.
        3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    The '.' character indicates empty cells.
*/
public class ValiSudoku {
    public boolean isValidSudoku(String[][] board) {
        // Brute Force Approach: Validate each rows + cols + sub grids ==> O(N^2)
        /*if (!validateRows(board)) return false;
        if (!validateCols(board)) return false;
        return validateSubBoxes(board);*/

        // Optimal Approach:
        //return isValidSudoku__optimalV1(board); // using String to represent unique position
        return isValidSudoku__optimalV2(board); // Avoid String concatenation — use arrays or bitsets.
    }

    private boolean isValidSudoku__optimalV1(String[][] board) {
        Set<String> set = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col].equals(".")) continue;
                String rowStr = "ROW_" + row + "_IS_" + board[row][col];
                String colStr = "COL_" + col + "_IS_" + board[row][col];
                String boxStr =  "BOX_" + (row/3) + "_" + (col/3) + "_IS_" + board[row][col];

                if (set.contains(rowStr) || set.contains(colStr) || set.contains(boxStr)) return false;
                set.add(rowStr);
                set.add(colStr);
                set.add(boxStr);
            }
        }
        return true;
    }

    /*
        Avoid String concatenation — use arrays or bitsets.
        String creation (like "ROW_" + row + "_IS_" + value) creates many objects. Instead, you can track values using boolean[9][9] or even int bitmasks for each row/col/box.
     */
    private boolean isValidSudoku__optimalV2(String[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char c = board[row][col].charAt(0); // string to char
                if (c == '.') continue;

                int num = c - '1'; // convert '1'-'9' to 0-8
                int boxIndex = (row / 3) * 3 + (col / 3);

                if (rows[row][num] || cols[col][num] || boxes[boxIndex][num]) {
                    return false;
                }

                rows[row][num] = cols[col][num] = boxes[boxIndex][num] = true;
            }
        }
        return true;
    }

    private boolean validateRows(String[][] board) { // O(N^2)
        for (int row = 0; row < 9; row++) {
            Set<String> set = new HashSet<>();
            for (int col = 0; col < 9; col ++) {
                if (".".equals(board[row][col])) continue;
                if (set.contains(board[row][col])) return false;
                set.add(board[row][col]);
            }
        }
        return true;
    }

    private boolean validateCols(String[][] board) { // O(N^2)
        for (int col = 0; col < 9; col ++) {
            Set<String> set = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                if (".".equals(board[row][col])) continue;
                if (set.contains(board[row][col])) return false;
                set.add(board[row][col]);
            }
        }
        return true;
    }

    private boolean validateSubBoxes(String[][] board) { // O(N^2)
        for (int row = 0; row < 9; row += 3) {
            for (int col =0; col < 9; col += 3) {
                if (!isValidSubBox(board, row, col)) return false;
            }
        }
        return true;
    }

    private boolean isValidSubBox(String[][] board, int startRow, int startCol) {
        int endRow = startRow + 3;
        int endCol = startCol + 3;
        Set<String> set = new HashSet<>();

        for (int row = startRow; row < endRow; row++) {
            for (int col = startCol; col < endCol; col++) {
                if (".".equals(board[row][col])) continue;
                if (set.contains(board[row][col])) return false;
                set.add(board[row][col]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValiSudoku solution = new ValiSudoku();
        String[][] board1 = new String[][]{
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
        };
        System.out.println(solution.isValidSudoku(board1)); // true

        String[][] board2 = new String[][]{
                {"8","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
        };
        System.out.println(solution.isValidSudoku(board2)); // false <= Since there are two 8's in the top left 3x3 sub-box, it is invalid.
    }
}
