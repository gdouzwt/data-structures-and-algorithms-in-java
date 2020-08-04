/**
 * R-1.4 Write a short Java method, isEven, that takes an int i and returns true if and only if i is even. Your method
 * cannot user the multiplication, modulus, or division operators, however.
 */
public class IsEvenDemo {

    public static void main(String[] args) {
        System.out.println(isEven(19381));
    }

    private static boolean isEven(int n) {
        // use a loop to decrease the value of i by 2 at each iteration
        // if value of i reaches zero at any iteration before becoming negative,
        // then it is even
        // Ex: int i = 5;
        // when iteration = 1, i = 5-2 = 3 (not zero)
        // when iteration = 2, i = 3-2 = 1 (not zero)
        // when iteration = 3, i = 1-2 = -1 (negative), comes out of loop
        // Similarly, when int i = 4;
        // when iteration = 1, i = 4-2 = 2 (not zero)
        // when iteration = 2, i = 2-2 = 0 (even)
        while (n > 0) {
            n = n - 2;
            if (n == 0) {
                return true;
            }
        }
        return false;
    }
}
