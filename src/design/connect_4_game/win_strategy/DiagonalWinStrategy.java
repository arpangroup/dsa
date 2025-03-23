package design.connect_4_game.win_strategy;

public class DiagonalWinStrategy implements WinStrategy{
    @Override
    public boolean checkWin(char[][] grid, char disc) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row + 1][col + 1] == disc &&
                    grid[row + 2][col + 2] == disc &&
                    grid[row + 3][col + 3] == disc
                ) {
                    return true;
                }
            }
        }

        for (int row = 3; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] == disc &&
                    grid[row - 1][col + 1] == disc &&
                    grid[row - 2][col + 2] == disc &&
                    grid[row - 3][col + 3] == disc
                ) {
                    return true;
                }
            }
        }
        return false;
    }
}
