import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
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
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the CSLL is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size)
            );
        }
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an CSLL.");
        }
        if (index == 0) {
            addToFront(data);
            return;
        } else if (index == size) {
            addToBack(data);
            return;
        } else {
            CircularSinglyLinkedListNode<T> c = this.head;
            for (int i = 0; i < index - 1; i++) {
                c = c.getNext();
            }
            CircularSinglyLinkedListNode<T> n = new CircularSinglyLinkedListNode<T>(data, c.getNext());
            c.setNext(n);
            this.size += 1;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an CSLL.");
        }
        if (this.head == null) {
            this.head = new CircularSinglyLinkedListNode<T>(data);
            this.head.setNext(this.head);
            this.size += 1;
        } else {
            CircularSinglyLinkedListNode<T> n = new CircularSinglyLinkedListNode<T>(
                this.head.getData(), this.head.getNext()
            );
            this.head.setNext(n);
            this.head.setData(data);
            this.size += 1;
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an CSLL.");
        }
        if (this.head == null) {
            this.head = new CircularSinglyLinkedListNode<T>(data);
            this.head.setNext(this.head);
            this.size += 1;
        } else if (this.size == 1) {
            CircularSinglyLinkedListNode<T> n = new CircularSinglyLinkedListNode<T>(data, this.head);
            this.head.setNext(n);
            this.size += 1;
        } else {
            CircularSinglyLinkedListNode<T> n = new CircularSinglyLinkedListNode<T>(
                this.head.getData(), this.head.getNext()
            );
            this.head.setData(data);
            this.head.setNext(n);
            this.head = n;
            this.size += 1;
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the CSLL is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size - 1)
            );
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (this.head.getNext() == this.head) {
            T d = this.head.getData();
            this.head = null;
            this.size -= 1;
            return d;
        }
        CircularSinglyLinkedListNode<T> n = this.head;
        for (int i = 1; i < index; i++) {
            n = n.getNext();
        }
        T d = n.getNext().getData();
        n.setNext(n.getNext().getNext());
        this.size -= 1;
        return d;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (this.head == null) {
            throw new NoSuchElementException("CSLL is empty so there is no node to remove.");
        }
        if (this.head.getNext() == this.head) {
            T d = this.head.getData();
            this.head = null;
            this.size -= 1;
            return d;
        }
        T d = this.head.getData();
        this.head.setData(this.head.getNext().getData());
        this.head.setNext(this.head.getNext().getNext());
        this.size -= 1;
        return d;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (this.head == null) {
            throw new NoSuchElementException("CSLL is empty so there is no node to remove.");
        }
        return removeAtIndex(this.size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                String.format("%d-th index of the CSLL is out of the bounds of the ArrayList is %d to %d",
                        index, 0, this.size - 1)
            );
        }
        if (index == 0) {
            return this.head.getData();
        } else {
            CircularSinglyLinkedListNode<T> n = this.head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which is not storable inside an CSLL.");
        }
        if (isEmpty()) {
            throw new NoSuchElementException(data + " was not found inside this CSLL.");
        }
        if (this.head.getData().equals(data) && this.head.getNext() == this.head) {
            return removeFromFront();
        }
        CircularSinglyLinkedListNode<T> s = this.head.getData().equals(data) ? this.head : null;
        CircularSinglyLinkedListNode<T> n = this.head;
        while (n.getNext() != this.head) {
            n = n.getNext();
            if (n.getData().equals(data)) {
                s = n;
            }
        }
        if (s == null) {
            throw new NoSuchElementException(data + " was not found inside this CSLL.");
        }
        T d = s.getData();
        s.setData(s.getNext().getData());
        s.setNext(s.getNext().getNext());
        this.size -= 1;
        return d;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[this.size];
        CircularSinglyLinkedListNode<T> n = this.head;
        for (int i = 0; i < size; i++) {
            arr[i] = n.getData();
            n = n.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
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
        // DO NOT MODIFY!
        return size;
    }
}