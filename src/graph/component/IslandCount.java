package graph.component;

import graph.Pair;

import java.util.*;

public class IslandCount {
    public int countIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[] delRow = new int[]{-1, 0, +1, 0};
        int[] delCol = new int[]{0, +1, 0, -1};

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int count = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 1 && visited[row][col] == 0) { // if land && not yet visited
                    bfs(grid, row, col, visited, delRow, delCol);
                    count ++;
                }
            }
        }
        return count;
    }

    private void bfs(int[][] grid, int row, int col, int[][] visited, int[] delRow, int[] delCol) {
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(row, col));
        visited[row][col] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int currRow = (int) pair.first;
            int currCol = (int) pair.second;

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

        int noOfIsland = new IslandCount().countIsland(grid);
        System.out.println("NoOfIsland: " + noOfIsland);
    }
}
