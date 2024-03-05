package practice.leetcode;

import java.util.*;

public class S347 {
    public static void main(String[] args) {
        S347 obj = new S347();
        obj.major();
    }

    private void major() {

        int[] nums = {1, 1, 1, 2, 2, 3, 3, 4};
//        int[] nums = {1, 7, 7, 7, 7, 7, 3, 3};
        int k = 3;

        int[] topKFrequenceNumsArr = topKFrequent(nums, k);
        System.out.println(Arrays.toString(topKFrequenceNumsArr));

    }

    public int[] topKFrequent(int[] nums, int k) {

//        return solutionUsingHashMap(nums, k);

        return solutionUsingPriorityQueue(nums, k);

    }

    private int[] solutionUsingPriorityQueue(int[] nums, int k) {
        /*
            Time Complexity:    O(n * log(k))
            Space Complexity:   O(n * log(k))
            Approach:           Using Priority Queue with a custom comparator based on frequency
         */

        int[] resultArr = new int[k];

        //  Create a frequency map to store the count of each number in nums
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) freqMap.merge(x, 1, Integer::sum);

        //  Create a min heap (priority queue) based on the frequency of numbers
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            priorityQueue.add(entry);
            // If the size of the priority queue exceeds k, remove the element with the lowest frequency
            if (priorityQueue.size() > k) priorityQueue.poll();
        }

        // Populate the result array with the k most frequent elements in reverse order
        for (int i = k - 1; i >= 0; i--) resultArr[i] = Objects.requireNonNull(priorityQueue.poll()).getKey();

        return resultArr;

    }

    private void practiceCompareMethod(int[] nums, int k) {

/*
//        Comparator.comparing((Map.Entry<Integer, String> map) -> map.getValue());
        Comparator<Map.Entry<Integer, Integer>> entryComparator = Comparator.comparingInt((Map.Entry<Integer, Integer> map) -> map.getValue());
        entryComparator.compare(null, null);
//        Comparator<Map.Entry<Integer, Integer>> mapComparator = Comparator.comparingInt(map -> map.getValue());
        Comparator<Map.Entry<Integer, Integer>> mapComparator2 = Comparator.comparingInt(Map.Entry::getValue);
        Comparator<Map.Entry<Integer, Integer>> mapComparator3 = (o1, o2) -> o1.getValue() - o2.getValue();
        Comparator<Map.Entry<Integer, Integer>> mapComparator4 = (o1, o2) -> {
            return Comparator.comparingInt((Map.Entry<Integer, Integer> map) -> map.getValue());
        };
        Comparator<Map.Entry<Integer, Integer>> mapComparator5 = (o1, o2) -> {
            return Comparator.comparingInt(map -> map.getValue());
        };
        Comparator<Map.Entry<Integer, Integer>> mapComparator6 = (o1, o2) -> {
            return Comparator.comparingInt((map<Map.Entry<Integer, Integer>>) ->map.getValue());
        };
        Comparator<Map.Entry<Integer, Integer>> mapComparator7 = (o1, o2) -> {
            return Comparator.comparingInt(Map.Entry::getValue);
        };
*/

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