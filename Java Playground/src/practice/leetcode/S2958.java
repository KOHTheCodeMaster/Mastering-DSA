package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class S2958 {

    public static void main(String[] args) {
        S2958 obj = new S2958();
        obj.major();
    }

    private void major() {

        int[] nums = {1,4,4,3};
        int k = 1;

        int result = solutionUsingTwoPointers(nums, k);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------


    private int solutionUsingTwoPointers(int[] nums, int k) {
        /*
            Time Complexity:    O(n)
            Space Complexity:   O(n)
            Approach:           Using Two Pointers
         */

        int maxLength = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        if (nums.length == 1) return 1;

        for (int x : nums) {

            freqMap.merge(x, 1, Integer::sum);  //  Update Frequency Map

            if (freqMap.get(x) <= k) {
                maxLength = Math.max(maxLength, (right - left) + 1);
                right++;
            } else {

                while (freqMap.get(x) >= k) {
                    freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                    if (nums[left] == x) {
                        left++;
                        break;
                    }
                    left++;
                }

                right++;
            }

        }

        return maxLength;

    }

    //  ----------------------------------------------------------------------------------------------------

}
