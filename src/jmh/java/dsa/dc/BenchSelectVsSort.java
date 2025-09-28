package dsa.dc;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BenchSelectVsSort {
    @Param({"10000","50000","100000"})
    public int n;
    public int[] data;
    public int k;

    @Setup(Level.Invocation)
    public void setup() {
        Random r = new Random(123);
        data = new int[n];
        for (int i = 0; i < n; i++) data[i] = r.nextInt();
        k = n/2;
    }

    @Benchmark
    public int selectMoM5() {
        int[] a = data.clone();
        return SelectMoM5.select(a, k, new Metrics());
    }

    @Benchmark
    public int sortThenPick() {
        int[] a = data.clone();
        Arrays.sort(a);
        return a[k];
    }
}