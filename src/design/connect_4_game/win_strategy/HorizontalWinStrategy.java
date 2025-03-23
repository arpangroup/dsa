package design.connect_4_game.win_strategy;

public class HorizontalWinStrategy implements WinStrategy{
    @Override
    public boolean checkWin(char[][] grid, char disc) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] == disc &&
                    grid[row][col + 1] == disc &&
                    grid[row][col + 2] == disc &&
                    grid[row][col + 3] == disc
                ) {
                    return true;
                }
            }
        }
        return false;
    }
}
