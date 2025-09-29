package dsa.dc;

public class QuickSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        sort(a, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int l, int r, Metrics m) {
        while (l < r) {
            if (r - l + 1 <= CUTOFF) { Util.insertionSort(a, l, r, m); return; }
            m.enter();
            int p = partitionRand(a, l, r, m);
            int leftSize = p - 1 - l + 1;
            int rightSize = r - (p + 1) + 1;
            if (leftSize < rightSize) {
                sort(a, l, p - 1, m); 
                l = p + 1; 
            } else {
                sort(a, p + 1, r, m);
                r = p - 1;
            }
            m.leave();
        }
    }

    private static int partitionRand(int[] a, int l, int r, Metrics m) {
        int pv = Util.randPivot(l, r);
        Util.swap(a, pv, r, m);
        int x = a[r]; m.write();
        int i = l;
        for (int j = l; j < r; j++) {
            if (Util.compare(a[j], x, m) <= 0) { Util.swap(a, i, j, m); i++; }
        }
        Util.swap(a, i, r, m);
        return i;
    }
}
