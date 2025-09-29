package dsa.dc;

import java.util.Arrays;

public class SelectMoM5 {

    public static int select(int[] a, int k, Metrics m) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of range");
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int l, int r, int k, Metrics m) {
        while (true) {
            if (l == r) return a[l];

            int pivot = medianOfMedians(a, l, r, m);
            int p = partition(a, l, r, pivot, m);

            if (k == p) return a[p];
            if (k < p) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
    }

    private static int partition(int[] a, int l, int r, int pivot, Metrics m) {
        int i = l, lt = l, gt = r;
        while (i <= gt) {
            int cmp = Util.compare(a[i], pivot, m);
            if (cmp < 0) { Util.swap(a, lt++, i++, m); }
            else if (cmp > 0) { Util.swap(a, i, gt--, m); }
            else { i++; }
        }
        return lt;
    }

    private static int medianOfMedians(int[] a, int l, int r, Metrics m) {
        int n = r - l + 1;
        if (n <= 5) {
            Arrays.sort(a, l, r + 1);
            m.alloc(n);               
            return a[l + n / 2];
        }

        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int gs = l + i * 5;
            int ge = Math.min(gs + 4, r);
            Arrays.sort(a, gs, ge + 1);     m.alloc(ge - gs + 1);
            int medIdx = gs + (ge - gs) / 2;
            Util.swap(a, l + i, medIdx, m); 
        }

        int midIndex = l + (numGroups - 1) / 2;
        return select(a, l, l + numGroups - 1, midIndex, m);
    }
}
