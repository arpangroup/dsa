package heap;

import java.util.Arrays;

public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {

        return new int[][]{};
    }

    public static void main(String[] args) {
        KClosestPointToOrigin solution = new KClosestPointToOrigin();
        int[][] points = new int[][]{{1,3}, {-2, 2}};
        int k = 1;
        System.out.println(Arrays.deepToString(solution.kClosest(points, k))); // [[-2,2]]; dist((0,0), (1,3))=sqrt(10); dist((0,0), (-2,2))=sqrt(8)

        points = new int[][]{{3,3}, {5, -1}, {-2,4}};
        k = 2;
        System.out.println(Arrays.deepToString(solution.kClosest(points, k))); // [[3,3],[-2,4]]
    }
}
