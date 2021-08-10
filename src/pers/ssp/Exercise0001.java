package pers.ssp;

public class Exercise0001 {

    public static void main(final String[] args) {
        final Exercise0001 ex = new Exercise0001();
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
