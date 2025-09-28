package dsa.dc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class SelectMoM5Test {
    @Test void compareWithSort() {
        Random r = new Random(3);
        for (int t = 0; t < 100; t++) {
            int n = 200 + r.nextInt(300);
            int[] a = r.ints(n).toArray();
            int k = r.nextInt(n);
            int[] b = a.clone(); Arrays.sort(b);
            Metrics m = new Metrics();
            int got = SelectMoM5.select(a, k, m);
            assertEquals(b[k], got);
        }
    }
}