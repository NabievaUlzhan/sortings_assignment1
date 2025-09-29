package dsa.dc;

public class MergeSort {
    private static final int CUTOFF = 24; 

    public static void sort(int[] a, Metrics m) {
        int[] tmp = new int[a.length]; m.alloc(a.length);
        sort(a, 0, a.length - 1, tmp, m);
    }

    private static void sort(int[] a, int l, int r, int[] tmp, Metrics m) {
        if (l >= r) return;
        if (r - l + 1 <= CUTOFF) { Util.insertionSort(a, l, r, m); return; }
        m.enter();
        int mid = (l + r) >>> 1;
        sort(a, l, mid, tmp, m);
        sort(a, mid + 1, r, tmp, m);
        if (Util.compare(a[mid], a[mid+1], m) <= 0) { m.leave(); return; } 
        merge(a, l, mid, r, tmp, m);
        m.leave();
    }

    private static void merge(int[] a, int l, int mid, int r, int[] tmp, Metrics m) {
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            if (Util.compare(a[i], a[j], m) <= 0) tmp[k++] = a[i++];
            else tmp[k++] = a[j++];
            m.write();
        }
        while (i <= mid) { tmp[k++] = a[i++]; m.write(); }
        while (j <= r) { tmp[k++] = a[j++]; m.write(); }
        for (int t = l; t <= r; t++) { a[t] = tmp[t]; m.write(); }
    }
}
