import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMultimap<K, V> {
    Map<K, List<V>> map = new HashMap<>();  // the primary map
    int total = 0;  // total number of entries in the multimap

    /**
     * Constructs an empty multimap.
     */
    public HashMultimap() {
    }

    /**
     * Returns the total number of entries in the multimap
     */
    public int size() {
        return total;
    }

    /**
     * Returns whether the multimap is empty.
     */
    public boolean isEmpty() {
        return (total == 0);
    }

    /**
     * Returns a (possibly empty) iteration of all values associated with the key.
     */
    Iterable<V> get(K key) {
        List<V> secondary = map.get(key);
        if (secondary != null)
            return secondary;
        return new ArrayList<>();  // return an empty list of values
    }
}
