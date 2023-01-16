import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Tanush Chopra   
 * @version 1.0
 * @userid tchopra32
 * @GTID 903785867
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        this.backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        this.size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the ArrayList is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size - 1)
            );
        }
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an ArrayList.");
        }
        if (index == 0) {
            addToFront(data);
            return;
        } else if (index == this.size) {
            addToBack(data);
            return;
        }
        if (checkResizeNeed(this.size + 1)) {
            resizeArray();
        }
        for (int i = size; i > index; i--) {
            this.backingArray[i] = this.backingArray[i - 1];
        }
        this.backingArray[index] = data;
        this.size += 1;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an ArrayList.");
        }
        if (checkResizeNeed(this.size + 1)) {
            resizeArray();
        }
        for (int i = size; i > 0; i--) {
            this.backingArray[i] = this.backingArray[i - 1];
        }
        this.backingArray[0] = data;
        this.size += 1;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an ArrayList.");
        }
        if (checkResizeNeed(this.size + 1)) {
            resizeArray();
        }
        this.backingArray[this.size] = data;
        this.size += 1;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the ArrayList is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size - 1)
            );
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == this.size - 1) {
            return removeFromBack();
        }
        T d = this.backingArray[index];
        for (int i = index; i < size - 1; i++) {
            this.backingArray[i] = this.backingArray[i + 1];
        }
        this.backingArray[size - 1] = null;
        this.size -= 1;
        return d;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayList has no data stored inside it.");
        }
        T d = this.backingArray[0];
        for (int i = 0; i < this.size - 1; i++) {
            this.backingArray[i] = this.backingArray[i + 1];
        }
        this.backingArray[size - 1] = null;
        this.size -= 1;
        return d;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T d = this.backingArray[this.size - 1];
        this.backingArray[this.size - 1] = this.backingArray[this.size];
        this.size -= 1;
        return d;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the ArrayList is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size - 1)
            );
        }
        return this.backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.backingArray = (T[]) (new Object[this.backingArray.length]);
        this.size = 0;
    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
     * Returns a boolean representing if the backingArray needs to be resized.
     *
     * @param projSize int representing the future size after an operation is performed.
     * @return boolean representing if the backingArray needs to be resized.
     */
    private boolean checkResizeNeed(int projSize) {
        if (projSize > this.backingArray.length) {
            return true;
        }
        return false;
    }

    /**
     * Mutating opreation for resizingArray.
     */
    private void resizeArray() {
        T[] t = (T[]) (new Object[this.backingArray.length * 2]);
        for (int i = 0; i < this.backingArray.length; i++) {
            t[i] = this.backingArray[i];
        }
        this.backingArray = t;
    }
}