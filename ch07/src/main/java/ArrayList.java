public class ArrayList<E> implements List<E> {

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
}
