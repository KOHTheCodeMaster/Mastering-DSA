package practice.unsorted;

import java.util.*;

public class Q15 {

    public static void main(String[] args) {

        Q15 obj = new Q15();
        obj.major();

    }

    private void major() {

        leetCodeQ15();

    }

    private void leetCodeQ15() {

        int[][] numsAll = {
                {-2, -1, -1, 0, 1, 1, 2},
                {-1, 0, 1, 2, -1, -4},
                {-4, -1, -1, 0, 1, 2},
//                {-8, -7, 2, 4, 4, 5, 7, 11},
                {1, 1, -2},
                {0, 0, 0},
                {0, 0, 0, 0},
                {1, 2, -2, -1},
                {-2, 0, 1, 1, 2}

        };

        for (int[] nums : numsAll) {
            List<List<Integer>> list = solution1(nums);
            System.out.println("\nNums: " + Arrays.toString(nums) + "\nResult: " + list);
        }

    }

    public List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }


    private List<List<Integer>> solution1(int[] nums) {
        /*
            -1 0 1 2 -1 -4
            -4 -1 -1 0 1 2

            Fails for below cases, requires O(n^2)
            -2, -1, -1, 0, 1, 1, 2
        */


        List<List<Integer>> resultList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        Arrays.sort(nums);

        while (leftIndex < rightIndex - 1) {

            int targetNum = -(nums[leftIndex] + nums[rightIndex]);

            if (targetNum < nums[leftIndex + 1]) rightIndex--;
            else if (targetNum > nums[rightIndex - 1]) leftIndex++;
            else if (targetNum >= nums[leftIndex + 1] && targetNum <= nums[rightIndex - 1]) {

                int targetIndex = Arrays.binarySearch(nums, leftIndex + 1, rightIndex, targetNum);
                if (targetIndex > 0) {

                    resultList.add(Arrays.asList(nums[leftIndex], nums[targetIndex], nums[rightIndex]));

                    // Check if next elements are duplicates and adjust indices accordingly
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) leftIndex++;
                    while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) rightIndex--;

//                    leftIndex++;
                    rightIndex--;

                } else {
                    leftIndex++;
//                    rightIndex--;
                }
            }
        }

        return resultList;

    }

    private int getTargetFrequency(int[] nums, int leftIndex, int rightIndex, int targetNum) {

        int freq = 0;
        for (int i = leftIndex; i <= rightIndex; i++) freq += nums[i] == targetNum ? 1 : 0;

        return freq;
    }


}
