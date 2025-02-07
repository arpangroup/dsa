package graph.shortest_path;

import graph.Cell;

import java.util.*;

public class BinaryMaze {
    // Directions for moving in the grid: up, right, down, left
    private static final int[] DELTA_ROW = {-1, 0, 1, 0}; // (row-1, col) (row+1, col)
    private static final int[] DELTA_COL = {0, 1, 0, -1}; // (row, col+1) (row, col-1)

    public static int shortestPath(int[][] grid, int[] source, int[] destination) {
        int rows = grid.length;
        int cols = grid[0].length;
        int srcRow = source[0];
        int srcCol = source[1];
        int dstRow = destination[0];
        int dstCol = destination[1];

        // If source and destination are the same
        if (srcRow == dstRow && srcCol == dstCol) {
            return 0;
        }


        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.distance)); // Priority queue to prioritize cells with the smallest distance
        Integer[][] dist = new Integer[grid.length][grid[0].length]; // Distance matrix to store the shortest distance to each cell
        for (int i = 0; i < grid.length; i++) Arrays.fill(dist[i], Integer.MAX_VALUE); // Initialize all distances to infinity

        // Start from the source cell
        dist[srcRow][srcCol] = 0;
        pq.add(new Cell(srcRow, srcCol, 0));

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            int currentRow  = current.row;
            int currentCol  = current.col;
            int currentDistance = current.distance;

            // Explore all 4 possible directions
            for (int i = 0; i < 4; i++) { // there are 4 possible directions: up, down, left, right
                int newRow = currentRow  + DELTA_ROW[i];
                int newCol = currentCol  + DELTA_COL[i];

                // Check if the new cell is within bounds and traversable
                if (isValidCell(newRow, newCol, rows, cols) && grid[newRow][newCol] == 1) {
                    int newDistance = currentDistance + 1;

                    // If a shorter path is found, update the distance and add to the queue
                    if (newDistance < dist[newRow][newCol]) {
                        dist[newRow][newCol] = newDistance;

                        // If the destination is reached, return the distance
                        if (newRow == dstRow && newCol == dstCol) {
                            return newDistance;
                        }
                    }
                    pq.offer(new Cell(newRow, newCol, newDistance));
                }
            }
        }
        // If the destination is unreachable, return -1
        return -1;
    }

    // Helper method to check if a cell is within the grid bounds
    private static boolean isValidCell(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1},
        };
    }

}
