package recursion.app;

import java.util.ArrayList;
import java.util.List;

public final class RecursionUtils {
    private RecursionUtils() {}

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n <= 1) return 1L;
        return n * factorial(n - 1);
    }

    public static long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        long[] memo = new long[n + 2];
        memo[0] = 0L; memo[1] = 1L;
        return fibMemo(n, memo);
    }

    private static long fibMemo(int n, long[] memo) {
        if (n <= 1) return memo[n];
        if (memo[n] != 0L) return memo[n];
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    public static long power(long base, int exp) {
        if (exp < 0) throw new IllegalArgumentException("exp must be >= 0");
        if (exp == 0) return 1L;
        if (exp == 1) return base;
        long half = power(base, exp / 2);
        long res = half * half;
        if (exp % 2 == 1) res *= base;
        return res;
    }

    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s == null ? "" : s;
        return s.charAt(s.length() - 1) + reverse(s.substring(0, s.length() - 1));
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        return isPalRange(s, 0, s.length() - 1);
    }

    private static boolean isPalRange(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return isPalRange(s, i + 1, j - 1);
    }

    public static int sumArray(int[] a) {
        if (a == null) throw new IllegalArgumentException("array is null");
        return sumRange(a, 0);
    }

    private static int sumRange(int[] a, int i) {
        if (i == a.length) return 0;
        return a[i] + sumRange(a, i + 1);
    }

    public static int binarySearch(int[] a, int key) {
        if (a == null) throw new IllegalArgumentException("array is null");
        return binSearch(a, 0, a.length - 1, key);
    }

    private static int binSearch(int[] a, int lo, int hi, int key) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) return mid;
        if (key < a[mid]) return binSearch(a, lo, mid - 1, key);
        return binSearch(a, mid + 1, hi, key);
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a); b = Math.abs(b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static String toBinaryString(int n) {
        if (n == 0) return "0";
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        return toBin(n);
    }

    private static String toBin(int n) {
        if (n == 0) return "";
        return toBin(n / 2) + (n % 2);
    }

    public static List<String> hanoi(int n, char from, char aux, char to) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        List<String> moves = new ArrayList<>();
        hanoiRec(n, from, aux, to, moves);
        return moves;
    }

    private static void hanoiRec(int n, char from, char aux, char to, List<String> out) {
        if (n == 0) return;
        hanoiRec(n - 1, from, to, aux, out);
        out.add(from + " -> " + to);
        hanoiRec(n - 1, aux, from, to, out);
    }
}
