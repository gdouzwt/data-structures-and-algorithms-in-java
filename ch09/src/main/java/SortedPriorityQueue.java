import java.util.Comparator;

/**
 * An implementation of a priority queue with a sorted list.
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    /**
     * primary collection of priority queue entries.
     */
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public SortedPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     */
    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the number of items in the priority queue.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        return null;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     */
    @Override
    public Entry<K, V> min() {
        return null;
    }

    /**
     * Removes and returns an entry with minimal key.
     */
    @Override
    public Entry<K, V> removeMin() {
        return null;
    }
}
