/**
 * Various recursion demos
 */
public class AnalyzingRecursion {


    /**
     * Returns the sum of the first n integers of the given array.
     */
    public static int linearSum(int[] data, int n) {
        if (n == 0)
            return 0;
        else
            return linearSum(data, n - 1) + data[n - 1];
    }

    /**
     * Reverses the contents of subarray data[low] through data[high] inclusive.
     */
    public static void reverseArray(int[] data, int low, int high) {
        if (low < high) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1);
        }
    }

    /**
     * Computes the value of x raised to the nth power, for nonnegative integer n.
     *
     * @param x
     * @param n
     * @return nth power of x.
     */
    public static double power(double x, int n) {
        if (n == 0)
            return 1;
        else
            return x * power(x, n - 1);
    }

    public static double powerImproved(double x, int n) {
        if (n == 0)
            return 1;
        else {
            double partial = power(x, n / 2);
            double result = partial * partial;
            if (n % 2 == 1)
                result *= x;
            return result;
        }
    }


    /**
     * Returns the sum of subarray data[low] through data[high] inclusive.  Binary Recursion
     *
     * @param data
     * @param low
     * @param high
     * @return sum
     */
    public static int binarySum(int[] data, int low, int high) {
        if (low > high)
            return 0;
        else if (low == high)
            return data[low];
        else {
            int mid = (low + high) / 2;
            return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
        }
    }

    /**
     * Recursion Run Amok
     * Returns true if there are no duplicate values from data[low] through data[high].
     */
    public static boolean unique3(int[] data, int low, int high) {
        if (low > high) return true;  // at most ont item
        else if (!unique3(data, low, high - 1)) return false;  // duplicate in first n - 1
        else if (!unique3(data, low + 1, high)) return false;  // duplicate in last n - 1
        else return (data[low] != data[high]);  // do first and last differ?
    }

    /**
     * Returns teh nth Fibonacci number (inefficiently).
     */
    public static long fibonacciBad(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacciBad(n - 2) + fibonacciBad(n - 1);
    }


    /**
     * Returns arrays containing the pair of Fibonacci numbers, F(n) and F(n - 1).
     */
    public static long[] fibonacciGood(int n) {
        if (n < 1) {
            long[] answer = {n, 0};
            return answer;
        } else {
            long[] temp = fibonacciGood(n - 1);  // returns { Fn-1, Fn-2}
            long[] answer = {temp[0] + temp[1], temp[0]};  // we want {Fn, Fn-1}
            return answer;
        }
    }

    /**
     * Don't call this (infinite) version.
     */
    public static int fibonacci(int n) {
        return fibonacci(n);
    }


    /**
     * Returns true if the target value is found in the data array.
     */
    public static boolean binarySearchIterative(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (target == data[mid])  // found a match
                return true;
            else if (target < data[mid])
                high = mid - 1;  // only consider values right of mid
            else
                low = mid + 1;  // only consider values right of mid
        }
        return false;  // loop ended without success.
    }
}
