package algorithmtoolkit;

/**
 * Utility class implementing linear and binary search.
 */
public class SearchUtil {

    /**
     * Linear Search
     * Time Complexity: O(n)
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                return i;
        return -1;
    }

    /**
     * Binary Search
     * Time Complexity: O(log n)
     * NOTE: Array must be sorted
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) return mid;
            else if (target < arr[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    // --------------------- TEST CASES -------------------------
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10};

        System.out.println("Linear Search 6 → " +
                linearSearch(arr, 6));

        System.out.println("Binary Search 8 → " +
                binarySearch(arr, 8));
    }
}
