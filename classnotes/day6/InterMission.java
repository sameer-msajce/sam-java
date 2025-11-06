import java.util.*;
import java.util.stream.*;

public class InterMission {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

        return Arrays.stream(nums2)
                     .distinct()
                     .filter(set1::contains)
                     .toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        int[] ans = intersection(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }
}
