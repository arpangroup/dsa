package sliding_window;

import base.MutableInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarraySumEqualsK {

    // Approach1: Brute Force Approach: O(N^2)
    public int subarraySumV1(int[] nums, int k) {
        int count = 0;

        for(int i=0; i< nums.length; i++) {
            int currSum = 0;
            for (int j=i; j< nums.length; j++) {
                currSum += nums[j];
                if (currSum == k) count ++;
            }
        }

        return count;
    }

    // Approach2: Using Backtracking to generate all possible subset
    // It will not work because this approach doesn't follow contiguous property
    public int subarraySumV2(int[] nums, int k) {
        MutableInteger count = new MutableInteger(0);
        backtrack(nums, 0, k, count, new ArrayList<>());
        return count.value;
    }
    private void backtrack(int[] arr, int index, int target, MutableInteger count, List<Integer> current) {
        if(target == 0) {
            count.value = count.value + 1;
            System.out.println(current);
            return;
        }
        if(target < 0 || index >= arr.length) return;

        // take the current element
        current.add(arr[index]);
        backtrack(arr, index + 1, target - arr[index], count, current);

        // not take the current element
        current.removeLast();
        backtrack(arr, index + 1, target, count, current);

    }
    // Fix: Approach2: Backtracking with Sliding Window
    private int subarraySumV2(int[] nums, int idx, int target) {
        if (idx >= nums.length) return 0;

        int sum = 0, count = 0;
        for (int end = idx; end < nums.length; end++) {
            sum += nums[end];
            if (sum == target) count++;
        }
        return count + subarraySumV2(nums, idx + 1, target);
    }




    // Approach3: Using 2-pointer - Variable Size Sliding Window:
    // Below slidingWindow approach will not work
    public int subarraySumV3_incorrect(int[] nums, int k) {
        int left = 0;
        int right = 0;

        int count = 0;
        int sum = 0;
        while(right < nums.length) {
            sum += nums[right];

            if(sum < k) right++;
            else if(sum == k) {
                count ++;
                right++;
            } else if(sum > k) {
                 sum -= nums[left];
                 left++;
                 right++;
            }
        }
        return count;
    }

    // Fix: Approach2: Works only for positive numbers
    // Sliding Window approach will fail when array contains negative number
    public int subarraySumV3(int[] nums, int k) {
        int left = 0;
        int right = 0;

        int count = 0;
        int sum = 0;
        while(right < nums.length) {
            sum += nums[right];

            if(sum < k) right++;
            else if(sum == k) {
                count ++;
                right++;
            } else if(sum > k) {

                // Shrink the window while sum is greater than k
                while (sum > k && left <= right) {
                    sum -= nums[left];
                    left++;
                }

                //After shrinking, check again if sum == k
                if (sum == k) {
                    count++;
                }
                right++;
            }
        }
        return count;
    }

    // Approach4: (O(n) Prefix Sum + HashMap)
    /*public int subarraySumV4(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);

        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            if (prefixSumMap.containsKey(sum - k)) {
                count += prefixSumMap.get(sum - k);
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }*/

    public int subarraySumV4(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = calculatePrefSum(nums);
        System.out.println("PREF_SUM: " + prefixSumMap);

        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            if (prefixSumMap.containsKey(sum-k)){
                count += prefixSumMap.get(sum-k);
            }
        }
        return count;
    }

    private Map<Integer, Integer> calculatePrefSum(int[] arr) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Base case to handle sum == k

        int sum = 0;
        for (int num : arr) {
            sum += num;
            //prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
            if (prefixSumMap.containsKey(sum)) {
                int prevVal = prefixSumMap.get(sum);
                prefixSumMap.put(sum, prevVal + 1);
            } else {
                prefixSumMap.put(sum, 1);
            }
        }
        return prefixSumMap;
    }


    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        int[][] input = new int[][]{
                {1,1,1},
                {1,2,3},
                {1,4,1,2,6},
                {1}
        };
        int[] k = new int[]{
                2,
                3,
                3,
                0
        };
        int[] output = new int[]{
                2,
                2,
                1,
                0
        };

        for (int i=0; i< input.length; i++) {
            System.out.println(solution.subarraySumV4(input[i], k[i]));
        }
    }
}
