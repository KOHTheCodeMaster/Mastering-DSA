
package practice.leetcode;

public class S42 {
    public static void main(String[] args) {
        S42 obj = new S42();
        obj.major();
    }

    private void major() {

        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {4, 2, 0, 3, 2, 5};

        int result = solutionUsingTwoPointerWithConstantSpace(height);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingTwoPointerWithConstantSpace(int[] height) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input array `height`
            Space Complexity:   O(1) - Constant Space - Excluding output triplets list
            Approach:           Using Two Pointers
         */

        int result = 0;
        int leftPointer = 0;
        int leftMaxHeight = 0;
        int rightPointer = height.length - 1;
        int rightMaxHeight = 0;

        while (leftPointer < rightPointer) {

            leftMaxHeight = Math.max(height[leftPointer], leftMaxHeight);
            rightMaxHeight = Math.max(height[rightPointer], rightMaxHeight);

            if (leftMaxHeight <= rightMaxHeight) {
                result += leftMaxHeight - height[leftPointer];
                leftPointer++;
            } else {
                result += rightMaxHeight - height[rightPointer];
                rightPointer--;
            }

        }

        return result;

    }


}
