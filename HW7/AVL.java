import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Tanush Chopra
 * @version 1.0
 * @userid tchopra32
 * @GTID 903785867
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) { throw new IllegalArgumentException(); }
        for (T d: data) {
            if (d == null) { throw new IllegalArgumentException(); }
            this.add(d);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) { throw new IllegalArgumentException(); }
        this.root = this.add(this.root, data);
    }

    private AVLNode<T> add(AVLNode<T> n, T d) {
        if (n == null) {
            ++this.size;
            return new AVLNode<T>(d);
        }
        if (d.compareTo(n.getData()) < 0) {
            n.setLeft(this.add(n.getLeft(), d));
        } else if (d.compareTo(n.getData()) > 0) {
            n.setRight(this.add(n.getRight(), d));
        } else {
            return n;
        }
        this.calc(n);
        return this.balance(n);
    }

    private void calc(AVLNode<T> n) {
        int l = n.getLeft().getHeight();
        int r = n.getRight().getHeight();
        n.setHeight(1 + Math.max(l, r));
        n.setBalanceFactor(l - r);
    }

    private AVLNode<T> balance(AVLNode<T> n) {
        if (n.getBalanceFactor() >= 2) {
            if (n.getLeft().getBalanceFactor() <= -1) {
                n.setLeft(this.rotateLeft(n.getLeft()));
            }
            n = this.rotateRight(n)
        } else if (n.getBalanceFactor() <= -2) {
            if (n.getRight().getBalanceFactor() >= 1) {
                n.setRight(this.rotateRight(n.getRight()));
            }
            n = this.rotateLeft(n);
        }
        return n;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> n) {
        AVLNode<T> c = n.getRight();
        n.setRight(c.getLeft());
        c.setLeft(n);
        this.calc(n);
        this.calc(c);
        return c;
    }

    private AVLNode<T> rotateRight(AVLNode<T> n) {
        AVLNode<T> c = n.getLeft();
        n.setLeft(c.getRight());
        c.setRight(n);
        this.calc(n);
        this.calc(c);
        return c;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) { throw new IllegalArgumentException(); }
        AVLNode<T> r = new AVLNode<T>(null);
        this.root = this.remo
    }

    private AVLNode<T> remove(AVLNode<T> n, AVLNode<T> r, T d) {
        if (n == null) { throw new NoSuchElementException(); }
        if (d.)
    }
    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) { throw new IllegalArgumentException(); }
        return this.get(this.root, data);
    }

    private T get(AVLNode<T> n, T d) {
        if (n == null) { throw new NoSuchElementException(); }
        if (d.compareTo(n.getData()) < 0) {
            return this.get(n.getLeft(), d);
        } else if (d.compareTo(n.getData()) > 0) {
            return this.get(n.getRight(), d);
        } else {
            return d;
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        try {
            this.get(data);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return this.root == null ? -1: 1 + Math.max(
            this.root.getLeft() == null ? -1: this.root.getLeft().getHeight(),
            this.root.getRight() == null ? -1: this.root.getRight().getHeight()
        );
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * This must be done recursively.
     *
     * Your list should not have duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {

    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * This must be done recursively.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @return a sorted list of data that is > data1 and < data2
     * @throws IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     */
    public List<T> sortedInBetween(T data1, T data2) {

    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
