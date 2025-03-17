package heap;

import java.util.*;

/*
    Company Tags                :
    LeetCode                    : 378
    Leetcode Link               : https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
*/
public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        //return kthSmallestV1__SortFlattenTo1DArray(matrix, k); // TC: O(n^2 log n^2);  SC: O(n^2)
        //return kthSmallestV2__maxHeap(matrix, k);              // TC: O(N^2 * log(K)); SC: O(K)
        return kthSmallestV3__binarySearch(matrix, k);           // TC: O(2*N* log(MAX – MIN)) ; SC: O(1)
    }

    /* Approach1: Flatten the 2D array to 1D array and sort
        TC: O(n^2) + O(n^2 log n^2)
          ==> O(n^2 log n^2)
        SC: O(n^2) to store the flatten elements
    */
    private int kthSmallestV1__SortFlattenTo1DArray(int[][] matrix, int k) {
        List<Integer> flattenList = new ArrayList<>();

        for(int i= 0; i< matrix.length; i++) { // O(n^2)
            for(int j=0; j< matrix[i].length; j++) {
                flattenList.add(matrix[i][j]);
            }
        }

        Collections.sort(flattenList); // O(n^2 log n^2)

        return flattenList.get(k-1);
    }

    /* Approach2: MaxHeap of size K
        TC: O(N^2 * log(K))
        SC: O(k)
    */
    private int kthSmallestV2__maxHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);

        for(int i= 0; i< matrix.length; i++) { // O(n^2)
            for(int j=0; j< matrix[i].length; j++) {
                pq.offer(matrix[i][j]); // logK

                if(pq.size() > k) {
                    pq.poll();
                }
            }
        }

        return pq.peek();
    }


    /* Approach2: BinarySearch on answer
       As the array is row-wise & column-wise sorted we can apply Binary Search on Answer
        TC: O(2*N* log(MAX – MIN))
        SC: O(1)
    */
    private int kthSmallestV3__binarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];

        int ans = 0;;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            // Count elements less than or equal to mid
            int count = countSmallerEqual(matrix, mid);

            if (count < k) {
                // If there are less than k elements <= mid, the kth smallest is larger
                low = mid + 1;
            } else {
                // Otherwise, mid might be the answer, but we need to check for smaller values
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public int countSmallerEqual(int[][] matrix, int x) {
        int n = matrix.length;
        int row = 0;
        int col = n-1;
        int count = 0;

        // Traverse from the top-right corner
        while (row < n && col >=0){
            if (matrix[row][col] <= x) {
                // If current element is less than or equal to x,
                // all elements in this row up to the current column are <= x
                count += (col + 1);
                row++;
            } else {
                // Move left in the matrix
                col--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        KthSmallestElementInSortedMatrix solution = new KthSmallestElementInSortedMatrix();
        int[][] matrix = new int[][]{
                {1,5,9},
                {10,11,13},
                {12,13,15}
        };
        int k = 8;
        System.out.println(solution.kthSmallest(matrix, k)); // 13

        matrix = new int[][]{
                {-5}
        };
        k = 1;
        System.out.println(solution.kthSmallest(matrix, k)); // -5
    }
}
