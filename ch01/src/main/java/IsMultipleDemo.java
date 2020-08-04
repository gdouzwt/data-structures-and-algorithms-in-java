import java.util.Scanner;

/**
 * R-1.3 Write a short Java method, isMultiple, that takes two long values, n and m, and returns true if and only if n
 * is a multiple of m, that is, n = mi for some integer i.
 */
public class IsMultipleDemo {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Please input two numbers for n and m: ");
        long n = stdIn.nextLong();
        long m = stdIn.nextLong();
        System.out.println(isMultiple(n, m));
    }

    private static boolean isMultiple(long n, long m) {
        if (m > n) {
            return isMultiple(m, n);
        }
        return n % m == 0;
    }
}
