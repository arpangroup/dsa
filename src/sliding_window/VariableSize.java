package sliding_window;

public class VariableSize {
    public int longestSubarrayOfSumK(int[] arr, int k) {
        int left = 0, right = 0;

        int sum = 0;
        int maxWindowSize = 0;
        while (right < arr.length) {
            sum += arr[right];

            if (sum < k) right++;
            if (sum == k) {
                maxWindowSize = Math.max(maxWindowSize, right - left + 1);
                right++;
            } else if (sum > k) {
                //while (sum >  k) sum -= arr[left];
                sum -= arr[left];
                left++;
                right++;
            }
        }
        return maxWindowSize;
    }

    public static void main(String[] args) {
        VariableSize solution = new VariableSize();
        int[] arr = new  int[]{4, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 5};
        System.out.println(solution.longestSubarrayOfSumK(arr, 5));
    }
}
