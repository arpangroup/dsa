package recursion;

public class BinarySearch {
    public int binarySearch(int[] arr, int left, int right, int x) {
        if (left > right) return -1;
        int mid = (left + right) / 2;

        if (x == arr[mid]) return mid;
        if (x < arr[mid]) return binarySearch(arr, left, mid - 1, x); // go to left
        return binarySearch(arr, mid + 1, right, x); // go to right
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 5, 7, 8, 9, 12, 15};
        int search = 5;
        var result = new BinarySearch().binarySearch(arr, 0, arr.length-1, search);
        System.out.println("FOUND: " + result);
    }
}
