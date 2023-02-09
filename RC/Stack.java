import java.util.NoSuchElementException;

public class Stack<T> implements StackADT<T> {

    private static final int INITIAL_SIZE = 100;
    
    private T[] a;
    private int size;

    public Stack<T>() {
        this.a = (T[]) (new Object[Stack.INITIAL_SIZE]);
    }

    public void push(T d) {
        this.a[this.size++] = d;
    }

    public T pop() {
        T d = this.a[--size];
        this.a[size] = null;
        return d;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}