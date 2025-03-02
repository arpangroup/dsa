package sliding_window;

public class MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        return 0;
    }

    public static void main(String[] args) {
        MaximumPointsYouCanObtainFromCards solution = new MaximumPointsYouCanObtainFromCards();
        int[][] inputs = new int[][]{
                {1,2,3,4,5,6,1},
                {2,2,2},
                {9,7,7,9,7,7,9}
        };
        int[] k = new int[]{
                3,
                2,
                7
        };
        int[] outputs = new int[]{
                12,
                4,
                55
        };

        for (int i=0; i< inputs.length; i++) {
            System.out.println(solution.maxScore(inputs[i], k[i]));
        }
    }
}
