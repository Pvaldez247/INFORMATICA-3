package linkedlist.app;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SinglyLinkedList<T> implements Iterable<T> {

    private static final class Node<T> {
        T value;
        Node<T> next;
        Node(T v) { this.value = v; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void addFirst(T value) {
        Node<T> n = new Node<>(value);
        n.next = head;
        head = n;
        if (tail == null) tail = n;
        size++;
    }

    public void addLast(T value) {
        Node<T> n = new Node<>(value);
        if (tail == null) head = tail = n;
        else { tail.next = n; tail = n; }
        size++;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index " + index);
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }
        Node<T> prev = nodeAt(index - 1);
        Node<T> n = new Node<>(value);
        n.next = prev.next;
        prev.next = n;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("list is empty");
        T v = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return v;
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("list is empty");
        if (size == 1) return removeFirst();
        Node<T> prev = nodeAt(size - 2);
        T v = tail.value;
        prev.next = null;
        tail = prev;
        size--;
        return v;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index " + index);
        if (index == 0) return removeFirst();
        Node<T> prev = nodeAt(index - 1);
        Node<T> cur = prev.next;
        prev.next = cur.next;
        if (cur == tail) tail = prev;
        size--;
        return cur.value;
    }

    public boolean removeValue(T value) {
        if (isEmpty()) return false;
        if ((head.value == null && value == null) || (head.value != null && head.value.equals(value))) {
            removeFirst(); return true;
        }
        Node<T> prev = head, cur = head.next;
        while (cur != null) {
            if ((cur.value == null && value == null) || (cur.value != null && cur.value.equals(value))) {
                prev.next = cur.next;
                if (cur == tail) tail = prev;
                size--;
                return true;
            }
            prev = cur; cur = cur.next;
        }
        return false;
    }

    public T get(int index) { return nodeAt(index).value; }

    public T set(int index, T newValue) {
        Node<T> n = nodeAt(index);
        T old = n.value;
        n.value = newValue;
        return old;
    }

    public boolean contains(T value) { return indexOf(value) >= 0; }

    public int indexOf(T value) {
        int i = 0;
        for (Node<T> n = head; n != null; n = n.next, i++) {
            if ((n.value == null && value == null) || (n.value != null && n.value.equals(value))) return i;
        }
        return -1;
    }

    public void clear() {
        for (Node<T> n = head; n != null;) {
            Node<T> nxt = n.next;
            n.next = null; n.value = null; n = nxt;
        }
        head = tail = null; size = 0;
    }

    public Object[] toArray() {
        Object[] a = new Object[size];
        int i = 0;
        for (Node<T> n = head; n != null; n = n.next) a[i++] = n.value;
        return a;
    }

    public void reverse() {
        Node<T> prev = null, cur = head;
        tail = head;
        while (cur != null) {
            Node<T> nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        head = prev;
    }

    private Node<T> nodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index " + index);
        Node<T> n = head;
        for (int i = 0; i < index; i++) n = n.next;
        return n;
    }

    @Override public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cur = head;
            @Override public boolean hasNext() { return cur != null; }
            @Override public T next() {
                if (cur == null) throw new NoSuchElementException();
                T v = cur.value; cur = cur.next; return v;
            }
        };
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> n = head;
        while (n != null) {
            sb.append(n.value);
            n = n.next;
            if (n != null) sb.append(" -> ");
        }
        sb.append("]");
        return sb.toString();
    }
}
