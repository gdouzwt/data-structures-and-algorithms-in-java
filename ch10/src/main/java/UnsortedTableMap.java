import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /**
     * Underlying storage for the map of entries.
     */
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /**
     * Constructs an initially empty map.
     */
    public UnsortedTableMap() {

    }

    // private utility

    /**
     * Returns the index of an entry with equal key, or -1 if none found.
     */

    private int findIndex(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++)
            if (table.get(j).getKey().equals(key))
                return j;
        return -1;  // special value denotes that key was not found
    }

    /**
     * Returns the number of entries in the map.
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
        if (j == -1) return null;
        return table.get(j).getValue();  // not found
    }

    /**
     * Associates given value with given key, replacing a previous value (if any).
     */
    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));  // add new entry
            return null;
        } else
            return table.get(j).setValue(value);  // replaced value is returned
    }


    /**
     * Removes the entry with the specified key (if any) and returns its value.
     */
    @Override
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if (j == -1) return null;
        V answer = table.get(j).getValue();  // relocate last entry to 'hole' created by removal
        table.remove(n - 1);
        return answer;
    }

    // Support for public entrySet method...
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        @Override
        public boolean hasNext() {
            return j < table.size();
        }

        @Override
        public Entry<K, V> next() {
            if (j == table.size()) throw new NoSuchElementException();
            return table.get(j++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }
}
