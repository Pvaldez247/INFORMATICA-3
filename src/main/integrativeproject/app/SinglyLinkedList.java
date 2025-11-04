package integrativeproject.app;

import java.util.NoSuchElementException;

public final class SinglyLinkedList<T> {
    private static final class Node<T>{ T v; Node<T> n; Node(T v){this.v=v;} }
    private Node<T> h, t; private int s;
    public int size(){ return s; } public boolean isEmpty(){ return s==0; }
    public void addLast(T v){ Node<T> x=new Node<>(v); if(t==null){h=t=x;} else{t.n=x; t=x;} s++; }
    public T removeFirst(){ if(s==0) throw new NoSuchElementException("empty"); T v=h.v; h=h.n; if(h==null)t=null; s--; return v; }
    public T getFirst(){ if(s==0) throw new NoSuchElementException("empty"); return h.v; }
    public Object[] toArray(){ Object[] a=new Object[s]; int i=0; for(Node<T> n=h;n!=null;n=n.n) a[i++]=n.v; return a; }
}
