package graph.leetcode;

import java.util.Arrays;


/**
 * LeetCode: 733. Flood Fill
 * https://leetcode.com/problems/flood-fill/
 *
 * Note: For very large images, the recursive approach might lead to a stack overflow.
 * Use BFS to avoid stack overflow for large image
 */
public class FloodFill {
    static final int delRow[] = {-1, 0, +1, 0};
    static final int delCol[] = {0, +1, 0, -1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] result = image;

        int initialColor = image[sr][sc];
        dfs(image, sr, sc, initialColor, color, result);
        return result;
    }

    private void dfs(int[][] image, int row, int col, int initialColor, int targetColor, int[][] result) {
        if(!isValidCell(image, row, col)) return;
        if(image[row][col] != initialColor) return;
        if(result[row][col] == targetColor) return; // result can be use as visited array

        //visited[row][col] = 1; // we use result as visited
        result[row][col] = targetColor;

        dfs(image, row-1, col, initialColor, targetColor, result);
        dfs(image, row+1, col, initialColor, targetColor, result);
        dfs(image, row, col-1, initialColor, targetColor, result);
        dfs(image, row, col+1, initialColor, targetColor, result);

    }

    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] image1 = new int[][]{
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };
        int[][] image2 = new int[][]{
                {0,0,0},
                {0,0,0}
        };
        int[][] image1Result = new FloodFill().floodFill(image1, 1, 1, 2); // [[2,2,2],[2,2,0],[2,0,1]]
        int[][] image2Result = new FloodFill().floodFill(image1, 1, 1, 2); // [[0,0,0],[0,0,0]]
        System.out.println(Arrays.deepToString(image1Result));
        System.out.println(Arrays.deepToString(image2Result));
    }
}
