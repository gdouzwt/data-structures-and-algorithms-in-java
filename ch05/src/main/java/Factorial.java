import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        System.out.println("输入要求阶乘的数 n :");
        Scanner stdIn = new Scanner(System.in);
        System.out.println(factorial(stdIn.nextInt()));
    }

    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0)
            throw new IllegalArgumentException();  // argument must be nonnegative
        else if (n == 0)
            return 1;  // base case
        else
            return n * factorial(n - 1);  // recursive case
    }
}
