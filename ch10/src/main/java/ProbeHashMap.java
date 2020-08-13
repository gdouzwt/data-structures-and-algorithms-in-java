import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;  // a fixed array of entries (all initially null)
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);  // sentinel

    public ProbeHashMap() {
        super();
    }

    public ProbeHashMap(int cap) {
        super(cap);
    }

    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity]; // safe cast
    }

    /**
     * Returns true if location is either empty or the "default" sentinel.
     */
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    /**
     * Returns index with key k, or -(a+1) such that k could be added at index a.
     */
    private int findSlot(int h, K k) {
        int avail = -1;  // no slot available (thus far)
        int j = h;  // index while scanning table
        do {
            if (isAvailable(j)) {  // may be either empty or defunct
                if (avail == -1) avail = j;  // this is the first available slot!
                if (table[j] == null) break;  // if empty, search fails immediately
            } else if (table[j].getKey().equals(k))
                return j;
            j = (j + 1) % capacity;  // keep looking (cyclically)
        } while (j != h);  // stop if we return to the start
        return -(avail + 1);  // search has failed
    }

    /**
     * Returns value associated with key k in bucket with hash value h, or else null.
     */
    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;
        return table[j].getValue();  // no match found
    }

    /**
     * Associates key k with value v in bucket with hash value h; returns old value.
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0)
            return table[j].setValue(v);  // this key has an existing entry
        table[-(j + 1)] = new MapEntry<>(k, v);  // convert to proper index
        n++;
        return null;
    }

    /**
     * Removes entry having key from bucket with has value h (if any).
     */
    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;  // nothing to remove
        V answer = table[j].getValue();
        table[j] = DEFUNCT;  // mark this slot as deactivated
        n--;
        return answer;
    }


    /**
     * Returns an iterable collection of all key-value entries of the map.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (!isAvailable(h)) buffer.add(table[h]);
        }
        return buffer;
    }
}
