package sorting.app;

import java.util.Comparator;

public final class Sorting {
    private Sorting() {}

    private static <T> int cmp(Comparator<? super T> c, T x, T y, SortStats s) {
        s.comparisons++;
        return c.compare(x, y);
    }

    private static <T> void swap(T[] a, int i, int j, SortStats s) {
        if (i == j) return;
        T t = a[i]; a[i] = a[j]; a[j] = t; s.moves += 3;
    }

    public static <T> void insertionSort(T[] a, Comparator<? super T> c, SortStats s) {
        s.start();
        for (int i = 1; i < a.length; i++) {
            T key = a[i]; s.moves++;
            int j = i - 1;
            while (j >= 0 && cmp(c, a[j], key, s) > 0) { a[j + 1] = a[j]; s.moves++; j--; }
            a[j + 1] = key; s.moves++;
        }
        s.stop();
    }

    public static <T> void selectionSort(T[] a, Comparator<? super T> c, SortStats s) {
        s.start();
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) if (cmp(c, a[j], a[min], s) < 0) min = j;
            swap(a, i, min, s);
        }
        s.stop();
    }

    public static <T> void mergeSort(T[] a, Comparator<? super T> c, SortStats s) {
        s.start();
        @SuppressWarnings("unchecked") T[] aux = (T[]) new Object[a.length];
        mergeRec(a, aux, 0, a.length - 1, c, s);
        s.stop();
    }

    private static <T> void mergeRec(T[] a, T[] aux, int lo, int hi, Comparator<? super T> c, SortStats s) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeRec(a, aux, lo, mid, c, s);
        mergeRec(a, aux, mid + 1, hi, c, s);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (cmp(c, a[i], a[j], s) <= 0) aux[k++] = a[i++];
            else aux[k++] = a[j++];
            s.moves++;
        }
        while (i <= mid) { aux[k++] = a[i++]; s.moves++; }
        while (j <= hi) { aux[k++] = a[j++]; s.moves++; }
        for (k = lo; k <= hi; k++) { a[k] = aux[k]; s.moves++; }
    }

    public static <T> void quickSort(T[] a, Comparator<? super T> c, SortStats s) {
        s.start(); quick(a, 0, a.length - 1, c, s); s.stop();
    }
    private static <T> void quick(T[] a, int lo, int hi, Comparator<? super T> c, SortStats s) {
        int i = lo, j = hi; T pivot = a[lo + (hi - lo) / 2];
        while (i <= j) {
            while (cmp(c, a[i], pivot, s) < 0) i++;
            while (cmp(c, a[j], pivot, s) > 0) j--;
            if (i <= j) { swap(a, i, j, s); i++; j--; }
        }
        if (lo < j) quick(a, lo, j, c, s);
        if (i < hi) quick(a, i, hi, c, s);
    }

    public static void countingSort(int[] a, SortOrder order, SortStats s) {
        s.start();
        if (a.length == 0) { s.stop(); return; }
        int min = a[0], max = a[0];
        for (int v : a) { if (v < min) min = v; if (v > max) max = v; }
        int k = max - min + 1;
        int[] count = new int[k];
        for (int v : a) { count[v - min]++; s.moves++; }
        for (int i = 1; i < k; i++) count[i] += count[i - 1];
        int[] out = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            int v = a[i];
            int pos = --count[v - min];
            out[pos] = v; s.moves++;
        }
        if (order == SortOrder.ASCENDING) {
            System.arraycopy(out, 0, a, 0, a.length);
        } else {
            for (int i = 0; i < a.length; i++) a[i] = out[a.length - 1 - i];
        }
        s.moves += a.length;
        s.stop();
    }
}
