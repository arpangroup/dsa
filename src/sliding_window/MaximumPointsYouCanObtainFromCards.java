package sliding_window;

public class MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        int left = k-1;
        int right = cardPoints.length - 1;

        int leftSum = 0;
        int rightSum = 0;
        for (int i=0; i< k; i++) leftSum += cardPoints[i];

        int totalSum = leftSum + rightSum;
        while (left >=0 && right >= 0) {
            leftSum -= cardPoints[left];
            left--;

            rightSum += cardPoints[right];
            right --;

            totalSum = Math.max(totalSum, (leftSum + rightSum));
        }


        return totalSum;
    }

    public static void main(String[] args) {
        MaximumPointsYouCanObtainFromCards solution = new MaximumPointsYouCanObtainFromCards();
        int[][] inputs = new int[][]{
                {6, 2, 3, 4, 7, 2, 1, 7, 1},
                {1,2,3,4,5,6,1},
                {2,2,2},
                {9,7,7,9,7,7,9}
        };
        int[] k = new int[]{
                4,
                3,
                2,
                7
        };
        int[] outputs = new int[]{
                16,
                12,
                4,
                55
        };


        for (int i=0; i< inputs.length; i++) {
            System.out.println(solution.maxScore(inputs[i], k[i]));
        }
    }
}
