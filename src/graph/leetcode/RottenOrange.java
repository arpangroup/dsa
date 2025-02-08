package graph.leetcode;

import graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOrange {
    private static final int[] delRow = {-1, 0, +1, 0};
    private static final int[] delCol = {0, +1, 0, -1};

    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int freshOranges = 0;
        Queue<Pair> q = new LinkedList<>();

        int timeEllapsed = 0;
        for(int row=0; row< grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 2) {// not yet visited && rotten
                    q.offer(new Pair(row, col)); // we need to start processing of all the rotten orange from same time
                } else if(grid[row][col] == 1) {
                    freshOranges++;
                }
            }
        }

        if(freshOranges == 0) return 0;
        return bfs(grid, q, freshOranges);
    }

    int bfs(int[][] grid, Queue<Pair> q, int freshOranges) {
        int timeEllapsed = 0;

        while(!q.isEmpty()) {
            int queSize = q.size();
            boolean rotted = false;

            for(int i=0; i< queSize; i ++ ) {
                Pair pair = q.poll();
                int currRow = (int)pair.first;
                int currCol = (int)pair.last;

                for(int d=0; d< 4; d++) {
                    int newRow = currRow + delRow[d];
                    int newCol = currCol + delCol[d];

                    if(isValidCell(grid, newRow, newCol) && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Mark as rotten
                        q.offer(new Pair(newRow, newCol));
                        freshOranges--;
                        rotted = true;
                    }
                }
            }
            if(rotted) timeEllapsed += 1;
        }
        return freshOranges == 0 ? timeEllapsed : -1; // If fresh oranges remain, return -1

    }

    boolean isValidCell(int[][] grid, int row, int col) {
        return row >=0 && row < grid.length && col >=0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][] {{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid2 = new int[][] {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid3 = new int[][] {{0, 2}};

        int timeTaken = new RottenOrange().orangesRotting(grid1);
        System.out.println("timeTaken: " + timeTaken);
    }
}
