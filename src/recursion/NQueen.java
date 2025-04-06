package recursion;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 51
    Leetcode Link               : https://leetcode.com/problems/n-queens/description/
    Description: 'Q' and '.' both indicate a queen and an empty space, respectively.
*/
public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');
        dfs(0, board, result);
        return result;
    }

    private void dfs(int col, char[][] board, List<List<String>> result) {
        if (col == board.length) {
            result.add(construct(board));
            return;
        }

        // try every row for each col
        for (int row = 0; row < board.length; row++) { // O(N)
            if (isSafeV1(board, row, col)) { // O(3N)
                board[row][col] = 'Q';
                dfs(col + 1, board, result);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafeV1(char[][] board, int row, int col) { // O(3N)
        final int tempRow = row;
        final int tempCol = col;

        // Check Upper-Left Diagonal
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row--;
            col--;
        }

        // Check Lower-Down Diagonal
        row = tempRow;
        col = tempCol;
        while (row < board.length && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row++;
            col--;
        }

        // Check Left sides
        row = tempRow;
        col = tempCol;
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List < String > res = new LinkedList < String > ();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        NQueen solution = new NQueen();
        System.out.println(solution.solveNQueens(4)); // [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        System.out.println(solution.solveNQueens(1)); // [["Q"]]
    }
}
