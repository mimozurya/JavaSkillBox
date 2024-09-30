public class num2 {
    public static void main (String[] args) {
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        System.out.println(getMinNumber(x));
    }

    public static int getMinNumber (int[][] arr) {
        int minNum = 100000;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] < minNum) {
                    minNum = arr[i][j];
                }
            }
        }
        return minNum;
    }
}
