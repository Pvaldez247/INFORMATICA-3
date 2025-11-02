package stackqueue.app;

public final class ArrayStack<T> implements Stack<T> {
    private final Object[] data;
    private int top;

    public ArrayStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.data = new Object[capacity];
        this.top = 0;
    }

    @Override public void push(T value) {
        if (isFull()) throw new IllegalStateException("stack overflow");
        data[top++] = value;
    }

    @SuppressWarnings("unchecked")
    @Override public T pop() {
        if (isEmpty()) throw new IllegalStateException("stack underflow");
        T v = (T) data[--top];
        data[top] = null;
        return v;
    }

    @SuppressWarnings("unchecked")
    @Override public T peek() {
        if (isEmpty()) throw new IllegalStateException("stack is empty");
        return (T) data[top - 1];
    }

    @Override public boolean isEmpty() { return top == 0; }
    @Override public boolean isFull() { return top == data.length; }
    @Override public int size() { return top; }
    @Override public int capacity() { return data.length; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[bottom ");
        for (int i = 0; i < top; i++) {
            sb.append(data[i]);
            if (i < top - 1) sb.append(", ");
        }
        sb.append(" <- top]");
        return sb.toString();
    }
}
