package design.connect_4_game;

public class Board {
    private static Board INSTANCE;
    private final int rows = 6;
    private final int cols = 7;
    private char[][] grid;

    private Board() {
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public static Board getInstance() {
        if (INSTANCE == null) INSTANCE = new Board();
        return INSTANCE;
    }

    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean dropDisc(int col, char disc) {
        if (col < 0 || col >= cols) return false;
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][col] == '-') {
                grid[row][col] = disc;
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == '.') {
                return false;
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return this.grid;
    }
}
