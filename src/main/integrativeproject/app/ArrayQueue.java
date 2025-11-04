package integrativeproject.app;

public final class ArrayQueue<T> {
    private final Object[] data; private int head, tail, size;
    public ArrayQueue(int capacity){ if(capacity<=0) throw new IllegalArgumentException("capacity"); data=new Object[capacity]; }
    public void enqueue(T v){ if(size==data.length) throw new IllegalStateException("queue overflow"); data[tail]=v; tail=(tail+1)%data.length; size++; }
    @SuppressWarnings("unchecked") public T dequeue(){ if(size==0) throw new IllegalStateException("queue underflow"); T v=(T)data[head]; data[head]=null; head=(head+1)%data.length; size--; return v; }
    @SuppressWarnings("unchecked") public T peek(){ if(size==0) throw new IllegalStateException("queue empty"); return (T)data[head]; }
    public boolean isEmpty(){ return size==0; } public int size(){ return size; } public int capacity(){ return data.length; }
}
