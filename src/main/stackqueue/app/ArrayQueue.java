package stackqueue.app;

public final class ArrayQueue<T> implements Queue<T> {
    private final Object[] data;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.data = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override public void enqueue(T value) {
        if (isFull()) throw new IllegalStateException("queue overflow");
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("queue underflow");
        T v = (T) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return v;
    }

    @SuppressWarnings("unchecked")
    @Override public T peek() {
        if (isEmpty()) throw new IllegalStateException("queue is empty");
        return (T) data[head];
    }

    @Override public boolean isEmpty() { return size == 0; }
    @Override public boolean isFull() { return size == data.length; }
    @Override public int size() { return size; }
    @Override public int capacity() { return data.length; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[head ");
        for (int i = 0; i < size; i++) {
            int idx = (head + i) % data.length;
            sb.append(data[idx]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append(" tail]");
        return sb.toString();
    }
}
