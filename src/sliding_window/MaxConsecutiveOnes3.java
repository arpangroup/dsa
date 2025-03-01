package sliding_window;

public class MaxConsecutiveOnes3 {
    public int longestOnes(int[] nums, int k) {

    }

    public static void main(String[] args) {
        MaxConsecutiveOnes3 solution = new MaxConsecutiveOnes3();
        int[][] inputs = new int[][]{
                {1,1,1,0,0,0,1,1,1,1,0},
                {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}
        };
        int[] k = new int[]{
                2,
                3
        };
        int[] outputs = new int[]{
                6,
                10
        };

        for (int i=0; i< inputs.length; i++) {
            System.out.println(solution.longestOnes(inputs[i], k[i]));
        }
    }
}
