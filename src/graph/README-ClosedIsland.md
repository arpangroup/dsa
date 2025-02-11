## Island Problems:
1. Number of Islands
2. Number of Closed Islands
3. Number of Islands in a Circular Matrix
4. Max Area of Island
5. Island Perimeter
6. **Distinct Island** 
7. Count Sub-island
8. [Making A Large Island](https://www.geeksforgeeks.org/problems/making-a-large-island/1)
9. [Unit Area of largest region of 1's](https://www.geeksforgeeks.org/problems/length-of-largest-region-of-1s-1587115620/1)
10. 
11. [Minimum number of Water to Land conversion to make two islands connected in a Grid](https://www.geeksforgeeks.org/minimum-number-of-water-to-land-conversion-to-make-two-islands-connected-in-a-grid/?ref=asr1)
12. [Number of Islands after changing given cell for K queries](https://www.geeksforgeeks.org/number-of-islands-after-changing-given-cell-for-k-queries/?ref=asr3)



## 1. Number of Islands - [GFG](https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1) | [LC-200](https://leetcode.com/problems/number-of-islands)


````java
private void dfs(int[][] grid, int[][] visited, int row, int col) {
    if (!isValidCell(grid, row, col)) return; // return false;
    if (grid[row][col] == 0 || visited[row][col] == 1) return; // water ==> return false

    visited[row][col] = 1; // Mark as visited

    dfs(grid, visited, row - 1, col); // Up
    dfs(grid, visited, row, col + 1); // Right 
    dfs(grid, visited, row + 1, col); // Down 
    dfs(grid, visited, row, col - 1); // Left
    //return true;

    /*for (int i=0; i< 4; i++) {
        int newRow = row + delRow[i];
        int newCol = col + delCol[i];
        dfs(grid, visited, newRow, newCol);
    }*/
}

private boolean isValidCell(int[][] grid, int row, int col) {
    return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
}
````

## 2. Number of Closed Islands - [GFG](https://www.geeksforgeeks.org/problems/find-number-of-closed-islands/1) |  [LC-1254](https://leetcode.com/problems/number-of-closed-islands)

### 2.1. Approach1 (incorrect):

````java
private boolean dfs(int[][] graph, int[][] visited, int row, int col) {
        if(!isValidCell(graph, row, col)) return false;
        if(isCornerCell(graph, row, col)) return false;
        if(graph[row][col] == 0 || visited[row][col] == 1) return false; // if water OR already visited
        
        visited[row][col] = 1;
        
        dfs(graph, visited, row - 1, col);
        dfs(graph, visited, row + 1, col);
        dfs(graph, visited, row, col + 1);
        dfs(graph, visited, row, col - 1);
        
        return true;
    }
    
    private boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >=0 && col < grid[0].length;
    }
    
    private boolean isCornerCell(int[][] grid, int row, int col) {
        return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
    }
````

Testcase1 (PASSED):
````console 
mat[][] = {
    {0, 0, 0, 0, 0, 0, 0, 1}, 
    {0, 1, 1, 1, 1, 0, 0, 1}, 
    {0, 1, 0, 1, 0, 0, 0, 1}, 
    {0, 1, 1, 1, 1, 0, 1, 0}, 
    {1, 0, 0, 0, 0, 1, 0, 1}
}
Expected Output: 2
our Output: 2
````

Testcase2 (PASSED):
````console 
mat[][] = {
    {1, 0, 0},
    {0, 1, 0},
    {0, 0, 1}
}
Expected Output: 1
our Output: 1
````

Testcase3 (FAILED):
````console 
mat[][] = {
    {1, 0, 0, 1, 0, 0, 1}
    {0, 0, 1, 1, 0, 1, 0}
    {1, 0, 1, 1, 1, 0, 1}
    {1, 0, 1, 1, 0, 1, 1}
    {1, 0, 1, 0, 0, 1, 1}
    {1, 1, 1, 1, 0, 0, 1}
    {0, 0, 0, 0, 0, 0, 0}
    {0, 0, 1, 0, 1, 0, 0}
}
Expected Output: 1
our Output: 3
````

### Why Testcase3 Failed?

Our code has a few issues that prevent it from correctly solving the Closed Island problem:
- We should not return false immediately when encountering a boundary (`isCornerCell`).
- Instead, if an island reaches the boundary, mark it and return `false`, but continue the DFS.

Correct code:
````java
private boolean dfs(int[][] grid, int[][] visited, int row, int col) {
    if (!isValidCell(grid, row, col)) return false;  // Out of bounds
    if (grid[row][col] == 0 || visited[row][col] == 1) return true;  // Water or already visited

    visited[row][col] = 1;

    boolean isClosed = true; // Assume it's closed initially

    if (isCornerCell(grid, row, col)) {
        isClosed = false;  // If touches boundary, it's not closed
    }

    // DFS in all 4 directions
    isClosed &= dfs(grid, visited, row - 1, col);
    isClosed &= dfs(grid, visited, row + 1, col);
    isClosed &= dfs(grid, visited, row, col - 1);
    isClosed &= dfs(grid, visited, row, col + 1);

    return isClosed;
}

private boolean isCornerCell(int[][] grid, int row, int col) {
    return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
}
````

### 2.2. Approach2 (Incorrect):
call DFS for the cells with value ‘1’  which are not on the corners
````java
public int closedIslandV1(int[][] grid) {
    ....

    MutableBoolean hasCornerCell = new MutableBoolean(false);
    dfsV1(grid, row, col, visited, hasCornerCell);
    if (!hasCornerCell.value) count ++;
}


private void dfs(int[][] grid, int[][] visited, int row, int col, MutableBoolean hasCornerCell) {
    if (!isValidCell(grid, row, col)) return;
    if (visited[row][col] == 1 || grid[row][col] == 1) return; // water=1=> skip

    hasCornerCell.value = row == 0 || col == 0 ||  row == grid.length - 1 || col == grid[0].length - 1;
    visited[row][col] = 1;

    dfs(grid, visited, row-1, col, hasCornerCell);
    dfs(grid, visited, row+1, col, hasCornerCell);
    dfs(grid, visited, row, col+1, hasCornerCell);
    dfs(grid, visited, row, col-1, hasCornerCell);
}

static class MutableBoolean {
    public boolean value;

    public MutableBoolean(boolean value) {
        this.value = value;
    }
}
````
There's a **critical logic issue** in updating `hasCornerCell.value`.

- This line:
    ````java
    hasCornerCell.value = row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
    ````
    Overwrites hasCornerCell.value instead of preserving previous true values.
- If a later recursive DFS call finds a corner cell, the earlier recursive calls won't retain that info.


## Final Correct answer for Closed Island problem:
````java
private boolean dfs(int[][] grid, int[][] visited, int row, int col) {
  if (!isValidCell(grid, row, col)) return false;
  if (grid[row][col] == 0 || visited[row][col] == 1) return true; // 0=>water; 1=>Land
  if (isCornerCell(grid, row, col)) return false; // orders matter, we cant write boundary condition before returning true

  visited[row][col] = 1;

  // Recursively check all four directions
  boolean top    = dfs(grid, visited, row - 1, col);
  boolean bottom = dfs(grid, visited, row + 1, col);
  boolean left   = dfs(grid, visited, row, col - 1);
  boolean right  = dfs(grid, visited, row, col + 1);

  // If any of the recursive calls reach the boundary, it's not a closed island
  return top && bottom && left && right;
}

private boolean isValidCell(int[][] grid, int row, int col) {
  return row >= 0 && row < grid.length && col >=0 && col < grid[0].length;
}

private boolean isCornerCell(int[][] grid, int row, int col) {
  return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
}
````
**Q1. why we are returning true when we found water `grid[row][col] == 0`?**
- When DFS encounters water `(0)`, it signifies that the land `(1)` hasn't reached an open boundary
- Returning `true` ensures that this path does not contribute to an "open" condition

- **If we return `false` instead:**
  - Any path touching water would make the entire island "open" even if it's actually enclosed.

**Q2. Why does changing the order break the code?** <br/>
When DFS exploring a valid land cell `(1)`, it will:
1. Check if it's on the boundary
2. If it is, return false (indicating an open island)
3. Even if there's a water boundary `(0)` next to it, DFS never gets a chance to check that.


## 3. Number of Islands in a Circular Matrix - [GFG](https://www.geeksforgeeks.org/number-of-islands-in-a-circular-matrix/)
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20231011162754/Islands-768.png"/>

Considering the map's cyclic nature. 
- Start from an unvisited land cell, 
- it explores adjacent cells while cyclically wrapping around the map. 
- simply modulo the row and column with the total number of rows and columns respectively to land inside the matrix.

> Note: Cyclic Map: Whenever we move outside the matrix, then we can simply modulo the row and column with the total number of rows and columns respectively** to land inside the matrix.

````java
private void dfs(char[][] worldMap, int row, int col, int rows, int cols) {
    worldMap[row][col] = 'W';

    for (int[] dir : directions) {
        // Cyclically wrap around if needed
        int newRow = (row + dir[0] + rows) % rows;
        int newCol = (col + dir[1] + cols) % cols;

        if (worldMap[newRow][newCol] == 'L') {
            // Recursively explore adjacent land cells
            dfs(worldMap, newRow, newCol, rows, cols);
        }
    }
}
````


## 4. Max Area of Island - [LC-695](https://leetcode.com/problems/max-area-of-island)
````java
private int dfs(int[][] grid, int[][] visited, int row, int col) {
    if (!isValidCell(grid, row, col)) return 0;
    if (grid[row][col] == 0 || visited[row][col] == 1) return 0; // water

    visited[row][col] = 1;

    int size = 1;
    size += dfs(grid, visited, row - 1, col); // Up
    size += dfs(grid, visited, row + 1, col); // Down
    size += dfs(grid, visited, row, col - 1); // Left
    size += dfs(grid, visited, row, col + 1); // Right

    return size;
}

private boolean isValidCell(int[][] grid, int row, int col) {
  return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
}
````

## 5. Island Perimeter - [LC-463](https://leetcode.com/problems/island-perimeter)

## 6. Distinct Island - [GFG](https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1)

## 7. Count Sub-island - [LC-1905](https://leetcode.com/problems/count-sub-islands/)