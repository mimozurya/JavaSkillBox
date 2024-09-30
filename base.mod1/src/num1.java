public class num1 {
    public static void main (String[] args) {
        int num = 1000;
        System.out.println(getSum(num));
    }
    public static int getSum (int num) {
        int sum = 0;
        for (int i = 0; i <= num; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum+=i;
            }
        }
        return sum;
    }
}
