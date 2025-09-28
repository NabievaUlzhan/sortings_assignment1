package dsa.dc;

import java.util.Random;

public final class Util {
    private static final Random RNG = new Random();

    private Util() {}

    public static void swap(int[] a, int i, int j, Metrics m) {
        int t = a[i]; a[i] = a[j]; a[j] = t; m.write(); m.write(); m.write();
    }

    public static int compare(int x, int y, Metrics m) { m.cmp(); return Integer.compare(x, y); }

    public static void insertionSort(int[] a, int l, int r, Metrics m) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i]; m.write();
            int j = i - 1;
            while (j >= l && compare(a[j], key, m) > 0) { a[j+1] = a[j]; m.write(); j--; }
            a[j+1] = key; m.write();
        }
    }

    public static int randPivot(int l, int r) { return l + RNG.nextInt(r - l + 1); }
}