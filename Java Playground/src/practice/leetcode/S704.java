
package practice.leetcode;

public class S704 {

    public static void main(String[] args) {
        S704 obj = new S704();
        obj.major();
    }

    private void major() {

        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        int result = solutionUsingBinarySearch(nums, target);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingBinarySearch(int[] nums, int target) {
        /*
            Time Complexity:    O(log n) - Binary Search - Each iteration halves the search space
            Space Complexity:   O(1) - Constant Space - Excluding output triplets list
            Approach:           Using Binary Search
         */

        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

//            int mid = (left + right) / 2;
            int mid = left + (right - left) / 2;    //  Prevent Integer Overflow

            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;

        }

        return index;

    }

    //  ----------------------------------------------------------------------------------------------------

}
