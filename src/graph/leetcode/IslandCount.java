package graph.leetcode;

import graph.Pair;

import java.util.*;


/**
 * LeetCode: 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands
 */
public class IslandCount {
    private static final int[] delRow = new int[]{-1, 0, +1, 0};
    private static final int[] delCol = new int[]{0, +1, 0, -1};

    public int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int count = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 1 && visited[row][col] == 0) { // if land && not yet visited
                    // bfsExplore(grid, row, col, visited);
                    // count ++;

                    // if(bfs(grid, row, col, visited)) {
                    //     count ++;
                    // }

                    if(dfs(grid, row, col, visited)) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    // using bfs explore, not returned anything
    private void bfsExplore(int[][] grid, int row, int col, int[][] visited) {
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(row, col));
        visited[row][col] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int currRow = (int)pair.first;
            int currCol = (int)pair.last;

            for (int i = 0; i < 4; i++) {
                int neighbourRow = currRow + delRow[i];
                int neighbourCol = currCol + delCol[i];

                if (isValidCell(grid, neighbourRow, neighbourCol)
                        && grid[neighbourRow][neighbourCol] == 1
                        && visited[neighbourRow][neighbourCol] == 0
                ){ // if land
                    visited[neighbourRow][neighbourCol] = 1;
                    q.offer(new Pair(neighbourRow, neighbourCol));
                }
            }
        }
    }

    // using bfs which return boolean
    private boolean bfs(int[][] grid, int row, int col, int[][] visited) {
        if (grid[row][col] == '0' || visited[row][col] == 1) return false; // water; Not a new island

        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(row, col));
        visited[row][col] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int currRow = (int)pair.first;
            int currCol = (int)pair.last;

            for (int i = 0; i < 4; i++) {
                int neighbourRow = currRow + delRow[i];
                int neighbourCol = currCol + delCol[i];

                if (isValidCell(grid, neighbourRow, neighbourCol)
                        && grid[neighbourRow][neighbourCol] == 1
                        && visited[neighbourRow][neighbourCol] == 0
                ){ // if land
                    visited[neighbourRow][neighbourCol] = 1;
                    q.offer(new Pair(neighbourRow, neighbourCol));
                }
            }
        }
        return true;
    }

    // using DFS:
    private boolean dfs(int[][] grid, int row, int col, int[][] visited) {
        if (!isValidCell(grid, row, col)) return false;
        if (grid[row][col] == 0 || visited[row][col] == 1) return false; // water

        visited[row][col] = 1; // Mark as visited

        dfs(grid, row - 1, col, visited); // Up
        dfs(grid, row + 1, col, visited); // Down
        dfs(grid, row, col - 1, visited); // Left
        dfs(grid, row, col + 1, visited); // Right

        /*for (int i=0; i< 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            dfs(grid, newRow, newCol, visited);
        }*/

        return true;
    }

    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
        };

        int noOfIsland = new IslandCount().numIslands(grid);
        System.out.println("NoOfIsland: " + noOfIsland); // 4
    }
}
