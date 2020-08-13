import java.util.ArrayList;
import java.util.Comparator;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public SortedTableMap() {
        super();
    }

    public SortedTableMap(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the smallest index for range table[low..high] inclusive storing an entry
     * with a key greater than or equal to k (or else index high+1, by convention).
     */
    private int findIndex(K key, int low, int high) {
        if (high < low) return high + 1;  // no entry qualifies
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));
        if (comp == 0)
            return mid;  // found exact match
        else if (comp < 0)
            return findIndex(key, low, mid - 1);  // answer is left of mid (or possibly mid)
        else
            return findIndex(key, mid + 1, high);  // answer is right of mid
    }

    /**
     * Version of findIndex that searches the entire table.
     */
    private int findIndex(K key) {
        return findIndex(key, 0, table.size() - 1);
    }

    /**
     * Returns the  number of entries in the map
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * Returns the value associated with the specified key (or else null).
     */
    @Override
    public V get(K key) {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0) return null;  // no match
        return table.get(j).getValue();
    }

    /**
     * Associates the given value with the given key, returning any overridden value.
     */
    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j < size() && compare(key, table.get(j)) == 0)  // match exists
            return table.get(j).setValue(value);
        table.add(j, new MapEntry<K, V>(key, value));  // otherwise new
        return null;
    }

    /**
     * Removes the entry having key k (if any) and returns its associated value.
     */
    @Override
    public V remove(K key) {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0) return null;  // no match
        return table.remove(j).getValue();
    }

    /**
     * Utility returns the entry at index j, or else
     */
    private Entry<K, V> safeEntry(int j) {
        if (j < 0 || j >= table.size()) return null;
        return table.get(j);
    }

    /**
     * Returns the entry having the least key (or null if map is empty).
     */
    @Override
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     */
    @Override
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size() - 1);
    }

    /**
     * Returns the entry with least key greater than or equal to given key (if any).
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key));
    }

    /**
     * Returns the entry with greatest key less than or equal to given key (if any).
     */
    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j == size() || !key.equals(table.get(j).getKey()))
            j--;  // look one earlier (unless we had found a perfect match)
        return safeEntry(j);
    }

    /**
     * Returns the entry with greatest key strictly less than given key (if any).
     */
    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key) - 1);  // go strictly before the ceiling entry
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        /*
         * Returns the entry with least key strictly greater than given key (if any).
         */
        int j = findIndex(key);
        if (j < size() && key.equals(table.get(j).getKey()))
            j++;  // go past exact match
        return safeEntry(j);
    }

    // support for snapshot iterators for entrySet() and subMap() follow
    private Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int j = startIndex;
        while (j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
            buffer.add(table.get(j++));
        return buffer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return snapshot(0, null);
    }


    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        return snapshot(findIndex(fromKey), toKey);
    }
}
