package graph.leetcode;

import graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode: 695. Max Area of Island
 *  https://leetcode.com/problems/max-area-of-island
 */
public class IslandAreaSize {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int size = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 1 && visited[row][col] == 0) { // if land && not yet visited
                    int currSize = dfs(grid, visited, row, col);
                    size = Math.max(size, currSize);
                }
            }
        }
        return size;
    }

    // using DFS:
    private int dfs(int[][] grid, int[][] visited, int row, int col) {
        if (!isValidCell(grid, row, col)) return 0;
        if (grid[row][col] == 0 || visited[row][col] == 1) return 0; // water

        visited[row][col] = 1; // Mark as visited

        int size = 1;
        size += dfs(grid, visited, row - 1, col); // Up
        size += dfs(grid, visited, row + 1, col); // Down
        size += dfs(grid, visited, row, col - 1); // Left
        size += dfs(grid, visited, row, col + 1); // Right

        return size;
    }

    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][]{ // MaxSize = 0
                {0,0,0,0,0,0,0,0}
        };

        int[][] grid2 = new int[][]{ // MaxSize = 6
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        int maxArea = new IslandAreaSize().maxAreaOfIsland(grid2);
        System.out.println("maxAreaOfIsland: " + maxArea); // 6
    }
}
