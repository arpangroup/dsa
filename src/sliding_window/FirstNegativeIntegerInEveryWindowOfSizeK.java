package sliding_window;

import java.util.ArrayList;
import java.util.List;

public class FirstNegativeIntegerInEveryWindowOfSizeK {

    public List<Integer> firstNegativeInteger(int[] arr, int k) {
        int left = 0;
        int right = left;
        List<Integer> result = new ArrayList<>();


        List<Integer> negatives = new ArrayList<>();
        System.out.println("LEFT: " + left + ", RIGHT: " + right + ", NEGATIVES: " + negatives);
        while(right < arr.length){
            int windowSize = right - left + 1;
            if (arr[right] < 0 ) negatives.add(arr[right]);
            right++;

            if (windowSize == k){
                result.add(negatives.isEmpty() ? 0 : negatives.getFirst());
                left++;
                if (!negatives.isEmpty() && negatives.getFirst() != arr[left]) negatives.removeFirst();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FirstNegativeIntegerInEveryWindowOfSizeK solution = new FirstNegativeIntegerInEveryWindowOfSizeK();
        int[] arr = new int[]{12, -1, -7, 8, -16, 30, 16, 28};
        int k = 3;

        System.out.println(solution.firstNegativeInteger(arr, k)); // [-1, -1, -7, -16, -16, 0];
    }
}
