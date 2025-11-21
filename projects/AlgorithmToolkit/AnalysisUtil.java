package algorithmtoolkit;

/**
 * Utility class for timing algorithms using lambda functions.
 */
public class AnalysisUtil {

    /**
     * Benchmarks any algorithm by measuring execution time.
     *
     * @param task Algorithm to execute
     * @return execution time in milliseconds
     */
    public static long measure(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000;  // convert to ms
    }

    // ---------------- TEST CASE -------------------------
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 1};

        long time = measure(() -> SortingUtil.quickSort(arr));
        System.out.println("QuickSort Time: " + time + " ms");
    }
}
