package dsa.dc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class ClosestPair2DTest {
    private static double slow(Pt[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++)
            for (int j = i + 1; j < pts.length; j++)
                best = Math.min(best, Math.hypot(pts[i].x()-pts[j].x(), pts[i].y()-pts[j].y()));
        return best;
    }

    public record Pt(double x, double y) {}

    @Test void smallNAgainstN2() {
        Random r = new Random(5);
        for (int n = 2; n <= 2000; n += 199) {
            ClosestPair2D.Pt[] a = new ClosestPair2D.Pt[n];
            Pt[] b = new Pt[n];
            for (int i = 0; i < n; i++) { double x=r.nextDouble(), y=r.nextDouble(); a[i]=new ClosestPair2D.Pt(x,y); b[i]=new Pt(x,y);}
            double fast = ClosestPair2D.closest(a, new Metrics());
            double slowD = slow(b);
            assertEquals(slowD, fast, 1e-9);
        }
    }
}