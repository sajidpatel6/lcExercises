package pers.ssp.P0100;

public class P0001TwoSum {

    public static void main(final String[] args) {
        final P0001TwoSum ex = new P0001TwoSum();
        final int[] nums = new int[] { 2, 7, 11, 15 };

        final int[] result = ex.twoSum(nums, 13);
        for (final int i : result) {
            System.out.println(i + " ");
        }

    }

    public int[] twoSum(final int[] nums, final int target) {
        final int result[] = new int[2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean testMinMax = true;
        for (int i = 0; i < (nums.length - 1); i++) {
            final int temp = target - nums[i];
            if (!testMinMax && !((min <= temp) && (max >= temp))) {
                continue;
            }
            for (int j = (nums.length - 1); j > i; j--) {
                if (testMinMax && (min > nums[j])) {
                    min = nums[j];
                }
                if (testMinMax && (max < nums[j])) {
                    max = nums[j];
                }
                if (temp == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
            testMinMax = false;
        }

        return result;
    }
}
