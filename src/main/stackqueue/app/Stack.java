package stackqueue.app;

public interface Stack<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();
    boolean isFull();
    int size();
    int capacity();
}
