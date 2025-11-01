package sorting.app;

import java.util.Random;

public final class ArrayGenerators {
    private static final Random RND = new Random();
    private ArrayGenerators() {}

    public static int[] randomIntArray(int n, int min, int max) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = min + RND.nextInt(max - min + 1);
        return a;
    }

    public static Integer[] randomIntegerArray(int n, int min, int max) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = min + RND.nextInt(max - min + 1);
        return a;
    }
}
