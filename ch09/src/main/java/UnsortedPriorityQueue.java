import java.util.Comparator;

/**
 * An implementation of a priority queue with an unsorted list.
 * @param <K>
 * @param <V>
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    /**
     * primary collection of priority queue entries.
     */
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Creates an empty priority queue based on the natual ordering of its keys.
     */
    public UnsortedPriorityQueue() {
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    private Position<Entry<K,V>> findMin() {  // only called when nonempty
        Position<Entry<K, V>> small = list.first();
        for (Position<Entry<K,V>> walk: list.positions())
            if (compare(walk.getElement(), small.getElement()) < 0)
                small = walk;  // found an even smaller key
        return small;
    }



    @Override
    public int size() {
        return list.size();
    }

    /**
     * Insets a key-value pair and returns the entry created.
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);  // auxiliary key-checking method (cloud throw exception)
        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     */
    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    /**
     * Removes and returns an entry with minimal key.
     */
    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }
}
