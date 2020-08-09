import java.util.Arrays;
import java.util.List;

public class StackUsageDemo {

    public static <E> void reverse(E[] a) {
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (E e : a) buffer.push(e);
        for (int i = 0; i < a.length; i++)
            a[i] = buffer.pop();
    }

    public static void main(String[] args) {
        String letters = "ABCDEFG";
        char[] chars = letters.toCharArray();
        Character[] characters = new Character[letters.length()];
        for (int i = 0; i < letters.length(); i++) {
            characters[i] = chars[i];
        }
        List<Character> characters1 = Arrays.asList(characters);
        System.out.println(Arrays.toString(chars));
        System.out.println("Reversing...");
        reverse(characters);
        System.out.println(characters1);

        Integer[] a = {4, 8, 15, 16, 23, 42};  // autoboxing allows this.
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
    }
}
