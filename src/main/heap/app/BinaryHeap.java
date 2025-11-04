package heap.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Generic binary heap (array-based) supporting MIN or MAX behavior.
 * Operations:
 *  - insert (offer)
 *  - peek (element at top)
 *  - poll (remove top)
 *  - removeAt(index)  [0..size-1]
 *  - build(Collection)
 *  - clear, size, isEmpty, toArray
 */
public final class BinaryHeap<T> {

    private final ArrayList<T> data = new ArrayList<>();
    private final Comparator<? super T> cmp; // determines MIN or MAX behavior

    public BinaryHeap(Comparator<? super T> comparator) {
        if (comparator == null) throw new IllegalArgumentException("comparator is null");
        this.cmp = comparator;
    }

    public static <T extends Comparable<? super T>> BinaryHeap<T> ofType(HeapType type) {
        return new BinaryHeap<T>(type == HeapType.MIN ? Comparator.naturalOrder()
                                                      : Comparator.reverseOrder());
    }

    // ---- core queries ----
    public int size() { return data.size(); }
    public boolean isEmpty() { return data.isEmpty(); }

    // ---- insert / top / remove top ----
    public void insert(T value) {
        data.add(value);
        siftUp(size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("heap is empty");
        return data.get(0);
    }

    public T poll() {
        if (isEmpty()) throw new NoSuchElementException("heap is empty");
        T top = data.get(0);
        T last = data.remove(size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            siftDown(0);
        }
        return top;
    }

    /** Remove element at given array index (0..size-1). */
    public T removeAt(int index) {
        rangeCheck(index);
        int lastIdx = size() - 1;
        T removed = data.get(index);
        T last = data.remove(lastIdx);
        if (index < data.size()) {
            data.set(index, last);
            // decide direction
            if (index > 0 && cmp.compare(data.get(index), data.get(parent(index))) < 0) {
                siftUp(index);
            } else {
                siftDown(index);
            }
        }
        return removed;
    }

    // ---- bulk ops ----
    public void clear() { data.clear(); }

    public void build(Collection<? extends T> values) {
        data.clear();
        data.addAll(values);
        // heapify in O(n)
        for (int i = parent(size() - 1); i >= 0; i--) siftDown(i);
    }

    public Object[] toArray() { return data.toArray(); }

    public List<T> snapshot() { return new ArrayList<>(data); }

    // ---- helpers ----
    private void siftUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (cmp.compare(data.get(i), data.get(p)) < 0) {
                swap(i, p);
                i = p;
            } else break;
        }
    }

    private void siftDown(int i) {
        int n = size();
        while (true) {
            int l = left(i), r = right(i), best = i;
            if (l < n && cmp.compare(data.get(l), data.get(best)) < 0) best = l;
            if (r < n && cmp.compare(data.get(r), data.get(best)) < 0) best = r;
            if (best == i) break;
            swap(i, best);
            i = best;
        }
    }

    private static int parent(int i) { return (i - 1) / 2; }
    private static int left(int i) { return 2 * i + 1; }
    private static int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        T t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("index " + index);
    }

    @Override public String toString() { return data.toString(); }
}
