package practice.leetcode;

import java.util.Arrays;

public class S167 {
    public static void main(String[] args) {
        S167 obj = new S167();
        obj.major();
    }

    private void major() {

//        String s = "A man, a plan, a canal: Panama";
//        int[] numbers = {2, 7, 11, 15};
//        int[] numbers = {2, 3, 4};
//        int[] numbers = {5, 25, 75};
//        int[] numbers = {3,24,50,79,88,150,345};
        int[] numbers = {12, 13, 23, 28, 43, 44, 59, 60, 61, 68, 70, 86, 88, 92, 124, 125, 136, 168, 173, 173, 180, 199, 212, 221, 227, 230, 277, 282, 306, 314, 316, 321, 325, 328, 336, 337, 363, 365, 368, 370, 370, 371, 375, 384, 387, 394, 400, 404, 414, 422, 422, 427, 430, 435, 457, 493, 506, 527, 531, 538, 541, 546, 568, 583, 585, 587, 650, 652, 677, 691, 730, 737, 740, 751, 755, 764, 778, 783, 785, 789, 794, 803, 809, 815, 847, 858, 863, 863, 874, 887, 896, 916, 920, 926, 927, 930, 933, 957, 981, 997};
        int target = 542;

//        int[] resultArr = solutionUsingTwoPointers(numbers, target);
//        int[] resultArr = solutionUsingTwoPointersWithFlag(numbers, target);
        int[] resultArr = solutionUsingBinarySearch(numbers, target);

        System.out.println("Result: " + Arrays.toString(resultArr));

    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionUsingTwoPointers(int[] numbers, int target) {
        /*
            Time Complexity:    O(n) - Linear time to scan through the input array `numbers`
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers - Sequential movement
         */

        int[] indices = new int[2];
        int leftPointer = 0;
        int rightPointer = 1;

        while (leftPointer < rightPointer) {

            int sum = numbers[leftPointer] + numbers[rightPointer];

            if (sum == target) {
                // Store the index of the left & right number (1-indexed)
                indices[0] = leftPointer + 1;
                indices[1] = rightPointer + 1;
                break;
            } else if (sum > target) rightPointer--; // Move the right pointer to the left to explore smaller numbers
            else leftPointer++; // Move the left pointer to explore smaller numbers
        }

        return indices;

    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionUsingTwoPointersWithFlag(int[] numbers, int target) {
        /*
            Time Complexity:    O(n) - Scan through the input array `numbers` twice (once for each pointer)
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers - Conditional movements with flag
         */

        int[] indices = new int[2];
        int leftPointer = 0;
        int rightPointer = 1;
        boolean moveRight = true;

        while (leftPointer < rightPointer) {

            int sum = numbers[leftPointer] + numbers[rightPointer];

            if (sum == target) {
                // Store the index of the left & right number (1-indexed)
                indices[0] = leftPointer + 1;
                indices[1] = rightPointer + 1;
                break;
            } else if (sum > target) {
                moveRight = false;  // Set the flag as false to prevent moving the right pointer
                rightPointer--; // Move the right pointer to the left to explore smaller numbers
            } else {
                // Move the right pointer to explore larger numbers if needed
                if (moveRight && rightPointer != numbers.length - 1) rightPointer++;
                else leftPointer++; // Move the left pointer to explore smaller numbers
            }
        }

        return indices;

    }

    //  ----------------------------------------------------------------------------------------------------

    public int[] solutionUsingBinarySearch(int[] numbers, int target) {
        /*
            Time:       O(n log(n))
            Space:      O(1)
            Approach:   Using Binary Search
        */

        int[] resultIndices = new int[2];

        for (int i = 0; i < numbers.length; i++) {

            int num1 = numbers[i];
            int num2 = target - num1;

            int startIndex = i + 1;
            int endIndex = numbers.length - 1;

            int index2 = binarySearch(numbers, startIndex, endIndex, num2);

            if (index2 != -1) {
                resultIndices[0] = i + 1;
                resultIndices[1] = index2 + 1;
                break;
            }

        }

        return resultIndices;

    }

    private int binarySearch(int[] arr, int startIndex, int endIndex, int target) {

        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) endIndex = mid - 1;
            else startIndex = mid + 1;
        }

        return -1;

    }

}
