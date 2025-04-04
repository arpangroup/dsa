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
public class SudokuSolver {
    public void solveSudoku(String[][] board) {


    }


    public static void main(String[] args) {
        SudokuSolver solution = new SudokuSolver();
        String[][] board = new String[][]{
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
        solution.solveSudoku(board);
    }
}
