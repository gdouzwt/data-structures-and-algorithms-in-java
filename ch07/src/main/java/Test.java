import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 6, 6, 7, 8, 8, 10};
        List<Integer> integers = Arrays.asList(numbers);
        int frequency = Collections.frequency(integers, 6);
        System.out.println(Collections.max(integers));
        Collections.reverse(integers);
        System.out.println(frequency);
        System.out.println(integers);
        Collections.shuffle(integers);
        System.out.println(integers);
        Collections.sort(integers);
        System.out.println(integers);
        Integer[] number2 = integers.toArray(numbers);
        for (int a : number2) {
            System.out.print(a + " ");
        }
    }
}
