/**
 * Interface for key-value pair.
 *
 * @param <K> key
 * @param <V> value
 */
public interface Entry<K, V> {
    K getKey();  // returns the key stored in this entry

    V getValue();  // returns the value stored in this entry

}
