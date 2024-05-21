
package practice.leetcode;

public class S35 {
    public static void main(String[] args) {
        S35 obj = new S35();
        obj.major();
    }

    private void major() {

        int[] nums = {1, 3, 5, 7};
        int target = 3;

//        int result = solutionUsingUpperBoundBinarySearch(nums, target);
        int result = solutionUsingLowerBoundBinarySearch(nums, target);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingUpperBoundBinarySearch(int[] nums, int target) {
        /*
            Time Complexity:    O(log n) - Binary Search - Each iteration halves the search space
            Space Complexity:   O(1) - Constant Memory
            Approach:           Binary Search - Upper Bound
         */

        int left = 0;
        int right = nums.length - 1;

        //  Handle Edge Cases
        if (target > nums[right]) return right + 1;
        if (target < nums[left]) return left;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid;
                break;
            } else if (nums[mid] > target) right = mid;
            else left = mid + 1;
        }

        return left;

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingLowerBoundBinarySearch(int[] nums, int target) {
        /*
            Time Complexity:    O(log n) - Binary Search - Each iteration halves the search space
            Space Complexity:   O(1) - Constant Space
            Approach:           Binary Search - Lower Bound
         */

        int left = 0;
        int right = nums.length - 1;

        //  Handle Edge Cases
        if (target > nums[right]) return right + 1;
        if (target < nums[left]) return left;

        while (left <= right) {
            int mid = left + (right - left) / 2;    //  Prevent Integer Overflow

            if (nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }

        return left;

    }

    //  ----------------------------------------------------------------------------------------------------

}
