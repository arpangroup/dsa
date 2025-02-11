package graph.leetcode;

import graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode: 1254. Number of Closed Islands
 *  https://leetcode.com/problems/number-of-closed-islands/
 */
public class ClosedIslandCount {
    static final int[] delRow = new int[]{-1, 0, +1, 0};
    static final int[] delCol = new int[]{0, +1, 0, -1};

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int count = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 0 && visited[row][col] == 0) { // if land && not yet visited
//                    if(bfs(grid, row, col, visited)) {
//                        count++;
//                    }
                    if (dfs(grid, visited, row, col)) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }


    public int closedIslandV1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];

        int count = 0;
        for(int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                if (grid[row][col] == 1 && visited[row][col] == 0) { // if land && not yet visited
                    /*MutableBoolean hasCornerCell = new MutableBoolean(false);
                    dfsV1(grid, row, col, visited, hasCornerCell);
                    if (!hasCornerCell.value) count ++;*/

                    if (dfsV2(grid, visited, row, col)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean bfs(int[][] grid, int row, int col, int[][] visited) {
        Queue<Pair> q = new LinkedList<>();
        boolean isClosed = true;

        q.offer(new Pair(row, col));
        visited[row][col] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int currRow = (int)pair.first;
            int currCol = (int)pair.last;

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

    private boolean dfs(int[][] grid, int[][] visited, int row, int col) {
        if (!isValidCell(grid, row, col)) return false;
        if (grid[row][col] == 0 || visited[row][col] == 1) return true; // 0=>water; 1=>Land
        if (isCornerCell(grid, row, col)) return false; // orders matter, we cant write boundary condition before returning true

        visited[row][col] = 1;

        // If the current cell is on the boundary and it's land, it's not a closed island
        boolean isClosed = true;
        if (row == 0 || row == grid.length - 1 || col == 0 || col == grid[0].length - 1) {
            isClosed = false;
        }

        // Recursively check all four directions
        boolean top    = dfs(grid, visited, row - 1, col);
        boolean bottom = dfs(grid, visited, row + 1, col);
        boolean left   = dfs(grid, visited, row, col - 1);
        boolean right  = dfs(grid, visited, row, col + 1);

        // If any of the recursive calls reach the boundary, it's not a closed island
        return isClosed && top && bottom && left && right;
    }

    private void dfsV1(int[][] grid, int row, int col, int[][] visited, MutableBoolean hasCornerCell) {
        if (!isValidCell(grid, row, col)) return;
        if (visited[row][col] == 1 || grid[row][col] == 1) return; // water=1=> skip

        hasCornerCell.value = (row == 0 || col == 0 ||  row == grid.length - 1 || col == grid[0].length - 1);
        visited[row][col] = 1;

        dfsV1(grid, row-1, col, visited, hasCornerCell);
        dfsV1(grid, row, col+1, visited, hasCornerCell);
        dfsV1(grid, row+1, col, visited, hasCornerCell);
        dfsV1(grid, row, col-1, visited, hasCornerCell);
    }

    private boolean dfsV2(int[][] graph, int[][] visited, int row, int col) {
        if(!isValidCell(graph, row, col)) return false;
        if(isCornerCell(graph, row, col)) return false;
        if(graph[row][col] == 0 || visited[row][col] == 1) return true; //  water OR already visited

        visited[row][col] = 1;

        dfsV2(graph, visited, row - 1, col);
        dfsV2(graph, visited, row, col + 1);
        dfsV2(graph, visited, row + 1, col);
        dfsV2(graph, visited, row, col - 1);

        return true;
    }

    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private boolean isCornerCell(int[][] grid, int row, int col) {
        return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 0, 0, 0, 0, 1, 1, 0},
//                {1, 0, 1, 0, 1, 1, 1, 0},
//                {1, 0, 0, 0, 0, 1, 0, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0}
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

//        int noOfIsland = new ClosedIslandCount().closedIsland(grid); // 2
        int noOfIsland = new ClosedIslandCount().closedIslandV1(grid);
        System.out.println("ClosedIsland: " + noOfIsland); // 2
    }

    static class MutableBoolean {
        public boolean value;

        public MutableBoolean(boolean value) {
            this.value = value;
        }
    }

}
