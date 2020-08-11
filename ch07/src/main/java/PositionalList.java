/**
 * An interface for positional lists.
 *
 * @param <E> element
 */
public interface PositionalList<E> extends Iterable<E> {

    /**
     * Returns the number of elements in the list.
     */
    int size();

    /**
     * Tests whether the list is empty.
     */
    boolean isEmpty();

    /**
     * Returns the first Position in the list (or null, if empty).
     */
    Position<E> first();

    /**
     * Returns the last Position in the list (or null, if empty).
     */
    Position<E> last();

    /**
     * @param p position
     * @return the Position immediately before Position p (or null, if p is first).
     * @throws IllegalArgumentException if Position is not valid.
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * @param p position
     * @return the Position immediately after Position p (or null, if p is last).
     * @throws IllegalArgumentException if Position is not valid.
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Inserts element e at the front of the list and returns its new Position.
     *
     * @param e the element to be added.
     * @return its new Position
     */
    Position<E> addFirst(E e);

    /**
     * Inserts element e at the back of the list and returns its new Position.
     *
     * @param e the element to be added.
     * @return its new Position
     */
    Position<E> addLast(E e);

    /**
     * @param p the Position
     * @param e the element to be added
     * @return its new Position
     * @throws IllegalArgumentException if the Position invalid.
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * @param p the Position
     * @param e the element to be added
     * @return its new Position
     * @throws IllegalArgumentException if the Position invalid.
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at Position p and returns the replaced element.
     *
     * @param p the position
     * @param e the new element at Position p
     * @return the replaced element
     * @throws IllegalArgumentException if the position invalid
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element stored at Position p and returns it (invalidating p).
     *
     * @param p the position in which the element to be removed
     * @return the removed element
     * @throws IllegalArgumentException
     */
    E remove(Position<E> p) throws IllegalArgumentException;

}
