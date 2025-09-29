package dsa.dc;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Cli {
    public static void main(String[] args) throws Exception {
        Map<String,String> arg = parse(args);
        String algo = arg.getOrDefault("--algo", "mergesort");
        int n = Integer.parseInt(arg.getOrDefault("--n", "100000"));
        int trials = Integer.parseInt(arg.getOrDefault("--trials", "3"));
        String csv = arg.getOrDefault("--csv", "out/metrics.csv");
        int k = Integer.parseInt(arg.getOrDefault("--k", String.valueOf(n/2)));
        Files.createDirectories(Path.of(csv).getParent());
        CsvWriter w = new CsvWriter(csv, "algo,n,time_ms,max_depth,comparisons,writes,allocations");
        for (int t = 0; t < trials; t++) {
            Metrics m = new Metrics();
            long start = System.nanoTime();
            switch (algo) {
                case "mergesort" -> {
                    int[] a = randArray(n, 42 + t);
                    MergeSort.sort(a, m);
                }
                case "quicksort" -> {
                    int[] a = randArray(n, 100 + t);
                    QuickSort.sort(a, m);
                }
                case "select" -> {
                    int[] a = randArray(n, 200 + t);
                    int val = SelectMoM5.select(a, k, m);
                    if (val == Integer.MIN_VALUE) System.out.print("");
                }
                case "closest" -> {
                    ClosestPair2D.Pt[] pts = randPts(n, 300 + t);
                    double d = ClosestPair2D.closest(pts, m);
                    if (d < 0) System.out.print("");
                }
                default -> throw new IllegalArgumentException("Unknown --algo");
            }
            long ms = (System.nanoTime() - start) / 1_000_000;
            w.writeRow(algo + "," + n + "," + ms + "," + m.maxDepth + "," + m.comparisons + "," + m.writes + "," + m.allocations);
        }
        w.close();
        System.out.println("Done → " + csv);
    }

    private static int[] randArray(int n, int seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }

    private static ClosestPair2D.Pt[] randPts(int n, int seed) {
        Random r = new Random(seed);
        ClosestPair2D.Pt[] a = new ClosestPair2D.Pt[n];
        for (int i = 0; i < n; i++) a[i] = new ClosestPair2D.Pt(r.nextDouble(), r.nextDouble());
        return a;
    }

    private static Map<String,String> parse(String[] args) {
        Map<String,String> m = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 < args.length && !args[i+1].startsWith("--")) m.put(args[i], args[i+1]);
                else m.put(args[i], "true");
            }
        }
        return m;
    }
}
