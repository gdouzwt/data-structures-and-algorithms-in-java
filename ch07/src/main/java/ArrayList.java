import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>, Iterable<E> {

    //instant variables
    public static final int CAPACITY = 16;  // default array capacity
    private E[] data;  // generic array used for storage
    private int size = 0;  // current number of elements

    public ArrayList() {  // constructs list with default capacity
        this(CAPACITY);
    }

    public ArrayList(int capacity) {  // constructs list with given capacity
        data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }


    // public methods

    /**
     * @return the number of elements in the array list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the array list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param i index
     *          Returns (but does not remove) the element at index i.
     * @throws IndexOutOfBoundsException exception
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }


    /**
     * Replaces the element at index i with e, and returns the replaced element.
     *
     * @param i index
     * @param e the new element
     * @return the replaced element
     * @throws IndexOutOfBoundsException exception
     */
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /**
     * Inserts element e to be at index i, shifting all subsequent elements later.
     *
     * @param i index
     * @param e the element to be added
     * @throws IndexOutOfBoundsException exception
     */
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length)  // not enough capacity
            throw new IllegalStateException("Array is full");
        for (int k = size - 1; k >= i; k--)  // start by shifting rightmost
            data[k + 1] = data[k];
        data[i] = e; // ready to place the new element
        size++;
    }

    /**
     * Removes/returns the element at index i, shifting subsequent elements earlier
     *
     * @param i index
     * @return the removed element.
     * @throws IndexOutOfBoundsException exception
     */
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++)  // shift element to fill hole
            data[k] = data[k + 1];
        data[size - 1] = null;  // help garbage collection
        size--;
        return temp;
    }


    // utility method

    /**
     * Checks whether the given index is in the range[0, n-1].
     *
     * @param i index
     * @param n number of the elements
     * @throws IndexOutOfBoundsException exception
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    //------------------- nested ArrayIterator class ----------------------------

    /**
     * A (nonstatic) inner class. Note well that each instance contains and implicit reference to the containing list,
     * allowing it to access the list's members.
     */
    private class ArrayIterator implements Iterator<E> {

        private int j = 0;  // index of the next element to report
        private boolean removable = false;  // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         * @return true if there are further objects, false otherwise
         */
        @Override
        public boolean hasNext() {
            return j < size; // size is field of outer instance
        }

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there no further elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true; // this element can subsequently be removed
            return data[j++];  // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        @Override
        public void remove() throws IllegalStateException{
            if (!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(j - 1);  // that was the last one returned
            j--; // next element has shifted one cell to the left
            removable = false;  // do not allow remove again util next is called
        }
    }  //-------------------------- end of nested ArrayIterator class ---------------------


    /**
     *
     * @return an iterator of the elements stored in the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();  // create a new instance of the inner class
    }
}
