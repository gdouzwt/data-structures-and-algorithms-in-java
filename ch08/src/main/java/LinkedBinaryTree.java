import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    //------------------- nested Node class -----------------------
    protected static class Node<E> implements Position<E> {

        private E element;  // an element stored at this node
        private Node<E> parent;  // a reference to the parent node (if any)
        private Node<E> left;  // a reference to the left child (if any)
        private Node<E> right;  // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          element
         * @param above      element above
         * @param leftChild  left child
         * @param rightChild right child
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    } // --------------- end of nested Node class -----------------


    // ----------------- nested ElementIterator class -------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();  // return element!
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }


    /**
     * Factory function to create a new node storing element e.
     *
     * @param e      the element to be stored
     * @param parent parent node
     * @param left   lift child
     * @param right  right child
     * @return the newly created node.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }


    // LinkedBinaryTree instance variables
    protected Node<E> root = null;  // root of the tree
    private int size = 0;  // number of nodes in the tree

    // constructor
    public LinkedBinaryTree() {
    }  // constructs an empty binary tree

    // nonpublic utility

    /**
     * Validates the position and returns it as node.
     *
     * @param p position
     * @return as node
     * @throws IllegalArgumentException something wrong.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;  // safe cast
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * @param p position in question
     * @return the Position of p's left left child (or null if no child exists).
     * @throws IllegalArgumentException wrong node.
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * @param p position in question
     * @return the Position of p's left right child (or null if no child exists).
     * @throws IllegalArgumentException wrong node.
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * @return the root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * @param p given position
     * @return the Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException wrong node.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * @return the number of nodes in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    // 这两个方法，暂时用不到，
    // 后面讲到遍历的顺序的时候才实现，
    // 因为树的遍历不同
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();  // 现在可以了
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null; // todo
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     */
    public Position<E> addRoot(Position<E> p, E e) throws IllegalArgumentException {
        if (!isEmpty()) throw new IllegalArgumentException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Creates a new left child of Position p storing element e; return its Position.
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e; return its Position.
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2 as left and right subtree of external p.
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {          // attach t1 as left subtree of node
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);  // attach t2 as right subtree of node
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child. if nay.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent());
        if (node == root)
            root = child;
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);  // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);  // our convention for defunct node
        return temp;
    }

}
