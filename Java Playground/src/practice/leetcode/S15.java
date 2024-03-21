package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S15 {
    public static void main(String[] args) {
        S15 obj = new S15();
        obj.major();
    }

    private void major() {

//        String s = "A man, a plan, a canal: Panama";
//        int[] numbers = {2, 7, 11, 15};
//        int[] numbers = {2, 3, 4};
//        int[] numbers = {5, 25, 75};
//        int[] numbers = {3,24,50,79,88,150,345};
//        int[] nums = {12, 13, 23, 28, 43, 44, 59, 60, 61, 68, 70, 86, 88, 92, 124, 125, 136, 168, 173, 173, 180, 199, 212, 221, 227, 230, 277, 282, 306, 314, 316, 321, 325, 328, 336, 337, 363, 365, 368, 370, 370, 371, 375, 384, 387, 394, 400, 404, 414, 422, 422, 427, 430, 435, 457, 493, 506, 527, 531, 538, 541, 546, 568, 583, 585, 587, 650, 652, 677, 691, 730, 737, 740, 751, 755, 764, 778, 783, 785, 789, 794, 803, 809, 815, 847, 858, 863, 863, 874, 887, 896, 916, 920, 926, 927, 930, 933, 957, 981, 997};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-2, 0, 1, 1, 2};

//        List<List<Integer>> triplets = solutionUsingTwoPointers(nums);
        List<List<Integer>> triplets = solutionUsingThreePointers(nums);

        System.out.println("Triplets: " + triplets);

    }

    //  ----------------------------------------------------------------------------------------------------

    private List<List<Integer>> solutionUsingThreePointers(int[] nums) {
        /*
            Time Complexity:    O(n^2)
            Space Complexity:   O(1) - Constant Space - Excluding output triplets list
            Approach:           Using Two Pointers - Binary Search
         */

        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(nums);

        for (int leftIndex = 0; leftIndex < nums.length - 2; leftIndex++) {

            if (nums[leftIndex] > 0) break;
            if (leftIndex > 0 && nums[leftIndex] == nums[leftIndex - 1]) continue; // Skip duplicates for leftIndex

            int midIndex = leftIndex + 1;
            int rightIndex = nums.length - 1;

            while (midIndex < rightIndex) {

                int sum = nums[leftIndex] + nums[midIndex] + nums[rightIndex];

                if (sum < 0) midIndex++;
                else if (sum > 0) rightIndex--;
                else {

                    triplets.add(Arrays.asList(nums[leftIndex], nums[midIndex], nums[rightIndex]));

                    // Optimize by Skipping duplicates and adjust indices accordingly
                    while (midIndex < rightIndex && nums[midIndex] == nums[midIndex + 1]) midIndex++;
                    while (midIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) rightIndex--;

                    midIndex++;
                    rightIndex--;
                }
            }
        }

        return triplets;

    }

    @Deprecated
    private List<List<Integer>> solutionUsingTwoPointers(int[] nums) {
        /*
            Time Complexity:    O(n log(n)) - Linear time to scan through the input array `nums` & log n for Binary Search
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers - Binary Search
         */

        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(nums);

        /*
            nums = [-2  -1  -1  0  2 ]
                         R   L

            nums = [-2  0  1  1  2 ]
                        L        R
        */

        int left = 0;
        int right = 1;

        while (left <= right) {

            if (left == right) {
                right++;
                continue;
            }

            int target = -(nums[left] + nums[right]);
            int targetIndexAtRight = -1;
            int targetIndexAtMid = -1;
            int targetIndexAtLeft = -1;

            if (right != nums.length - 1) targetIndexAtRight = binarySearch(nums, right + 1, nums.length, target);
            targetIndexAtMid = binarySearch(nums, left + 1, right, target);
            targetIndexAtLeft = binarySearch(nums, 0, left, target);

            int targetIndex = targetIndexAtRight != -1 ? targetIndexAtRight
                    : targetIndexAtLeft != -1 ? targetIndexAtLeft
                    : targetIndexAtMid;

            if (targetIndex != -1) {

                //  Triplet Found
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[left]);
                triplet.add(nums[targetIndex]);
                triplet.add(nums[right]);
                triplets.add(triplet);

                left++;
            } else if (right != nums.length - 1) right++;
            else if (right == nums.length - 1) left++;

        }

        return triplets;

    }

    @Deprecated
    private List<List<Integer>> solutionUsingTwoPointers2(int[] nums) {
        /*
            Time Complexity:    O(n log(n)) - Linear time to scan through the input array `nums` & log n for Binary Search
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers - Binary Search
         */

        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(nums);

        /*
            nums = [-4  -1  -1  0  1  2 ]
                         L            R
                    X = 0 - (L + R)


            nums = [-2  0  1  1  2 ]
                     L           R

            nums = [-2  -1  -1  0  2 ]
                         R   L
        */

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int target = -(nums[left] + nums[right]);
            int targetIndex = binarySearch(nums, left + 1, right - 1, target);

            if (targetIndex != -1) {

                List<Integer> tempTriples = new ArrayList<>();
                tempTriples.add(nums[left]);
                tempTriples.add(nums[targetIndex]);
                tempTriples.add(nums[right]);
                triplets.add(tempTriples);

                right--;
            }

            left++;

        }

        return triplets;

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
