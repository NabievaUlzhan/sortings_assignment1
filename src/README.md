# **Report**

## **Overview**

This report analyzes the performance of four algorithms — MergeSort, QuickSort, Deterministic Select (Median-of-Medians), and Closest Pair of Points — across varying input sizes.
Two sets of experiments were conducted:
Small inputs: n = 10, 50, 100, 500, 1000, 2000, 5000
Larger inputs: n = 100, 500, 1000, 2000, 5000, 12000, 24000, 36000
The benchmarks measure execution time, comparisons, writes, allocations, and recursion depth.

## Architecture Notes

### Recursion Depth Control

MergeSort: Recursion depth is log₂(n), controlled by halving at each step.

QuickSort: Average depth is log₂(n) with randomized pivot; worst case O(n) if pivots are bad.

Deterministic Select: Always recurses only into the smaller side, guaranteeing O(log n) depth.

Closest Pair: Balanced divide-and-conquer recursion with log₂(n) levels.

### Memory Allocation

MergeSort: Requires O(n) auxiliary buffer for merging.

QuickSort: In-place, O(log n) stack space.

Deterministic Select: In-place, O(log n) stack space, but high overhead due to median-of-medians grouping.

Closest Pair: O(n) memory for auxiliary arrays when sorting and merging points.

## Recurrence Analysis

MergeSort
T(n) = 2T(n/2) + O(n)
→ Master Theorem Case 2 → Θ(n log n).

QuickSort
T(n) = T(k) + T(n−k−1) + O(n)
→ Expected Θ(n log n) with randomized pivots, O(n²) worst case.

Deterministic Select (Median-of-Medians)
T(n) = T(n/5) + T(7n/10) + O(n)
→ Θ(n), guaranteed linear time.

Closest Pair
T(n) = 2T(n/2) + O(n log n)
→ Θ(n log² n).

## **Experimental Results**

### Small Sizes (n = 10…5000)

MergeSort and QuickSort follow Θ(n log n) scaling.

Deterministic Select grows linearly, but overhead makes it slower than sorts at these input sizes.

Closest Pair is dominated by constant factors and is slower than expected for small n.

### Larger Sizes (n = 100…36000)

MergeSort consistently shows the best performance.

QuickSort is close to MergeSort but with more variability.

Deterministic Select scales linearly but remains slower in practice due to high constant factors.

Closest Pair grows steeply (Θ(n log² n)), becoming the slowest algorithm on large inputs.

## Performance Plots

### 1) Execution Time vs Input Size (Small range: 10…5000)

MergeSort and QuickSort are efficient and predictable.
Deterministic Select is slower despite linear complexity.
Closest Pair has high overhead for small n.

### 2) Execution Time vs Input Size (Larger range: 100…36000)

MergeSort and QuickSort remain practical winners. 
Deterministic Select is still behind due to constants.
Closest Pair becomes clearly the heaviest algorithm.

## Constant-Factor Effects

_Cache effects:_ MergeSort benefits from sequential access, QuickSort from good pivots.

_Overhead:_ Deterministic Select pays for its careful pivoting with high constants.

_Memory pressure:_ MergeSort’s buffer increases allocation costs; Closest Pair requires repeated auxiliary arrays.

## Theory vs Practice

_MergeSort:_ Matches Θ(n log n) both theoretically and experimentally.

_QuickSort:_ Matches Θ(n log n) on average, with occasional variability.

_Deterministic Select:_ Theoretically Θ(n), but slower in practice for tested input sizes.

_Closest Pair:_ Growth matches Θ(n log² n), but overhead dominates at small and medium n.

## Conclusion

For sorting, MergeSort and QuickSort are the most efficient in practice.

Deterministic Select validates its linear complexity but is not competitive at tested scales.

Closest Pair is asymptotically efficient compared to O(n²), but constants make it impractical for small and medium inputs.

The experiments confirm theoretical complexity but emphasize the role of constant factors, cache behavior, and practical implementation details in real performance.

<img width="585" height="236" alt="image" src="https://github.com/user-attachments/assets/56400bef-ae57-4f1f-b3dc-f9c1a8869219" />
<img width="1039" height="660" alt="image" src="https://github.com/user-attachments/assets/2a2b9e1e-13ea-420a-8bcd-6df1613bb917" />

<img width="523" height="246" alt="image" src="https://github.com/user-attachments/assets/71d76422-c530-4f74-ab10-f6b4034d58bb" />
<img width="1116" height="704" alt="image" src="https://github.com/user-attachments/assets/00ee670f-e758-4d56-843f-b125348165dc" />



