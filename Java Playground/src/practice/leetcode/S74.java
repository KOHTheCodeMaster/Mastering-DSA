package practice.leetcode;

public class S74 {
    public static void main(String[] args) {
        S74 obj = new S74();
        obj.major();
    }

    private void major() {

        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        boolean result = solutionUsingTwoPointers(matrix, target);
//        boolean result = solutionUsingBinarySearch(matrix, target);
//        boolean result = solutionUsingBinarySearch2Pass(matrix, target);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingBinarySearch(int[][] matrix, int target) {
        /*
            Time Complexity:    O(log(m * n)) - Binary search over a matrix of dimensions m x n
            Space Complexity:   O(1) - Constant space, as only a few variables are used
            Approach:           Using Binary Search
        */

        // Edge Case Check: If the target is out of bounds, return false.
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;

        boolean targetFound = false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = (rows * cols) - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;
            int midVal = matrix[mid / cols][mid % cols];

            if (midVal == target) {
                targetFound = true;
                break;
            } else if (midVal > target) right = mid - 1;
            else left = mid + 1;

        }

        return targetFound;
    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingBinarySearch2Pass(int[][] matrix, int target) {
        /*
            Time Complexity:    O(log(m) + log(n)) - Binary search over a matrix of dimensions m x n
            Space Complexity:   O(1) - Constant space, as only a few variables are used
            Approach:           Using Binary Search
        */

        // Check for Edge Cases: If the target is out of bounds, return false.
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
//        System.out.println("Left: " + left + " | Right: " + right + " | Row Index: " + rowIndex);

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

    private boolean solutionUsingTwoPointers(int[][] matrix, int target) {
        /*
            Time Complexity:    O(m + n) - The time complexity is determined by the dimensions of the matrix, m  rows and n columns.
            Space Complexity:   O(1) - Constant space complexity, as only a few variables are used regardless of the input size.
            Approach:           Using Two Pointers
        */

        boolean targetFound = false;

        int left = 0;                       // Start from top row
        int right = matrix[0].length - 1;   // Start from last column of the first row

        while (left < matrix.length && right >= 0) {

            if (matrix[left][right] == target) {
                targetFound = true;
                break;
            } else if (matrix[left][right] > target) right--;   // Move towards smaller elements
            else left++;                                        // Move towards larger elements

        }

        return targetFound;

    }

    //  ----------------------------------------------------------------------------------------------------

}
