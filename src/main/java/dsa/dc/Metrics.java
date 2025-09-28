package dsa.dc;

public class Metrics {
    public long comparisons;
    public long writes;
    public long allocations;
    public int maxDepth;
    private int curDepth;

    public void cmp() { comparisons++; }
    public void write() { writes++; }
    public void alloc(long n) { allocations += n; }

    public void enter() { curDepth++; if (curDepth > maxDepth) maxDepth = curDepth; }
    public void leave() { curDepth--; }
}