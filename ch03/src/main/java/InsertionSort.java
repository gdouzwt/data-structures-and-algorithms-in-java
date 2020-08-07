import java.util.Arrays;

/**
 * Insertion-sort of an array of characters into nondecreasing order
 */
public class InsertionSort {

    public static void main(String[] args) {
        char[] letters = {'B', 'C', 'D', 'A', 'E', 'H', 'G', 'F'};
        char[] others = new char[5];
        Arrays.fill(others, 'a');
        System.out.println(others);
        System.out.println(letters);
//        insertionSort(letters);
        Arrays.sort(letters);
        System.out.println(letters);
    }

    public static void insertionSort(char[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {  // begin with second character
            char cur = data[k];  // time to insert cur = data[k]
            int j = k;  // find correct index j for cur
            while (j > 0 && data[j - 1] > cur) {  // thus, data[j-1] must go after cur
                data[j] = data[j - 1];  // slide data[j-1] rightward
                j--;  // and consider previous j for cur
            }
            data[j] = cur;  // this is the proper place for cur
        }
    }
}
