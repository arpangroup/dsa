package sliding_window;

public class VariableSize {
    public static void variableSlidingWindow(int[] arr, int k) {
        int left =0, right = 0;
        while (right < args.length) {
            // calculation....

            if(windowSize < k) right ++;
            else if (windowSize == k) {
                // Step1: calculate ans

                // Slide the window ==> Remove Left + slide(i++; j++)
                sum = sum - arr[left];
                left ++;
                right++;
            }
        }
    }
}
