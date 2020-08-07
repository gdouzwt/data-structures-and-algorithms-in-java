import java.util.Arrays;
import java.util.Random;

/**
 * R-1.2 Suppose that we create an array A of GameEntry objects, which has an integer scores field, and we clone A and
 * store the result in an array B. If we then immediately set A[4].score equal to 500, what is the score value of the
 * GameEntry object referenced by B[4]?
 */
public class CloneDemo {
    public static void main(String[] args) {

        GameEntry[] A = new GameEntry[5];
        for (int i = 0; i < A.length; i++) {
            A[i] = new GameEntry(new Random().nextInt(500) + 1);
        }
        GameEntry[] B = A.clone();
        System.out.println("A contents below");
        printScores(A);
        System.out.println("\n++++++++++++++++++++++++++");
        System.out.println("B contents below");
        printScores(B);
        System.out.println("set A[4] to 550");
        A[4].setScore(550);
        System.out.println("print B[4]");
        System.out.println(B[4].getScore());
        System.out.println(A.equals(B)); // false
        System.out.println(Arrays.equals(A, B)); // true
        System.out.println(A[4].equals(B[4])); // true
        // 结论是 clone() 之后，数组 A 和 数组 B 是不同的对象，但是里边对应 index 存的 GameEntry 却同样的。
    }

    private static void printScores(GameEntry[] a) {
        for (GameEntry gameEntry : a) {
            System.out.print(gameEntry.getScore() + " ");
        }
        System.out.println();
    }
}

class GameEntry {
    private Integer score;

    GameEntry(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
