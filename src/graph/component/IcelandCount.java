package graph.component;

import java.util.*;

public class IcelandCount {
    public int countIcelandBFS(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        int count = 0;
        for (int row=0; row< grid.length; row++) {
            for (int col = 0; col < grid[0].length; col ++) {
                count ++;
                bfs(grid, row, col, visited);
            }
        }
        return count;
    }

    void bfs(int[][] grid, int row, int col, int[][] visited) {
       Queue<Pair> q = new LinkedList<>(); // {row, col}
       int rows = grid.length;
       int cols = grid[0].length;

       visited[row][col] = 1;
       q.offer(new Pair(row, col));

       while (!q.isEmpty()) {
           Pair it = q.poll();
           int currRow = it.row;
           int currCol = it.col;

           // traverse in the neighbours and mark them if its a land
           for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int nRow = currRow + delRow;
                    int nCol = currCol + delCol;
                    if (isValidCell(nRow, nCol, rows, cols)
                            && grid[nRow][nCol] == 1
                            && visited[nRow][nCol] == 0
                    ) {
                        visited[nRow][nCol] = 1;
                        q.offer(new Pair(nRow, nCol));
                    }
                }
           }
       }
    }

    int countIcelandDFS(int[][] grid) {
        Set<String> visited = new HashSet<>();// int[][] visited;
        int count = 0;

        for (int r=0; r< grid.length; r++) {
            for (int c=0; c<grid[0].length; c++) {
                if (exploreDFS(grid, r, c, visited)) {
                    count ++;
                }
            }
        }
        return count;
    }

    boolean exploreDFS(int[][] grid, int row, int col, Set<String> visited) {
        if (isValidCell(row, col, grid.length, grid[0].length)) return false;
        if (grid[row][col] == 0) return false; // water

        String pos = row + "," + col;
        if (visited.contains(pos)) return false; // its not a new iceland
        visited.add(pos);

        exploreDFS(grid, row - 1, col, visited);
        exploreDFS(grid, row + 1, col, visited);
        exploreDFS(grid, row, col - 1, visited);
        exploreDFS(grid, row, col + 1, visited);

        return true;
    }

    private static boolean isValidCell(int row, int col, int rows, int cols) {
        //boolean rowInbounds = row >= 0 && row < rows;
        //boolean colInbounds = col >= 0 && col < cols;
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    static class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
