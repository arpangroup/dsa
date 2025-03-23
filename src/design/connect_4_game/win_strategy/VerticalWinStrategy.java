package design.connect_4_game.win_strategy;

public class VerticalWinStrategy implements WinStrategy{
    @Override
    public boolean checkWin(char[][] grid, char disc) {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 3; row++) {
                if (grid[row][col] == disc &&
                    grid[row + 1][col] == disc &&
                    grid[row + 2][col] == disc &&
                    grid[row + 3][col] == disc
                ) {
                    return true;
                }
            }
        }
        return false;
    }
}
