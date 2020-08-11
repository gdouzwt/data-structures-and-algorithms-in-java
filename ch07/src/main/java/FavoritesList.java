import java.util.Iterator;

/**
 * Maintains a list of elements ordered according to access frequency.
 */
public class FavoritesList<E> {
    //----------------- nested Item class ------------------------
    protected static class Item<E> {
        private E value;
        private int count = 0;

        /**
         * Constructs new item with initial count of zero.
         */
        public Item(E val) {
            value = val;
        }

        public int getCount() {
            return count;
        }

        public E getValue() {
            return value;
        }

        public void increment() {
            count++;
        }
    }  // ---------------- end of nested Item class -----------------

    PositionalList<Item<E>> list = new LinkedPositionalList<>();  // list of items

    public FavoritesList() {  // constructs initially empty favorites list
    }

    // nonpublic utilities

    /**
     * Provides shorthand notation to retrieve user's element stored at Position p.
     */
    protected E value(Position<Item<E>> p) {
        return p.getElement().getValue();
    }

    /**
     * Provides shorthand notation to retrieve count of item stored at Position p.
     */
    protected int count(Position<Item<E>> p) {
        return p.getElement().getCount();
    }

    /**
     * Returns Position having element equal to e (or null if not found)
     *
     * @param e element to find
     * @return element equal to e
     */
    protected Position<Item<E>> findPosition(E e) {
        Position<Item<E>> walk = list.first();
        while (walk != null && !e.equals(value(walk)))
            walk = list.after(walk);
        return walk;
    }

    /**
     * Moves item at Position p earlier in the list based on access count.
     *
     * @param p position to be moved
     */
    protected void moveUp(Position<Item<E>> p) {
        int cnt = count(p);  //revised count of accessed item
        Position<Item<E>> walk = p;
        while (walk != list.first() && count(list.before(walk)) < cnt)
            walk = list.before(walk);  // found smaller count ahead of item
        if (walk != p)
            list.addBefore(walk, list.remove(p));  // remove/reinsert item
    }

    // public methods

    /**
     * @return the number of items in the favorites list.
     */
    public int size() {
        return list.size();
    }

    /**
     * @return true if the favorites list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Accesses element e (possibly new), increasing its access count.
     */
    public void access(E e) {
        Position<Item<E>> p = findPosition(e);  // try to locate existing element
        if (p == null)
            p = list.addLast(new Item<E>(e));  // if new, place at end
        p.getElement().increment();  // always increment count
        moveUp(p);  // consider moving forward
    }

    /**
     * Removes element equal to e from the list of favorites (if found).
     */
    public void remove(E e) {
        Position<Item<E>> p = findPosition(e);  // try to locate existing element.
        if (p != null)
            list.remove(p);
    }

    /**
     * Returns an iterable collection of the k most frequently accessed elements.
     *
     * @param k frequency
     * @return an iterable collection
     * @throws IllegalArgumentException if goes wrong.
     */
    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if (k < 0 || k > size())
            throw new IllegalArgumentException("Invalid k");
        PositionalList<E> result = new LinkedPositionalList<>();
        Iterator<Item<E>> iter = list.iterator();
        for (int j = 0; j < k; j++)
            result.addLast(iter.next().getValue());
        return result;
    }
}
