package dsa.dc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class QuickSortTest {
    @Test void adversarialWithRandomPivot() {
        for (int n : new int[]{1,2,3,10,100,1000}) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i; // sorted
            Metrics m = new Metrics();
            QuickSort.sort(a, m);
            for (int i = 1; i < n; i++) assertTrue(a[i-1] <= a[i]);
            assertTrue(m.maxDepth <= 2 * (int)Math.floor(Math.log(n + 1)/Math.log(2.0)) + 20);
        }
    }
}