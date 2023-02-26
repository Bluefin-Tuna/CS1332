import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Tanush Chopra
 * @version 1.0
 * @userid tchopra32
 * @GTID 903785867
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        this.backingArray = (T[]) (new Comparable[MaxHeap.INITIAL_CAPACITY]);
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     * 
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("ArrayList cannot be null.");
        }
        this.backingArray = (T[]) (new Comparable[2 * data.size() + 1]);
        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data to be put into MaxHeap cannot be null.");
            }
            this.backingArray[i + 1] = data.get(i);
        }
        this.size = data.size();
        for (int i = this.size / 2; i >= 1; --i) {
            this.maxHeapify(i);
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be put into MaxHeap cannot be null.");
        }
        if (++this.size >= this.backingArray.length) {
            T[] t = (T[]) (new Comparable[2 * this.backingArray.length]);
            for (int i = 1; i < this.size; ++i) {
                t[i] = this.backingArray[i];
            }
            this.backingArray = t;
        }
        this.backingArray[this.size] = data;
        int i = this.size;
        while (i > 1 && this.backingArray[i / 2].compareTo(this.backingArray[i]) < 0) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("MaxHeap is empty meaning there is no element to remove.");
        }
        T d = swap(1, this.size--);
        maxHeapify(1);
        this.backingArray[this.size + 1] = null;
        return d;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("MaxHeap is empty meaning there is no max to get.");
        }
        return this.backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        this.backingArray = (T[]) (new Comparable[MaxHeap.INITIAL_CAPACITY]);
        this.size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Private helper method for swapping two elements in the heap.
     *
     * @param i index of the first element to swap
     * @param j index of the second element to swap
     * @return data stored at the first index
     */
    private T swap(int i, int j) {
        T d = this.backingArray[i];
        this.backingArray[i] = this.backingArray[j];
        this.backingArray[j] = d;
        return d;
    }

    /**
     * Private recursive helper method for maxHeapifying a given index.
     *
     * @param i index to recursively maxHeapify on
     */
    private void maxHeapify(int i) {
        int la = i;
        if (2 * i <= this.size && this.backingArray[2 * i].compareTo(this.backingArray[la]) > 0) {
            la = 2 * i;
        }
        if (2 * i + 1 <= this.size && this.backingArray[2 * i + 1].compareTo(this.backingArray[la]) > 0) {
            la = 2 * i + 1;
        }
        if (la != i) {
            swap(i, la);
            maxHeapify(la);
        }
    }
}
