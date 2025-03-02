package sliding_window;

// 2^n ==> 1<<n
public class Subsets {
    public void subsets(int[] arr) {
        int n = arr.length;
        for (int i=0; i< Math.pow(2, n); i++) {
            for (int j = i; j< arr.length; j++) {
                 System.out.println("{" + arr[j] + "");
            }
        }
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        solution.subsets(new int[]{1, 2, 3});
    }
}
