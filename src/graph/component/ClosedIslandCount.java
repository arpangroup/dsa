package graph.component;

import graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/number-of-closed-islands/
// 1254. Number of Closed Islands
public class ClosedIslandCount {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[] delRow = new int[]{-1, 0, +1, 0};
        int[] delCol = new int[]{0, +1, 0, -1};

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int count = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 0 && visited[row][col] == 0) { // if land && not yet visited
//                    if(bfs(grid, row, col, visited, delRow, delCol)) {
//                        count++;
//                    }
                    count += dfs(grid, row, col, visited, delRow, delCol);
                }
            }
        }
        return count;
    }

    private boolean bfs(int[][] grid, int row, int col, int[][] visited, int[] delRow, int[] delCol) {
        Queue<Pair> q = new LinkedList<>();
        boolean isClosed = true;

        q.offer(new Pair(row, col));
        visited[row][col] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int currRow = (int)pair.first;
            int currCol = (int)pair.second;

            // Check if the current cell is on the boundary
            if (currRow == 0 || currRow == grid.length - 1 || currCol == 0 || currCol == grid[0].length - 1) {
                isClosed = false;
            }

            for (int i = 0; i < 4; i++) {
                int neighbourRow = currRow + delRow[i];
                int neighbourCol = currCol + delCol[i];


                if (isValidCell(grid, neighbourRow, neighbourCol) && grid[neighbourRow][neighbourCol] == 0 && visited[neighbourRow][neighbourCol] == 0){
                    visited[neighbourRow][neighbourCol] = 1;
                    q.offer(new Pair(neighbourRow, neighbourCol));
                }
            }
        }
        return isClosed;
    }

    private int dfs(int[][] grid, int row, int col, int[][] visited, int[] delRow, int[] delCol) {
        //if (row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1) return 0; // false
        if (!isValidCell(grid, row, col)) return 0; // false
        if (grid[row][col] == 1 || visited[row][col] == 1) return 1; // If the current cell is water or already visited, return 1 (part of a closed island so far)

        visited[row][col] = 1;
        int bottom = dfs(grid, row + 1, col, visited, delRow, delCol);
        int top = dfs(grid, row - 1, col, visited, delRow, delCol);
        int right = dfs(grid, row, col + 1, visited, delRow, delCol);
        int left = dfs(grid, row , col - 1, visited, delRow, delCol);
        return (top == 1 && bottom == 1 && left == 1 && right == 1) ? 1 : 0;
    }

    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };

        int noOfIsland = new ClosedIslandCount().closedIsland(grid);
        System.out.println("ClosedIsland: " + noOfIsland);
    }

}
