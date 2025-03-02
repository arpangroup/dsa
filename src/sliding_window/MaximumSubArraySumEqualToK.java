package sliding_window;

import java.util.Arrays;

public class MaximumSubArraySumEqualToK {
    public int maxSubarraySumItr(int[] arr, int k) {
        int max = Integer.MIN_VALUE;

        int start = -1;

        for(int i=0; i< arr.length; i++) { // O(N)
            int sum = 0;
            for(int j=i; j< i+k; j++) { // O(K)
                if (j >= arr.length) continue;
                sum += arr[j];
            }
            int prevMax = max;
            max = Math.max(max, sum);

            if (max > prevMax) start = i;
        }
        System.out.println("Start: " + start + ", End: " + (start + k - 1));
        return max;
    }

    public int maxSubarraySum(int[] arr, int k) {
        int left = 0;
        int right = left;

        int max = Integer.MIN_VALUE;
        int sum = 0;
        while(right < arr.length){
            int windowSize = right - left + 1;
            sum += arr[right];
            right++;

            if (windowSize == k){
                max = Math.max(max, sum); // calculate max when windowSize reached
                sum -= arr[left];
                left++;
            }
            /*if (right - left + 1 <= k) {
                sum += arr[right];
                max = Math.max(max, sum);
                right ++;
            } else {
                sum -= arr[left];
                left = left+1;
            }*/
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSubArraySumEqualToK solution = new MaximumSubArraySumEqualToK();
        int[] arr = new int[]{2, 3, 5, 2, 9, 7, 1};
        int k = 3;

        System.out.println(solution.maxSubarraySum(arr, k));
    }
}
