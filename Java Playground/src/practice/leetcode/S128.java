package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class S128 {
    public static void main(String[] args) {
        S128 obj = new S128();
        obj.major();
    }

    private void major() {

        int[] nums = {100, 4, 200, 1, 3, 2};

        int result = solutionUsingHashMap(nums);
        System.out.println("Result: " + result);

    }
    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingHashMap(int[] nums) {
        /*
            Time Complexity:    O(n)
            Space Complexity:   O(n)
            Approach:           Using HashMap - Pre & Post Count Check
         */

        int result = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.merge(x, 1, Integer::sum);

        for (int key : nums) {
            if (map.get(key) == null) continue;

            int preCount = findPreSequenceCount(map, key, 0);
            int postCount = findPostSequenceCount(map, key + 1, 0);
//            System.out.println("Key: " + key + " | Pre-Count: " + preCount + " | Post-Count: " + postCount);

            result = Math.max(result, preCount + postCount);
        }

        return result;

    }

    private int findPreSequenceCount(Map<Integer, Integer> map, int key, int prevCount) {
        if (map.containsKey(key)) {
            map.put(key, null);
            prevCount = findPreSequenceCount(map, key - 1, prevCount) + 1;
        }
        return prevCount;
    }

    private int findPostSequenceCount(Map<Integer, Integer> map, int key, int nextCount) {
        if (map.containsKey(key)) {
            map.put(key, null);
            nextCount = findPostSequenceCount(map, key + 1, nextCount) + 1;
        }
        return nextCount;
    }

    //  ----------------------------------------------------------------------------------------------------

}
