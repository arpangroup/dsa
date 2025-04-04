package recursion;

import java.util.*;

/*
    Company Tags                :
    ProblemLink                 : GFG
    Problem Link                : https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    Description:
        Consider a rat placed at position (0, 0) in an n x n square matrix mat.
        The rat's goal is to reach the destination at position (n-1, n-1). The rat can move in
        four possible directions: 'U'(up), 'D'(down), 'L' (left), 'R' (right).

        The matrix contains only two possible values:
            - 0: A blocked cell through which the rat cannot travel.
            - 1: A free cell that the rat can pass through.

        Note: In a path, no cell can be visited more than one time. If the source cell is 0,
              the rat cannot move to any other cell. In case of no path, return an empty list.+

        The task is to find all possible paths the rat can take to reach the destination, starting from (0, 0)
        and ending at (n-1, n-1), under the condition that the rat cannot revisit any cell along the same path.
        Furthermore, the rat can only move to adjacent cells that are within the bounds of the matrix and not blocked.

Return the final result vector in lexicographically smallest order.

*/
public class RatInMaze {
    public ArrayList<String> findPath(ArrayList<ArrayList<Integer>> mat) {
        return null;
    }


    private static ArrayList<ArrayList<Integer>> convert(int[][] mat) {
        ArrayList<ArrayList<Integer>> matList = new ArrayList<>();

        for (int i = 0; i < mat.length; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < mat[i].length; j++) {
                row.add(mat[i][j]);
            }
            matList.add(row);
        }
        return matList;
    }

    public static void main(String[] args) {
        RatInMaze solution = new RatInMaze();

        int[][] mat = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };
        //The rat can reach the destination at (3, 3) from (0, 0)
        // by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.
        System.out.println(solution.findPath(convert(mat))); // ["DDRDRR", "DRDDRR"]

        mat = new int[][]{
                {1, 0},
                {1, 0}
        };
        System.out.println(solution.findPath(convert(mat))); // [] <== No path exists and the destination cell is blocked.

        mat = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        System.out.println(solution.findPath(convert(mat))); // ["DDRR", "RRDD"]
    }


}
