package dsa.dc;

import java.util.Arrays;

public class ClosestPair2D {

    public record Pt(double x, double y) {}

    public static double closest(Pt[] pts, Metrics m) {
        if (pts.length < 2) return Double.POSITIVE_INFINITY;

        // points sorted by X
        Pt[] px = pts.clone(); m.alloc(pts.length);
        Arrays.sort(px, (a,b) -> { m.cmp(); return Double.compare(a.x(), b.x()); });

        // aux array for Y-merge (reused)
        Pt[] py = new Pt[pts.length]; m.alloc(pts.length);

        m.enter();
        double ans = rec(px, 0, pts.length - 1, py, m);
        m.leave();
        return ans;
    }

    // returns minimal distance on px[l..r] (px is kept sorted by X)
    // additionally, leaves px[l..r] sorted by Y in py[l..r], which parent uses to build its strip
    private static double rec(Pt[] px, int l, int r, Pt[] py, Metrics m) {
        int n = r - l + 1;
        if (n <= 3) {
            // brute force + also sort by Y into py
            double best = Double.POSITIVE_INFINITY;
            for (int i = l; i <= r; i++) {
                for (int j = i + 1; j <= r; j++) {
                    best = Math.min(best, dist(px[i], px[j]));
                }
            }
            // sort px[l..r] by Y into py[l..r]
            Pt[] tmp = Arrays.copyOfRange(px, l, r + 1); // small (<=3)
            Arrays.sort(tmp, (a,b) -> Double.compare(a.y(), b.y()));
            for (int i = 0; i < tmp.length; i++) py[l + i] = tmp[i];
            return best;
        }

        int mid = (l + r) >>> 1;
        double midX = px[mid].x();

        double dl = rec(px, l, mid,  py, m);
        double dr = rec(px, mid+1, r, py, m);
        double d  = Math.min(dl, dr);

        // merge py[l..mid] and py[mid+1..r] (both already sorted by Y) into tmp by Y
        Pt[] tmp = new Pt[r - l + 1]; m.alloc(tmp.length);
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (py[i].y() <= py[j].y()) tmp[k++] = py[i++];
            else                         tmp[k++] = py[j++];
        }
        while (i <= mid) tmp[k++] = py[i++];
        while (j <= r)   tmp[k++] = py[j++];

        // copy back to py[l..r] and build strip (|x - midX| < d)
        int s = 0;
        for (int t = 0; t < k; t++) {
            py[l + t] = tmp[t];
            if (Math.abs(tmp[t].x() - midX) < d) tmp[s++] = tmp[t]; // reuse tmp as strip buffer
        }

        // scan at most next 7 neighbors in strip (already Y-sorted)
        for (int a = 0; a < s; a++) {
            for (int b = a + 1; b < s && (tmp[b].y() - tmp[a].y()) < d && b <= a + 7; b++) {
                d = Math.min(d, dist(tmp[a], tmp[b]));
            }
        }
        return d;
    }

    private static double dist(Pt p, Pt q) {
        double dx = p.x() - q.x(), dy = p.y() - q.y();
        return Math.hypot(dx, dy);
    }
}
