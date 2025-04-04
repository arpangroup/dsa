package recursion;

import java.util.*;

/*
    Company Tags                :
    ProblemLink                 : GFG
    Problem Link                : https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
    Description:
        You are given an undirected graph consisting of v vertices and a list of edges, along with an integer m.

        Your task is to determine whether it is possible to color the graph using
        at most m different colors such that no two adjacent vertices share the same color
*/
public class MColoringProblem {
    boolean graphColoring(int v, List<int[]> edges, int m) {
        return false;
    }

    public static void main(String[] args) {
        MColoringProblem solution = new MColoringProblem();

        int v = 4;
        List<int[]> edges = List.of(
                new int[]{0,1},
                new int[]{1,2},
                new int[]{2,3},
                new int[]{3,0},
                new int[]{0,2}
        );
        int m = 3;
        System.out.println(solution.graphColoring(v, edges, m)); // true

        v = 3;
        edges = List.of(
                new int[]{0,1},
                new int[]{1,2},
                new int[]{0,2}
        );
        m = 2;
        System.out.println(solution.graphColoring(v, edges, m)); // false
    }
}
