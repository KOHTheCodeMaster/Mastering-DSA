package practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class S128 {
    public static void main(String[] args) {
        S128 obj = new S128();
        obj.major();
    }

    private void major() {

        int[] nums = {100, 4, 200, 1, 3, 2};

        int result = solutionUsingHashMap(nums);
//        int result = solutionUsingHashSet(nums);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingHashSet(int[] nums) {
        /*
            Time Complexity:    O(n) - iterates through the input array `nums` twice
            Space Complexity:   O(n) - uses HashSet to store the unique elements from nums
            Approach:           Using HashSet
         */

        int maxSequenceLength = 1;

        //  Handle Edge Case
        if (nums.length == 0) return 0;

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);

        for (int num : nums) {
            //  Skip If num is not the Start of Sequence
            if (numSet.contains(num - 1)) continue;

            int currentSequenceLength = 1;
            while (numSet.contains(++num)) currentSequenceLength++;

            maxSequenceLength = Math.max(currentSequenceLength, maxSequenceLength);

            //  Stop iterating once maxSequenceLength is greater than half of the nums length
            if (maxSequenceLength > nums.length / 2) break;
        }

        return maxSequenceLength;
    }

    //  ----------------------------------------------------------------------------------------------------

    public int solutionUsingHashMap(int[] nums) {
        /*
            Time Complexity:    O(n) - iterates through the input array `nums` once to populate the HashMap,
                                and each key in the HashMap is processed once to calculate the sequence lengths
            Space Complexity:   O(n) - uses HashMap potentially storing all unique elements from the input array `nums`
            Approach:           Using HashMap - Pre & Post Count Check
         */

        int maxSequenceLength = -1;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) frequencyMap.merge(num, 1, Integer::sum);

        for (int key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) == null) continue;

            int leftSequenceLength = findSequenceLength(frequencyMap, key, -1); //  Inclusive of current key
            int rightSequenceLength = findSequenceLength(frequencyMap, key + 1, 1);

//            int leftSequenceLength = findLeftSequenceLength(frequencyMap, key, 0);
//            int rightSequenceLength = findRightSequenceCount(frequencyMap, key + 1, 0);
//            System.out.println("Key: " + key + " | Pre-Count: " + leftSequenceLength + " | Post-Count: " + rightSequenceLength);

            maxSequenceLength = Math.max(maxSequenceLength, leftSequenceLength + rightSequenceLength);
        }

        return maxSequenceLength;

    }

    private int findSequenceLength(Map<Integer, Integer> frequencyMap, int key, int direction) {

        int sequenceLength = 0;

        while (frequencyMap.containsKey(key)) {
            frequencyMap.put(key, null);
            sequenceLength++;
            key += direction;
        }

        return sequenceLength;
    }

    @Deprecated
    private int findLeftSequenceLength(Map<Integer, Integer> map, int key, int prevCount) {
        if (map.containsKey(key)) {
            map.put(key, null);
            prevCount = findLeftSequenceLength(map, key - 1, prevCount) + 1;
        }
        return prevCount;
    }

    @Deprecated
    private int findRightSequenceCount(Map<Integer, Integer> map, int key, int nextCount) {
        if (map.containsKey(key)) {
            map.put(key, null);
            nextCount = findRightSequenceCount(map, key + 1, nextCount) + 1;
        }
        return nextCount;
    }

    //  ----------------------------------------------------------------------------------------------------

}
