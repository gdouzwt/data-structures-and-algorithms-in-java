/**
 * R-1.6 Write a short Java method that takes an integer n and returns the sum of all the odd positive integers less
 * than or equal to n.
 * 其实就是等差数列求前 n 项和的问题。
 */
public class OnePointSix {

    public static void main(String[] args) throws Exception {
        System.out.println(sumOfOddPositive(8L));
        System.out.println(sumOfOddPositive(8));
    }

    private static long sumOfOddPositive(long n) throws IllegalArgumentException {
        // 先判断奇偶，偶数则 / 2 得到项数， 奇数则 - 1 再 / 2 得到项数
        // 之后就套用等差数列前 n 项和公式即可。
        if (n < 0) {
            throw new IllegalArgumentException("不能小于零");
       }
        long terms = n % 2 == 0 ? n / 2 : (n - 1) / 2;
        return terms * (2 + (terms - 1) * 2) / 2;
    }

    // method to add all odd positive integers till n
    // 这个傻狗算法，竟然出现在答案网站，看起来那个网站的答案不是书本官方的配套，是网页贡献的。
    // 这个也是会因为溢出而死循环，比较傻的是不用等差数列。
    private static int sumOfOddPositive(int n){
        // Variable to store the sum of odd positive integers (initialized with 0)
        int sum = 0;
        // use a loop to add all the odd positive integers till n, iteratively
        // in the variable sum
        // iterate using steps of 2 to take only odd elements
        // Ex: int n = 8;
        // when i = 1, sum = 0+1 = 1
        // when i = 3, sum = 1+3 = 4
        // when i = 5, sum = 4+5 = 9
        // when i = 7, sum = 9+7 = 16
        for(int i = 1; i <= n; i=i+2){
            sum = sum+i;
        }
        // return the sum of all odd positive integers till n
        // ex: when n = 8, return 15
        return sum;
    }
}
