package integrator.app;

public final class ArrayStack<T> {
    private final Object[] data; private int top;
    public ArrayStack(int capacity){ if(capacity<=0) throw new IllegalArgumentException("capacity"); data=new Object[capacity]; }
    public void push(T v){ if(top==data.length) throw new IllegalStateException("stack overflow"); data[top++]=v; }
    @SuppressWarnings("unchecked") public T pop(){ if(top==0) throw new IllegalStateException("stack underflow"); T v=(T)data[--top]; data[top]=null; return v; }
    public boolean isEmpty(){ return top==0; } public void clear(){ while(top>0) data[--top]=null; } public int size(){ return top; }
}
