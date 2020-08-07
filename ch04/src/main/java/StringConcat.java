import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringConcat {
    /**
     * Uses repeated concatenation to compose a String with n copies of character c.
     */
    public static String repeat1(char c, int n) {
        String answer = "";
        for (int j = 0; j < n; j++)
            answer += c;
        return answer;
    }

    /**
     * Uses StringBuilder to compose a String with n copies of character c.
     */
    public static String repeat2(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Runnable task1 = () -> {
            System.out.println(repeat1('*', 100));
        };

        Runnable task2 = () -> {
            System.out.println(repeat2('*', 100));
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(task1);
        service.execute(task2);

        service.shutdown();
    }

}
