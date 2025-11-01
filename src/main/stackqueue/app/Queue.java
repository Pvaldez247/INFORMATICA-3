package stackqueue.app;

public interface Queue<T> {
    void enqueue(T value);
    T dequeue();
    T peek();
    boolean isEmpty();
    boolean isFull();
    int size();
    int capacity();
}
