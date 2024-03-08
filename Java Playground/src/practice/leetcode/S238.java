package practice.leetcode;

import java.util.*;

public class S238 {
    public static void main(String[] args) {
        S238 obj = new S238();
        obj.major();
    }

    private void major() {

//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] nums = {1, 2, 3, 4, 5, 6};
//        int[] nums = {1, 2};
//        int[] nums = {1, 7, 7, 7, 7, 7, 3, 3};

        /*
            nums        = { 1,  2,  3,  4,   5,   6,     7,      8,        9,        10};
            productArr  = { 1,  2,  6, 24, 120, 720, 5_040, 40_320, 3_62_880, 36_28_800};


            n = 7
            nums        = { 1,  2,  3,  4,   5,   6,    7};
            productArr  = { 1,  2,  6, 24, 120, 720, 5040};

            nums        = {    1,    2,    3,    4,    5,   6,   7};
            resultArr   = { 5040, 2520, 1680, 1260, 1008, 840, 720};


            n = 6
            nums        = {    1,    2,    3,    4,    5,   6};
            rA          = {  720,  360,  240,  180,  144, 120};  //  Temp = 120

            nums        = {    1,    2,    3,    4,    5,   6};
            productArr  = {    1,    2,    6,   24,  120, 720};
            resultArr   = {  720,  360,  240,  180,  144  120};


            n = 5
            nums        = { 1,  2,  3,  4,   5};
            productArr  = { 1,  2,  6, 24, 120};

            nums        = {    1,   2,   3,   4,   5};
            resultArr   = {  120,  60,  40,  30,  24};
            rA          = {  120,  60,  40,  30,  24};  //  Temp = 24


            n = 4
            nums        = { 1,  2,  3,  4};
            productArr  = { 1,  2,  6, 24};
            leftArr     = { 1,  1,  2,  6};         //  Products of all Left Elements
            rightArr    = {24, 12,  4,  1};         //  Products of all Right Elements
            rightArr    = { 4, 12,  8,  1};         //  Products of all Right Elements

            nums        = {   1,   2,  3,  4};
            resultArr   = {  24,  12,  8,  6};

         */

        int[] arrProductExceptSelf = productExceptSelf(nums);
        System.out.println(Arrays.toString(arrProductExceptSelf));

    }

    private int[] productExceptSelf(int[] nums) {

//        return solutionTwoPointers(nums);

//        return solutionUsingTwoPointersTwoPass(nums);

        return solutionUsingMathLogarithmicFunction(nums);  //  ToDo: Understand Log Approach Deeply!

    }

    private int[] solutionUsingMathLogarithmicFunction(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        double logSum = 0;

        // Calculate total logarithmic sum
        for (int num : nums) logSum += Math.log(num);

        // Update each element by subtracting its logarithmic value
        for (int i = 0; i < n; i++) result[i] = (int) Math.round(Math.exp(logSum - Math.log(nums[i])));

        return result;
    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionUsingTwoPointersTwoPass(int[] nums) {
        /*
            Time Complexity:    O(n) - Linear Time to Scan nums - Iterating nums twice
            Space Complexity:   O(1) - No Extra Space used for computation (output array does not count)
            Approach:           Using Two Pointers
         */

        int[] arrProductExceptSelf = new int[nums.length];
        Arrays.fill(arrProductExceptSelf, 1);

        int runningProduct = 1;   // Temporary variable to store the running product value during traversal

        // First pass: Calculate products to the left of each element
        for (int i = 0; i < nums.length; i++) {
            arrProductExceptSelf[i] = runningProduct;   // Update product value with the running product to the left
            runningProduct *= nums[i];
        }

        runningProduct = 1; // Reset the running product value for the second pass

        // Second pass: Calculate products to the right of each element and update the final result
        for (int i = nums.length - 1; i >= 0; i--) {
            arrProductExceptSelf[i] *= runningProduct;    // Multiply with the running product to the right
            runningProduct *= nums[i];
        }

        return arrProductExceptSelf;
    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionTwoPointers(int[] nums) {
        /*
            Time Complexity:    O(n) - Linear Time to Scan nums
            Space Complexity:   O(1) - No Extra Space used for computation (output array does not count)
            Approach:           Using Two Pointers
         */

        // Initialize the output array to store the product of elements except self
        int[] arrProductExceptSelf = new int[nums.length];

        // Flag to indicate when the left and right pointers cross each other
        boolean pointersCrossed = false;
        int runningProduct = 1; // Temporary variable to store the running product value during traversal

        // Iterate through nums using two pointers, moving towards each other
        for (int left = 0, right = nums.length - 1; left < nums.length; left++, right--) {

            // Handle case when the pointers cross each other
            if (!pointersCrossed && left > right) {
                //  When Left & Right Cross Each Other
                pointersCrossed = true;

                runningProduct = nums[left] * nums[right];
                continue;
            } else if (!pointersCrossed && left == right) {
                // Handle case when left == right, representing the middle element
                pointersCrossed = true;

                // Populate result for middle element with runningProduct
                arrProductExceptSelf[left] = runningProduct;

                runningProduct = nums[left];
                continue;
            }

            // Calculate and update product values based on the pointers' positions
            if (!pointersCrossed) {
                arrProductExceptSelf[left] = nums[right] * runningProduct;
                arrProductExceptSelf[right] = nums[left] * runningProduct;

                runningProduct *= nums[left] * nums[right];
            } else {
                arrProductExceptSelf[left] *= runningProduct;
                arrProductExceptSelf[right] *= runningProduct;

                runningProduct *= nums[left] * nums[right];
            }

        }

        return arrProductExceptSelf;

    }

    //  ----------------------------------------------------------------------------------------------------

}