import java.util.ArrayList;
public class TraversalExamples {

  /** Returns a string containing n spaces. */
  public static String spaces(int n) {
    return StringExperiment.repeat2(' ', n);
  }

  public static <E> void printPreorder(AbstractTree<E> T) {
    for (Position<E> p : T.preorder())
      System.out.println(p.getElement());
  }

  public static <E> void printPreorderIndentSlow(AbstractTree<E> T) {
    for (Position<E> p : T.preorder())
      System.out.println(spaces(2*T.depth(p)) + p.getElement());  // beware of inefficiency
  }

  /** Prints preorder representation of subtree of T rooted at p having depth d. */
  public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d) {
    System.out.println(spaces(2*d) + p.getElement());   // indent based on d
    for (Position<E> c : T.children(p))
      printPreorderIndent(T, c, d+1);                   // child depth is d+1
  }

  /** Prints labeled representation of subtree of T rooted at p having depth d. */
  public static <E>
  void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path) {
    int d = path.size();                       // depth equals the length of the path
    System.out.print(spaces(2*d));             // print indentation, then label
    for (int j=0; j < d; j++) System.out.print(path.get(j) + (j == d-1 ? " " : "."));
    System.out.println(p.getElement());
    path.add(1);                               // add path entry for first child
    for (Position<E> c : T.children(p)) {
      printPreorderLabeled(T, c, path);
      path.set(d, 1 + path.get(d));            // increment last entry of path
    }
    path.remove(d);                            // restore path to its incoming state
  }

  /** Prints parenthesized representation of subtree of T rooted at p. */
  public static <E> void parenthesize(Tree<E> T, Position<E> p) {
    System.out.print(p.getElement());
    if (T.isInternal(p)) {
      boolean firstTime = true;
      for (Position<E> c : T.children(p)) {
        System.out.print( (firstTime ? " (" : ", ") ); // determine proper punctuation
        firstTime = false;                             // any future passes will get comma
        parenthesize(T, c);                            // recur on child
      }
      System.out.print(")");
    }
  }

  /** Returns total disk space for subtree of T rooted at p. */
  public static int diskSpace(Tree<Integer> T, Position<Integer> p) {
    int subtotal = p.getElement();    // we assume element represents space usage
    for (Position<Integer> c : T.children(p))
      subtotal += diskSpace(T, c);
    return subtotal;
  }


  // fake interface for geometric layout problem
  public interface Geometric {
    public void setX(int x);
    public void setY(int y);
  }

  /** Defines geometry for an inorder layout of subtree of a binary tree. */
  public static <E extends Geometric> int layout(BinaryTree<E> T, Position<E> p, int d, int x) {
    if (T.left(p) != null)
      x = layout(T, T.left(p), d+1, x);     // resulting x will be increased
    p.getElement().setX(x++);                      // post-increment x
    p.getElement().setY(d);
    if (T.right(p) != null)
      x = layout(T, T.right(p), d+1, x);    // resulting x will be increased
    return x;
  }

    public static void main(String[] args) {
//        Tree<String> company = new Lin
    }

}
