
package practice.leetcode;

public class S11 {
    public static void main(String[] args) {
        S11 obj = new S11();
        obj.major();
    }

    private void major() {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int result = solutionUsingTwoPointer(height);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingTwoPointer(int[] height) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input array `height`
            Space Complexity:   O(1) - Constant Space - Excluding output triplets list
            Approach:           Using Two Pointers
         */

        int result = 0;

        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while (leftPointer < rightPointer) {
            result = Math.max(findWaterCount(height, leftPointer, rightPointer), result);

            if (height[leftPointer] <= height[rightPointer]) leftPointer++;
            else rightPointer--;
        }

        return result;

    }

    private int findWaterCount(int[] height, int leftPos, int rightPos) {

        int tempPos = rightPos - leftPos;
        int minHeight = Math.min(height[leftPos], height[rightPos]);
        return tempPos * minHeight;

    }

}
