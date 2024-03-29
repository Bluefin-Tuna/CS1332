import java.util.NoSuchElementException;

/**
 * Your implementation of a LinkedQueue. It should NOT be circular.
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
public class LinkedQueue<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the back of the queue.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null which you cannot store inside an LinkedQueue.");
        }
        this.size += 1;
        if (isEmpty()) {
            this.tail = new LinkedNode<T>(data);
            this.head = this.tail;
            return;
        }
        this.tail.setNext(new LinkedNode<T>(data));
        this.tail = this.tail.getNext();
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedQueue is empty. There are no elements to dequeue.");
        }
        this.size -= 1;
        if (this.size == 0) {
            T d = this.head.getData();
            this.head = null;
            this.tail = null;
            return d;
        }
        T d = this.head.getData();
        this.head = this.head.getNext();
        return d;
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedQueue is empty. There are no elements to peek.");
        }
        return this.head.getData();
    }

    /**
     * Returns the head node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the queue
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the queue
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns whether the LinkedQueue is empty of not.
     *
     * @return boolean representing whether empty or not
     */
    private boolean isEmpty() {
        return this.size == 0 || (this.tail == null && this.head == null);
    }
}
