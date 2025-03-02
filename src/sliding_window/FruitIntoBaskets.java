package sliding_window;

public class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        return 0;
    }

    public static void main(String[] args) {
        FruitIntoBaskets solution = new FruitIntoBaskets();
        int[][] inputs = new int[][]{
                {1,2,1},
                {0,1,2,2},
                {1,2,3,2,2}
        };
        int[] outputs = new int[]{
                3,
                3,
                4
        };

        for (int[] fruits : inputs) {
            System.out.println(solution.totalFruit(fruits));
        }

    }
}
