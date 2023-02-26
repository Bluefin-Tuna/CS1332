import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        for (T d: data) {
            if (d == null) {
                throw new IllegalArgumentException();
            }
            this.add(d);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (this.root == null) {
            this.root = new BSTNode<T>(data);
            ++this.size;
            return;
        }
        this.add(data, this.root);
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        BSTNode<T> r = new BSTNode<T>(null);
        this.root = this.remove(data, this.root, r);
        return r.getData();
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return this.get(data, this.root);
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.get(data, this.root);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> l = new ArrayList<T>(this.size);
        this.preOrder(this.root, l);
        return l;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> l = new ArrayList<T>(this.size);
        this.inOrder(this.root, l);
        return l;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> l = new ArrayList<T>(this.size);
        this.postOrder(this.root, l);
        return l;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> l = new ArrayList<T>(this.size);
        Queue<BSTNode<T>> q = new LinkedList<BSTNode<T>>();
        if (this.root == null) {
            return l;
        }
        q.add(this.root);
        while (!q.isEmpty()) {
            BSTNode<T> n = q.poll();
            l.add(n.getData());
            if (n.getLeft() != null) {
                q.add(n.getLeft());
            }
            if (n.getRight() != null) {
                q.add(n.getRight());
            }
        }
        return l;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return this.height(this.root);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        if (k < 0 || k > this.size) {
            throw new IllegalArgumentException();
        }
        LinkedList<T> l = new LinkedList<T>();
        this.kLargest(root, k, l);
        return l;
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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

    /**
     * Private helper method for the add method.
     *
     * @param d T data representing the data to add
     * @param n BSTNode to recurse on
     */
    private void add(T d, BSTNode<T> n) {
        if (d.compareTo(n.getData()) < 0) {
            if (n.getLeft() == null) {
                n.setLeft(new BSTNode<T>(d));
                ++this.size;
                return;
            } else {
                this.add(d, n.getLeft());
            }
        } else if (d.compareTo(n.getData()) > 0) {
            if (n.getRight() == null) {
                n.setRight(new BSTNode<T>(d));
                ++this.size;
                return;
            } else {
                this.add(d, n.getRight());
            }
        } else {
            return;
        }
    }

    /**
     * Private helper method for the get method.
     *
     * @param d T data to get from BST
     * @param n BSTNode to recurse on
     * @return T data gotten from the node
     */
    private T get(T d, BSTNode<T> n) {
        if (n == null) {
            throw new NoSuchElementException();
        }
        if (d.compareTo(n.getData()) < 0) {
            return this.get(d, n.getLeft());
        } else if (d.compareTo(n.getData()) > 0) {
            return this.get(d, n.getRight());
        } else {
            return n.getData();
        }
    }

    /**
     * Private helper method for the preorder method.
     *
     * @param n BSTNode to recurse on
     * @param l List to store the data from the BST ordered by preorder traversal
     */
    private void preOrder(BSTNode<T> n, List<T> l) {
        if (n == null) {
            return;
        }
        l.add(n.getData());
        this.preOrder(n.getLeft(), l);
        this.preOrder(n.getRight(), l);
    }

    /**
     * Private helper method for the inorder method.
     *
     * @param n BSTNode to recurse on
     * @param l List to store the data from the BST ordered by inorder traversal
     */
    private void inOrder(BSTNode<T> n, List<T> l) {
        if (n == null) {
            return;
        }
        this.inOrder(n.getLeft(), l);
        l.add(n.getData());
        this.inOrder(n.getRight(), l);
    }

    /**
     * Private helper method for the postorder method.
     *
     * @param n BSTNode to recurse on
     * @param l List to store the data from the BST ordered by postorder traversal
     */
    private void postOrder(BSTNode<T> n, List<T> l) {
        if (n == null) {
            return;
        }
        this.postOrder(n.getLeft(), l);
        this.postOrder(n.getRight(), l);
        l.add(n.getData());
    }

    /**
     * Private helper method for the height method.
     *
     * @param n BSTNode to recurse on
     * @return int representing the height of the BST
     */
    private int height(BSTNode<T> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(
            this.height(n.getLeft()),
            this.height(n.getRight())
        );
    }

    /**
     * Private helper method for the kLargest method.
     *
     * @param n BSTNode to recurse on
     * @param k int representing the number of nodes of data to retrieve
     * @param l LinkedList holding the data retrieved
     */
    private void kLargest(BSTNode<T> n, int k, LinkedList<T> l) {
        if (l.size() >= k || n == null) {
            return;
        }
        this.kLargest(n.getRight(), k, l);
        if (l.size() >= k) {
            return;
        }
        l.addFirst(n.getData());
        if (l.size() >= k) {
            return;
        }
        this.kLargest(n.getLeft(), k, l);
    }

    /**
     * Private helper method for finding the successor of a node.
     *
     * @param n BSTNode to recurse on
     * @param c BSTNode representing the child
     * @return BSTNode representing the modified node
     */
    private BSTNode<T> findSuccessor(BSTNode<T> n, BSTNode<T> c) {
        if (n.getLeft() == null) {
            c.setData(n.getData());
            return n.getRight();
        }
        n.setLeft(this.findSuccessor(n.getLeft(), c));
        return n;
    }

    /**
     * Private helper method for the remove method.
     *
     * @param d T data representing the data to remove from the BST
     * @param n BSTNode to recurse on
     * @param r BSTNode that has been removed
     * @return BSTNode with the data removed
     */
    private BSTNode<T> remove(T d, BSTNode<T> n, BSTNode<T> r) {
        if (n == null) {
            throw new NoSuchElementException();
        }
        if (d.compareTo(n.getData()) < 0) {
            n.setLeft(this.remove(d, n.getLeft(), r));
        } else if (d.compareTo(n.getData()) > 0) {
            n.setRight(this.remove(d, n.getRight(), r));
        } else {
            r.setData(n.getData());
            --this.size;
            if (n.getLeft() == null) {
                return n.getRight();
            } else if (n.getRight() == null) {
                return n.getLeft();
            } else {
                BSTNode<T> c = new BSTNode<T>(null);
                n.setRight(this.findSuccessor(n.getRight(), c));
                n.setData(c.getData());
            }
        }
        return n;
    }
}