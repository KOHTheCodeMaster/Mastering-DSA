
package practice.leetcode;

public class S1351 {
    public static void main(String[] args) {
        S1351 obj = new S1351();
        obj.major();
    }

    private void major() {

        int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
//        int[][] grid = {{3, 2}, {1, 0}};

        int result = solutionUsingLowerBoundBinarySearch(grid);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingLowerBoundBinarySearch(int[][] grid) {
        /*
            Time Complexity:    n * O(log m) - Binary Search - O(log m) for each row.
            Space Complexity:   O(1) - Constant Memory
            Approach:           Binary Search - Upper Bound
         */

        int target = -1;
        int negativeNumCount = 0;

        for (int[] row : grid) {
            int left = 0;
            int right = row.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (row[mid] > target) left = mid + 1;
                else right = mid - 1;
            }

            negativeNumCount += row.length - left;
        }

        return negativeNumCount;

    }

    //  ----------------------------------------------------------------------------------------------------

}
