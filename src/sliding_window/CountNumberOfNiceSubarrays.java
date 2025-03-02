package sliding_window;

public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
        CountNumberOfNiceSubarrays solution = new CountNumberOfNiceSubarrays();
        int[][] inputs = new int[][]{
                {1,1,2,1,1},
                {2,4,6},
                {2,2,2,1,2,2,1,2,2,2}
        };
        int[] k = new int[]{
          3,
          1,
          2
        };

        int[] outputs = new int[]{
                2,
                0,
                16
        };

        for (int i=0; i< inputs.length; i++) {
            System.out.println(solution.numberOfSubarrays(inputs[i], k[i]));
        }
    }
}
