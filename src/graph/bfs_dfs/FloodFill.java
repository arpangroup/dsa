package graph.bfs_dfs;

public class FloodFill {
    static final int delRow[] = {-1, 0, +1, 0};
    static final int delCol[] = {0, +1, 0, -1};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // get initial color
        int iniColor = image[sr][sc];
        int[][] ans = image;
        dfs(sr, sc, ans, image, newColor, iniColor);
        return ans;
    }

    private void dfs(int row, int col,
                     int[][] ans,
                     int[][] image,
                     int newColor,
                     int iniColor) {
        // color with new color
        ans[row][col] = newColor;
        int n = image.length;
        int m = image[0].length;
        // there are exactly 4 neighbours
        for(int i = 0;i<4;i++) {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];
            // check for valid coordinate
            // then check for same initial color and unvisited pixel
            if(nrow>=0 && nrow<n && ncol>=0 && ncol < m &&
                    image[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
                dfs(nrow, ncol, ans, image, newColor, delRow, delCol, iniColor);
            }
        }
    }
}
