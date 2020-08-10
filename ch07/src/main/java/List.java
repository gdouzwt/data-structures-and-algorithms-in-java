/**
 * A simplified version of the java.util.List interface.
 * @param <E> 元素
 */
public interface List<E> {

    /**
     *
     * @return the number of
     */
    int size();

    /**
     * Returns whether the list is empty.
     */
    boolean isEmpty();

    /**
     *
     * @param i index
     * @return Returns (but does not remove) the element at index i.
     * @throws IndexOutOfBoundsException exception
     */
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at index i with e, and returns the replaced element.
     * @param i index
     * @param e the new element
     * @return the replaced element.
     * @throws IndexOutOfBoundsException exception
     */
    E set(int i, E e) throws IndexOutOfBoundsException;

    /**
     * Inserts element e to be at index i, shifting all subsequent element later.
     * @param i index
     * @param e the element to be added
     * @throws IndexOutOfBoundsException exception
     */
    void add(int i, E e) throws IndexOutOfBoundsException;


    /**
     *  Removes/returns the element at index i, shifting subsequent elements earlier.
     * @param i index
     * @return the removed element
     * @throws IndexOutOfBoundsException exception
     */
    E remove(int i) throws IndexOutOfBoundsException;
}
