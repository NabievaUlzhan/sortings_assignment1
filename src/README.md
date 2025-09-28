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

MergeSort follows a divide-and-conquer strategy where the input array is split into two halves, each of which is sorted recursively. The recurrence relation can be expressed as T(n)=2T(n/2)+O(n), where O(n) represents the time taken to merge the two sorted halves. This relation fits the Master Theorem, specifically Case 2, where a=2, b=2, and f(n)=O(n). By applying the theorem, we find that the solution is T(n)=Θ(nlogn). The logarithmic factor arises from the depth of recursion, while the linear factor comes from the merging process at each level.

QuickSort
T(n) = T(k) + T(n−k−1) + O(n)
→ Expected Θ(n log n) with randomized pivots, O(n²) worst case.

QuickSort also employs a divide-and-conquer approach, where a pivot element is chosen, and the array is partitioned into elements less than and greater than the pivot. The recurrence relation is given by T(n)=T(k)+T(n−k−1)+O(n), where k is the number of elements less than the pivot. The expected time complexity, with randomized pivots, is Θ(nlogn), as the average case partitions the input into two roughly equal halves. However, in the worst case (e.g., when the smallest or largest element is consistently chosen as the pivot), the time complexity degrades to O(n^2). This worst-case scenario can be analyzed using the Akra–Bazzi method, yielding insights into the algorithm's performance under different pivot selection strategies.

Deterministic Select (Median-of-Medians)
T(n) = T(n/5) + T(7n/10) + O(n)
→ Θ(n), guaranteed linear time.

The Median-of-Medians algorithm provides a deterministic way to select the k-th smallest element in linear time. The recurrence relation is defined as T(n)=T(n/5)+T(7n/10)+O(n). This relation arises because the algorithm partitions the input into groups of five, finding the median of each group in linear time, and then recursively selects the median of these medians. Applying the Master Theorem, we find that the algorithm runs in Θ(n) time, confirming its efficiency. The intuition here is that the selection of the median greatly reduces the size of the problem in each recursive call, ensuring that the depth of recursion remains shallow.

Closest Pair
T(n) = 2T(n/2) + O(n log n)
→ Θ(n log² n).

The Closest Pair algorithm utilizes a divide-and-conquer approach similar to MergeSort, where points are divided into two halves based on their x-coordinates. The recurrence relation can be expressed as 
T(n)=2T(n/2)+O(nlogn), where O(nlogn) accounts for the sorting of points and the merging process. This relation can be analyzed using the Master Theorem, leading to a complexity of Θ(nlog^2n). The additional logarithmic factor arises from the need to sort the points in both x and y directions, coupled with the merging step, which is required to find the closest pair across the dividing line.

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

### 1. Execution Time vs Input Size
   
_Small Range (n = 10…5000)_:

MergeSort and QuickSort exhibit efficient and predictable performance across this range. Both algorithms demonstrate a time complexity of Θ(nlogn), which is evident in their consistent scaling as the input size increases. The execution times increase logarithmically, reflecting the efficiency of their divide-and-conquer strategies.

Deterministic Select, while theoretically linear (Θ(n)), shows slower execution times compared to the sorting algorithms, especially as the input size grows. This is largely due to the overhead associated with its more complex pivot selection process, which involves additional comparisons and memory allocations.

Closest Pair of Points experiences significant overhead for small n. Although the algorithm operates with a complexity of 
Θ(nlog^2n), the constant factors involved in sorting and merging points lead to slower performance in practice. This overhead is particularly pronounced at smaller input sizes, where the algorithm's efficiency is overshadowed by these constants.

_Larger Range (n = 100…36000)_:

In this range, MergeSort continues to demonstrate superior performance, consistently outperforming QuickSort. Although both algorithms maintain their 
Θ(nlogn) complexity, MergeSort's predictable memory access patterns contribute to its efficiency, especially as input sizes become larger.

QuickSort, while competitive, shows more variability in execution time. This variability can be attributed to the choice of pivot elements; in some cases, poor pivot choices lead to uneven partitions, increasing the execution time significantly.

Deterministic Select continues to lag behind, as its linear complexity does not translate to competitive performance due to high constant factors. The complexity of grouping and selecting medians incurs additional time costs that overshadow its theoretical efficiency.

Closest Pair becomes markedly slower as input sizes increase, clearly emerging as the heaviest algorithm. The 
Θ(nlog^2n) complexity manifests in steep execution time increases, especially as the number of points grows. The algorithm's reliance on both sorting and recursive calls compounds its execution time, making it less suitable for larger datasets.

### 2. Depth vs Input Size
The recursion depth for MergeSort and QuickSort typically reaches 
log2(n), reflecting their divide-and-conquer nature. As input sizes increase, the depth remains logarithmic, indicating efficient problem-breaking strategies. The maximum depth for these algorithms rarely exceeds this logarithmic threshold, which is advantageous for stack usage.

Deterministic Select maintains a depth of O(logn) as it consistently recurses into the smaller half of the partitioned datasets. This ensures that the algorithm remains efficient in terms of stack space, even for larger inputs.

Closest Pair also exhibits a recursion depth of log2(n) due to its balanced divide-and-conquer approach. However, this depth can increase when considering additional factors, such as the depth of the merging process, which can lead to increased memory usage.

## Discussion of Constant-Factor Effects

_Cache Effects_: Both MergeSort and QuickSort benefit from optimized cache usage. MergeSort's sequential access pattern allows it to utilize cache effectively during the merging process, while QuickSort's random access can lead to cache misses but is mitigated by good pivot choices.

_Garbage Collection (GC)_: The memory allocation patterns of each algorithm influence the frequency and impact of garbage collection. For instance, MergeSort requires a significant amount of auxiliary memory for merging, which can lead to increased GC activity. Deterministic Select also incurs overhead due to frequent allocations for its median grouping. In contrast, QuickSort, being in-place, minimizes GC overhead, although its performance can fluctuate based on the state of memory and cache.

_Overhead Costs_: The constant factors associated with each algorithm play a crucial role in practical performance. While theoretical complexities provide a guideline for expected behavior, the actual execution times can vary significantly based on these constant factors, reinforcing the importance of implementation details in real-world applications.

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

<img width="585" height="236" alt="image" src="https://github.com/user-attachments/assets/0b892a58-1f10-4235-9f90-5a2c44639a54" />
<img width="1039" height="660" alt="image" src="https://github.com/user-attachments/assets/206b6707-2b8a-4090-985d-e11eb72631cc" />

<img width="523" height="246" alt="image" src="https://github.com/user-attachments/assets/d76338ba-01d1-4568-a354-74fc201b29ad" />
<img width="1116" height="704" alt="image" src="https://github.com/user-attachments/assets/819a8c3d-484c-4402-94a7-4d6a75b3ffdd" />


