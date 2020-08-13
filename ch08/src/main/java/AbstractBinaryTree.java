/**
 * An abstract base class providing some functionality of the BinaryTree interface.
 *
 * @param <E>
 */

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    /**
     * @param p position in question
     * @return the Position of p's sibling (or null if no sibling exists).
     */
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null;  // p must be the root
        if (p == left(parent))  // p is a left child
            return right(parent); // right child might be null
        else                      // p is a right child
            return left(parent);  // (left child might be null)
    }

    /**
     * @param p the position which the subtree rooted.
     * @return the number of children of Position p.
     */
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        // 这里用的是 java.util.List 和 java.util.ArrayList 而不是 ch07 自定义的 List 和 ArrayList
        List<Position<E>> snapshot = new ArrayList<>(2);  // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in inorder.
     */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot);  // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Overrides positions to make inorder the default order for binary trees.
     */
    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }

}
