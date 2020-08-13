import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    public boolean isEmpty() {
        return size() == 0;
    }

    // --------------- nested MapEntry class ------------------
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k;  // key
        private V v;  // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        // public methods of the Entry interface
        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }  // ----------- end of nested MapEntry class --------------

    // Support for public keySet method...
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();  // reuse entrySet


        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();  // return key!
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    // Support for public values method...
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();  // reuse entrySet

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();  // return value!
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public Iterable<V> values() {
        return new ValueIterable();
    }


}
