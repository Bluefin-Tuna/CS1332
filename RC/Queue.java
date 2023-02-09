public class Queue<T>  {
    
    private int size;
    private Node head;
    private Node tail;

    private class Node {
        
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Queue() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0 || (this.tail == null && this.head == null);
    }

    public void enqueue(T d) {
        ++this.size;
        if (isEmpty()) {
            this.tail = new Node(d, null);
            this.head = this.tail;
            return;
        }
        this.tail.setNext(new Node(d, null));
    }
}

public class Queue<T>  {
    private Stack<T> s1;
    private Stack<T> s2;
    public Queue<T>() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    public void enqueue(T data) {
        s1.push(data);
    }
    public T dequeue() {
        if (s1.size() == 0 && s2.size() == 0) {
            throw new NoSuchElementException();
        } else {
            if (s2.size() == 0) {         
                while (s1.size() != 0) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }
    }

    public int size() {
        return s1.size() + s2.size();
    }
}