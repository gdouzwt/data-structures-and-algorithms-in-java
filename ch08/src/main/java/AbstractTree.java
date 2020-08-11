/**
 * An abstract base class providing some functionality of the Tree interface.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param p the position
     * @return the number of levels separating Position p from the root.
     */
    public int depth(Position<E> p) {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    /**
     * @return Returns the height of the tree.
     */
    private int heightBad() {  // works, but quadratic worst-case time.  可以用，但是最坏情况是平方阶
        int h = 0;
        for (Position<E> p : positions())
            if (isExternal(p))  // only consider leaf positions
                h = Math.max(h, depth(p));
        return h;
    }

    /**
     * @param p position where the subtree rooted
     * @return the height of the subtree rooted at Position p.
     */
    public int height(Position<E> p) {
        int h = 0;  // base case if p is external
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }
}
