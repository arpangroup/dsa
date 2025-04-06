package recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    Company Tags                :
    LeetCode                    : 37
    Leetcode Link               : https://leetcode.com/problems/sudoku-solver/
    Description: A sudoku solution must satisfy all of the following rules:
        1. Each of the digits 1-9 must occur exactly once in each row.
        2. Each of the digits 1-9 must occur exactly once in each column.
        3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    The '.' character indicates empty cells.
*/
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] != '.') continue;

                // try to fill with 1-9 in the emmpty cell
                for(char c = '1'; c <= '9'; c++) {
                    if(isValid(board, row, col, c)) {
                        board[row][col] = c;

                        if(solve(board)) return true;
                        board[row][col] = '.'; // backtrack
                    }
                }
                return false; // no valid number found
            }
        }
        return true; // board solved
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        int subGridRowStart = 3 * (row / 3);
        int subGridColStart = 3 * (col / 3);

        for(int i = 0; i < 9; i++) {
            // Check for row
            if(board[row][i] == c) return false;

            // Check for column
            if(board[i][col] == c) return false;

            // Check 3x3 subgrid
            //if(board[3*(row / 3) + i / 3][3 * (col / 3) + (i % 3)] == c) return false;
            int subRow = subGridRowStart + i / 3; // <== i / 3 gives the row offset (0 to 2),
            int subCol = subGridColStart + i % 3; // <== i % 3 gives the column offset (0 to 2).
            if (board[subRow][subCol] == c) return false; // subgrid
        }
        return true;
    }


    public static void main(String[] args) {
        SudokuSolver solution = new SudokuSolver();
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solution.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));

        /* Output:
            [
                ["5","3","4","6","7","8","9","1","2"],
                ["6","7","2","1","9","5","3","4","8"],
                ["1","9","8","3","4","2","5","6","7"],
                ["8","5","9","7","6","1","4","2","3"],
                ["4","2","6","8","5","3","7","9","1"],
                ["7","1","3","9","2","4","8","5","6"],
                ["9","6","1","5","3","7","2","8","4"],
                ["2","8","7","4","1","9","6","3","5"],
                ["3","4","5","2","8","6","1","7","9"]
             ]

         */
    }
}
