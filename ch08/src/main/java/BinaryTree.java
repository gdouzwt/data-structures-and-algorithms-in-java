/**
 * An interface for a binary tree, in which each node has at most two children.
 *
 * @param <E> element to operate on.
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * @param p position in question
     * @return the Position of p's left child (or null if no child exists).
     * @throws IllegalArgumentException if something wrong.
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    /**
     * @param p position in question
     * @return the Position of p's right child (or null if no child exists).
     * @throws IllegalArgumentException if something wrong.
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * @param p position in question
     * @return the Position of p's sibling (or null if no sibling exists).
     * @throws IllegalArgumentException if something wrong.
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
