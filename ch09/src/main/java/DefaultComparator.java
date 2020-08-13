import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

    @Override
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>)a).compareTo(b);
    }
}
