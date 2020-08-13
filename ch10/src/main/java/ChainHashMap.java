import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {

    // a fixed capacity array of UnsortedTableMap that serve as buckets

    private UnsortedTableMap<K, V>[] table;  // initialized within createTable

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }


    /**
     * Returns value associated with key k in bucket with has value h, or else null.
     *
     * @param h hash value
     * @param k key
     * @return value associated with key k
     */
    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h; returns old value.
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            bucket = table[h] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize);  // size may have increased
        return answer;
    }

    /**
     * Removes entry having key k from bucket with hash value h (if any).
     */
    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size());  // size may have decreased
        return answer;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterable<K> keySet() {
        return null;
    }

    @Override
    public Iterable<V> values() {
        return null;
    }

    /**
     *
     * @return an iterable collection of all key-value entries of the map.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (table[h] != null)
                for (Entry<K,V> entry : table[h].entrySet())
                    buffer.add(entry);
        return buffer;
    }
}
