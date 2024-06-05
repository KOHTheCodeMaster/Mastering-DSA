
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
        System.out.println("Binary Search -> Result: " + result);

        result = solutionUsingTwoPointersTopRight(grid);
        System.out.println("Two Pointers (Top-right) -> Result: " + result);

        result = solutionUsingTwoPointersTopRight2(grid);
        System.out.println("Two Pointers (Top-right) via for loops -> Result: " + result);

        result = solutionUsingTwoPointersBottomLeft(grid);
        System.out.println("Two Pointers (Bottom-left) -> Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingLowerBoundBinarySearch(int[][] grid) {
        /*
            Time Complexity:    O(n * (log m)) - Binary Search - O(log m) for each row.
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

    public int solutionUsingTwoPointersTopRight(int[][] grid) {
        /*
            Time Complexity:    O(m + n)
            Space Complexity:   O(1) - Constant Memory
            Approach:           Two Pointers - Starting from Top-Right element
         */

        int negativeNumCount = 0;
        int rowCount = grid.length;
        int row = 0;
        int col = grid[0].length - 1;

        while (row < rowCount && col >= 0) {
            if (grid[row][col] >= 0) row++;
            else {
                negativeNumCount += rowCount - row; //  All the elements in current column are negative
                col--;
            }
        }

        return negativeNumCount;

    }

    //  ----------------------------------------------------------------------------------------------------

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public int solutionUsingTwoPointersTopRight2(int[][] grid) {
        /*
            Time Complexity:    O(m + n) - Process each row and column at most once
            Space Complexity:   O(1) - Constant Memory
            Approach:           Two Pointers - Unnecessary 2 for loops - Starting from Top-Right element
         */

        int negativeNumCount = 0;
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int row = 0;

        // Iterate through each column from right to left
        for (int currentCol = colCount - 1; currentCol >= 0; currentCol--) {

            // Iterate through each row starting from the currentRow
            for (int currentRow = row; currentRow < rowCount; currentRow++) {
                if (grid[currentRow][currentCol] >= 0) row++;    // Move to the next row if non-negative
                else {
                    negativeNumCount += rowCount - currentRow;  // All rows below the current row in this column are negative
                    break;  // Move to the next column
                }
            }
        }

        return negativeNumCount;

    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingTwoPointersBottomLeft(int[][] grid) {
        /*
            Time Complexity:    O(m + n)
            Space Complexity:   O(1) - Constant Memory
            Approach:           Two Pointers - Starting from Bottom-Left element
         */

        int negativeNumCount = 0;
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int row = rowCount - 1;
        int col = 0;

        while (row >= 0 && col < colCount) {
            if (grid[row][col] >= 0) col++;
            else {
                negativeNumCount += colCount - col; //  All the elements in current column are negative
                row--;
            }
        }

        return negativeNumCount;

    }

    //  ----------------------------------------------------------------------------------------------------

}
