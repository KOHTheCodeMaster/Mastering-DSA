package practice.leetcode;

import java.util.*;

public class S347 {
    public static void main(String[] args) {
        S347 obj = new S347();
        obj.major();
    }

    private void major() {

//        int[] nums = {1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums = {1, 7, 7, 7, 7, 7, 3, 3};
        int k = 2;

        int[] topKFrequenceNumsArr = topKFrequent(nums, k);
        System.out.println(Arrays.toString(topKFrequenceNumsArr));

    }

    public int[] topKFrequent(int[] nums, int k) {

        return solutionUsingHashMap(nums, k);

    }


    private int[] solutionUsingHashMap(int[] nums, int k) {
        /*
            Time Complexity:    O(n) - Linear scan through nums
            Space Complexity:   O(n) - Each element in nums has an entry in the frequency map
            Approach:           Using Frequency Map
        */

        // Result list to store the k most frequent numbers
        List<Integer> result = new ArrayList<>();

        // Generate a frequency map for each number in nums
        Map<Integer, Integer> frequencyMap = generateFreqMap(nums);

        // List to store numbers grouped by their frequency
        List<List<Integer>> numsByFreqList = new ArrayList<>();

        // Index represents frequency, and value is a list of numbers with the same frequency
        for (int i = 0; i < nums.length + 1; i++) numsByFreqList.add(null);

        // Populate numsByFreqList based on frequencyMap
        for (int x : frequencyMap.keySet()) {

            int xFreq = frequencyMap.get(x);
            List<Integer> numsWithXFreq = numsByFreqList.get(xFreq);

            if (numsWithXFreq == null) {
                numsWithXFreq = new ArrayList<>();
                numsWithXFreq.add(x);
                numsByFreqList.set(xFreq, numsWithXFreq);
            } else numsWithXFreq.add(x);

        }

        int maxFrequency = Collections.max(frequencyMap.values());

        // Collect the k most frequent numbers from numsByFreqList
        for (int i = maxFrequency; i > 0 && result.size() < k; i--)
            if (numsByFreqList.get(i) != null) result.addAll(numsByFreqList.get(i));

        // Convert the result list to an array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    private Map<Integer, Integer> generateFreqMap(int[] nums) {

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int x : nums) freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);

        return freqMap;

    }

}