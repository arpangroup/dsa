package sliding_window;

// LeetCode:53
// https://leetcode.com/problems/maximum-subarray/
public class MaxSumSubArray_Kadane {
    public int maxSubArrayV1(int[] nums) {
        int left = 0, right = 0;

        int maxSum = Integer.MIN_VALUE, sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            maxSum = Math.max(maxSum, sum);


            if (sum < 0) {
                sum = 0;
                left = right + 1;
            }
            right++;
        }
        return maxSum;
    }

    public int maxSubArrayV2(int[] nums) {
        int maxSum = Integer.MIN_VALUE, sum = 0;

        for (int i=0; i< nums.length; i++) {
            sum += nums[i];

            if (sum < 0) sum = 0;
            if (sum > maxSum) maxSum = sum; // maxSum = max(maxSum, sum)
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumSubArray_Kadane solution = new MaxSumSubArray_Kadane();
        int[][] input = new int[][]{
                {-2,1,-3,4,-1,2,1,-5,4},
                {1},
                {5,4,-1,7,8}
        };
        int[] output = new int[]{
          6,
          1,
          23
        };

//        for (int[] nums : input) {
//            System.out.println(solution.maxSubArray(nums));
//        }
        System.out.println(solution.maxSubArrayV2(input[0]));
    }
}
