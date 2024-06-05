
package practice.leetcode;

public class S1351 {
    public static void main(String[] args) {
        S1351 obj = new S1351();
        obj.major();
    }

    private void major() {

        int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
//        int[][] grid = {{3, 2}, {1, 0}};

//        int result = solutionUsingLowerBoundBinarySearch(grid);
//        int result = solutionUsingTwoPointers(grid);
        int result = solutionUsingTwoPointersOptimized(grid);

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

    public int solutionUsingTwoPointers(int[][] grid) {
        /*
            Time Complexity:    O(m + n)
            Space Complexity:   O(1) - Constant Memory
            Approach:           Two Pointers - currentRow & currentCol
         */

        int negativeNumCount = 0;
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int row = 0;

        for (int currentCol = colCount - 1; currentCol >= 0; currentCol--) {

            for (int currentRow = row; currentRow < rowCount; currentRow++) {
                if (grid[currentRow][currentCol] >= 0) row++;
                else {
                    negativeNumCount += rowCount - currentRow;
                    break;
                }
            }

        }

        return negativeNumCount;

    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingTwoPointersOptimized(int[][] grid) {
        /*
            Time Complexity:    O(m + n)
            Space Complexity:   O(1) - Constant Memory
            Approach:           Two Pointers
         */

        int count = 0;
        int cols = grid[0].length;
        int row = grid.length - 1;
        int col = 0;

        while (row >= 0 && col < cols) {
            if (grid[row][col] < 0) {
                count += cols - col;
                row--;
            } else col++;
        }

        return count;
    }

    //  ----------------------------------------------------------------------------------------------------

}
