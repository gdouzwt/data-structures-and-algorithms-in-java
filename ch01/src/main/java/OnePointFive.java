/**
 * R-1.5 Write a short Java method that takes an integer n and returns the sum of all positive integers less than or
 * equal to n.
 */
public class OnePointFive {
    public static void main(String[] args) {
        System.out.println(allSum(Integer.MAX_VALUE));
        // System.out.println(sumOfPositive(Integer.MAX_VALUE));
    }

    // 用 long 这样放 int 不会溢出，用高斯算法比较好。
    private static long allSum(long n) {

        if (n <= 0) {
            return 0;
        }
        return ((1 + n) * n) / 2;
    }

    // 这个算法有问题，因为 Integer.MAX_VALUE + 1 = -2147483648 <= n 所以死循环
    private static int sumOfPositive(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }
}
