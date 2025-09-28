package dsa.dc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;import java.util.Random;

public class MergeSortTest {
    @Test void randomArrays() {
        Random r = new Random(1);
        for (int t = 0; t < 50; t++) {
            int n = 100 + r.nextInt(500);
            int[] a = r.ints(n).toArray();
            int[] b = a.clone();
            Metrics m = new Metrics();
            MergeSort.sort(a, m);
            Arrays.sort(b);
            assertArrayEquals(b, a);
        }
    }
}