package pers.ssp;

public class Exercise0012 {

//    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//            The overall run time complexity should be O(log (m+n)).
//
//
//
//            Example 1:
//
//            Input: nums1 = [1,3], nums2 = [2]
//            Output: 2.00000
//            Explanation: merged array = [1,2,3] and median is 2.
//            Example 2:
//
//            Input: nums1 = [1,2], nums2 = [3,4]
//            Output: 2.50000
//            Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//            Example 3:
//
//            Input: nums1 = [0,0], nums2 = [0,0]
//            Output: 0.00000
//            Example 4:
//
//            Input: nums1 = [], nums2 = [1]
//            Output: 1.00000
//            Example 5:
//
//            Input: nums1 = [2], nums2 = []
//            Output: 2.00000
//
//
//            Constraints:
//
//            nums1.length == m
//            nums2.length == n
//            0 <= m <= 1000
//            0 <= n <= 1000
//            1 <= m + n <= 2000
//            -106 <= nums1[i], nums2[i] <= 106

    public static void main(final String[] args) {
        final Exercise0012 ex = new Exercise0012();
        final int[] nums1 = {};
        final int[] nums2 = { 1, 2, 3, 4 };
        final double result = ex.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        final int len1 = nums1.length;
        final int len2 = nums2.length;
        final int medianIndex = (((len1 + len2) / 2));
        final boolean evenCount = ((len1 + len2) % 2) == 0;

        int counter1 = 0;
        int counter2 = 0;

        int counter = 0;
        double median = 0;
        boolean onlyOne = false;
        boolean onlyTwo = false;
        while (((counter <= medianIndex) && !evenCount) || ((counter < medianIndex) && evenCount)) {
            if (counter2 >= len2) {
                onlyOne = true;
            }
            if (counter1 >= len1) {
                onlyTwo = true;
            }
            if (onlyOne) {
                median = nums1[counter1];
                counter1++;
            } else if (onlyTwo) {
                median = nums2[counter2];
                counter2++;
            } else if ((counter1 < len1) && (counter2 < len2) && (nums1[counter1] < nums2[counter2])) {
                median = nums1[counter1];
                counter1++;
            } else if (counter2 < len2) {
                median = nums2[counter2];
                counter2++;
            }

            if (evenCount) {
                if (counter2 >= len2) {
                    onlyOne = true;
                }
                if (counter1 >= len1) {
                    onlyTwo = true;
                }
                if (onlyOne) {
                    median += nums1[counter1];
                } else if (onlyTwo) {
                    median += nums2[counter2];
                } else if ((counter1 < len1) && (counter2 < len2) && (nums1[counter1] < nums2[counter2])) {
                    median += nums1[counter1];
                } else if (counter2 < len2) {
                    median += nums2[counter2];
                }
                median /= 2;
            }
            counter++;
        }

        return median;
    }
}