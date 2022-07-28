package pers.ssp.P0100;

public class P0042TrappingRainWater {

//    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//
//
//    Example 1:
//
//
//    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//    Output: 6
//    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//    Example 2:
//
//    Input: height = [4,2,0,3,2,5]
//    Output: 9
//
//
//    Constraints:
//
//    n == height.length
//    1 <= n <= 2 * 104
//    0 <= height[i] <= 105
    public static void main(final String[] args) {
        final P0042TrappingRainWater ex = new P0042TrappingRainWater();
        final int[] height = new int[] { 4, 2, 0, 3, 2, 5 };// { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        final int result = ex.trap(height);
        System.out.println(result);

    }

    int calculateWater(final int[] height, final int prevRightHigh, final int rightHigh) {
        int water = 0;
        final int lowPoint = height[prevRightHigh] > height[rightHigh] ? height[rightHigh] : height[prevRightHigh];
        for (int i = prevRightHigh; i <= rightHigh; i++) {
            if (height[i] < lowPoint) {
                water += (lowPoint - height[i]);
            }
        }
        return water;
    }

    int findHighestPoint(final int[] height, final int index, final boolean toRight) {
        int highPoint = Integer.MIN_VALUE;
        int highPointIndex = -1;
        if (toRight && ((height.length - 1 - index) > 0)) {
            for (int i = index; i < height.length; i++) {
                if (highPoint <= height[i]) {
                    highPoint = height[i];
                    highPointIndex = i;
                }
            }
        }

        if (!toRight && ((height.length - 1) > index)) {
            for (int i = index; i >= 0; i--) {
                if (highPoint <= height[i]) {
                    highPoint = height[i];
                    highPointIndex = i;
                }
            }
        }
        return highPointIndex;
    }

    public int trap(final int[] height) {
        final int maxBarIndex = findHighestPoint(height, 0, true);
        int prevRightHigh = maxBarIndex;
        int prevLeftHigh = maxBarIndex;
        int waterTrapped = 0;

        int leftHigh = findHighestPoint(height, maxBarIndex - 1, false);
        while (leftHigh > -1) {
            waterTrapped = waterTrapped + calculateWater(height, leftHigh, prevLeftHigh);
            prevLeftHigh = leftHigh;
            leftHigh = findHighestPoint(height, prevLeftHigh - 1, false);
        }

        int rightHigh = findHighestPoint(height, maxBarIndex + 1, true);
        while (rightHigh > -1) {
            waterTrapped = waterTrapped + calculateWater(height, prevRightHigh, rightHigh);
            prevRightHigh = rightHigh;
            rightHigh = findHighestPoint(height, prevRightHigh + 1, true);
        }

        return waterTrapped;
    }
}
