/**
 * 双端队列 ADT
 *
 * @param <E> 元素
 *            Interface for a double-ended queue. a collection of elements that can be inserted and removed at both ends;
 *            this interface is a simplified version of java.util.Deque.
 */
public interface Deque<E> {
    /**
     * @return the number of elements in the deque.
     */
    int size();

    /**
     * @return Tests whether the deque is empty.
     */
    boolean isEmpty();

    /**
     * @return Returns, but does not remove, the first element of the deque (null if empty).
     */
    E first();

    /**
     * Returns, but does not remove, the last element of the deque (null if empty).
     *
     * @return last element of the deque.
     */
    E last();

    /**
     * Inserts an element at the front of the deque.
     *
     * @param e the element to be inserted at the front.
     */
    void addFirst(E e);

    /**
     * Inserts an element at the back of the deque.
     *
     * @param e the element to be added.
     */
    void addLast(E e);

    /**
     * Removes and returns the first element in the deque (null if empty).
     *
     * @return the first element.
     */
    E removeFirst();

    /**
     * Removes and returns the last element of the deque (null if empty).
     *
     * @return the last element.
     */
    E removeLast();
}
