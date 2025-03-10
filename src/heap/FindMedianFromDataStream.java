package heap;

import java.util.*;

/*
    Company Tags                : Apple, AMAZON, VMWARE, GOOGLE, MICROSOFT, GOLDMAN SACHS, Adobe, Belzabar, SAP Labs, Yahoo,
                                  D-E-Shaw, Facebook, Flipkart, Google, Intuit, Microsoft, Morgan Stanley, Ola Cabs, Oracle, Samsung
    Leetcode Link               : https://leetcode.com/problems/find-median-from-data-stream/
    GfG Link                    : https://practice.geeksforgeeks.org/problems/find-median-in-a-stream-1587115620/1
*/
public class FindMedianFromDataStream {
    //Approach-1 (O(n^2) - TLE
    //T.C : O(n^2)
    //S.C : O(50001) ~= O(1)
    static class MedianFinderV1 {
        int[] arr;
        int size = 0;

        public MedianFinderV1() {
            arr = new int[5*10000+1];
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        public void addNum(int num) { // for n numbers n * nlogn
            arr[++size] = num;
            Arrays.sort(arr); // O(NlogN)
        }

        public double findMedian() { // O(1)
            System.out.println("ARR: " + Arrays.toString(Arrays.copyOfRange(arr, 0, size)));;
            int middle = size / 2;
            if (size % 2 == 0) { // even
                return (arr[middle] + arr[middle - 1])/2.0;
            } else {
                return arr[middle];
            }
        }
    }



    //Approach-2 (O(logn) insertion using priority_queue) - Accepeted
    //T.C : The overall time complexity is O(log N) for the addNum method and O(1) for the findMedian method.
    //S.C : O(N).
    static class MedianFinderV2 {
        private final PriorityQueue<Integer> maxHeap;
        private final PriorityQueue<Integer> minHeap;

        public MedianFinderV2() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap
            minHeap = new PriorityQueue<>((a, b) -> a - b); // Min heap
        }

        public void addNum(int num) {
            maxHeap.offer(num); // Add to maxHeap first
            minHeap.offer(maxHeap.poll()); // Move the largest from maxHeap to minHeap

            // Balance heaps: maxHeap should have equal or 1 more element than minHeap
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if(maxHeap.size() > minHeap.size()) { // Odd count, maxHeap has the median
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        MedianFinderV1 obj = new MedianFinderV1();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian()); // 1.5
        obj.addNum(3);
        System.out.println(obj.findMedian()); // 2.0
    }
}
