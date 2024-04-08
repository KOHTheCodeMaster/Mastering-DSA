package practice.leetcode;

public class S74 {
    public static void main(String[] args) {
        S74 obj = new S74();
        obj.major();
    }

    private void major() {

        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        boolean result = solutionUsingBinarySearch(matrix, target);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingBinarySearch(int[][] matrix, int target) {
        /*
            Time Complexity:    O(log(m) + log(n))
            Space Complexity:   O(n)
            Approach:           Using Binary Search
         */

        //  Handle Edge Cases Efficiently:
        //  if the target is less than the first element of the matrix or greater than the last element,
        //  it's guaranteed that the target doesn't exist in the matrix.
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;

        boolean targetFound;

        int rowIndex = binarySearch2D(matrix, target);
        int columnIndex = binarySearch(matrix[rowIndex], target);

        targetFound = columnIndex != -1;

        return targetFound;
    }

    private int binarySearch2D(int[][] matrix, int target) {

        int rowIndex = -1;
        int left = 0;
        int right = matrix.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (matrix[mid][0] == target) {
                rowIndex = mid;
                break;
            } else if (matrix[mid][0] > target) right = mid - 1;
            else left = mid + 1;
        }

        if (rowIndex == -1) rowIndex = right;
        System.out.println("Left: " + left + " | Right: " + right + " | Row Index: " + rowIndex);

        return rowIndex;

    }

    private int binarySearch(int[] nums, int target) {

        int targetIndex = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                targetIndex = mid;
                break;
            } else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }

        return targetIndex;

    }

    //  ----------------------------------------------------------------------------------------------------

}
