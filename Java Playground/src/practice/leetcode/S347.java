package practice.leetcode;

import java.util.*;

public class S347 {
    public static void main(String[] args) {
        S347 obj = new S347();
        obj.major();
    }

    private void major() {

        int[] nums = {1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4};
//        int[] nums = {1, 2};
//        int[] nums = {1, 7, 7, 7, 7, 7, 3, 3};
        int k = 2;

        int[] topKFrequenceNumsArr = topKFrequent(nums, k);
        System.out.println(Arrays.toString(topKFrequenceNumsArr));

    }

    public int[] topKFrequent(int[] nums, int k) {

//        return solutionUsingHashMap(nums, k);

//        return solutionUsingPriorityQueue(nums, k);

        return solutionUsingHoarePartitionAlgorithm(nums, k);

    }

    //  ----------------------------------------------------------------------------------------------------

    /**
     * Finds the top k elements in an array using the Hoare Partition Algorithm.
     * <p>
     * Time Complexity:    O(n log n) - [CONFLICT] - LeetCode Editorial says O(n) but In the worst case, it may require multiple passes
     * Space Complexity:   O(n) - Each element in nums has an entry in the frequency map
     * Approach:           Using Hoare Partition Algorithm
     *
     * @param nums The input array.
     * @param k    The number of top elements to find.
     * @return An array containing the top k elements.
     */
    private int[] solutionUsingHoarePartitionAlgorithm(int[] nums, int k) {

        // Count the frequency of each element in nums and store in freqMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) freqMap.merge(x, 1, Integer::sum);

        // Get unique keys from freqMap
        int[] keys = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        int topKSeparatorIndex = keys.length - k - 1;   // Index of (n - k)th least frequent element (0-based)

        // Log available information
//        System.out.println("\nfreqMap: " + freqMap + " | L: " + 0 + " | R: " + (keys.length - 1) +
//                " | n: " + keys.length + " | k: " + k + " | topKSeparatorIndex: " + topKSeparatorIndex);

        // Call the partition method to rearrange keys based on frequency
        partitionKeysForTopKElements(keys, freqMap, topKSeparatorIndex);

        // Return the top k frequent elements
        return Arrays.copyOfRange(keys, topKSeparatorIndex + 1, keys.length);
    }

    /**
     * Partitions the array of keys, placing the (n-k)th least frequent element at its correct sorted position.
     * <p>
     * Time Complexity:    O(n log n) - In the worst case, it may require multiple passes to find the correct position
     * Space Complexity:   O(1) - In-place rearrangement of the input array
     *
     * @param keys               The array of elements that will be partitioned based on topKSeparatorIndex.
     * @param freqMap            The frequency map of elements.
     * @param topKSeparatorIndex The index where the (n-k)th least frequent element
     *                           should be placed after the partial sort.
     */
    private void partitionKeysForTopKElements(int[] keys, Map<Integer, Integer> freqMap, int topKSeparatorIndex) {

        int left = 0;
        int right = keys.length - 1;

        // Continue partitioning until the (n-k)th least frequent element is at its sorted position
        while (left != right) {

            // Perform partition based on frequency
            int pivotIndex = partitionKeysBasedOnFrequency(keys, freqMap, left, right);

            // Check if pivotIndex is the desired topKSeparatorIndex position
            // Recursively partition the left or right side based on topKSeparatorIndex to find correct position
            if (pivotIndex == topKSeparatorIndex) return;
            else if (pivotIndex > topKSeparatorIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }
    }

    /**
     * Partitions the array of keys based on frequency, placing the pivot (right most element) at its correct sorted position.
     * <p>
     * Time Complexity:    O(n) - Linear scan through the input array
     * Space Complexity:   O(1) - In-place rearrangement of the input array
     *
     * @param keys    The array of elements to be partitioned.
     * @param freqMap The frequency map of elements.
     * @param left    The left index of the subarray to be partitioned.
     * @param right   The right index of the subarray to be partitioned.
     * @return The index where the pivot has been placed in its final sorted position.
     */
    private int partitionKeysBasedOnFrequency(int[] keys, Map<Integer, Integer> freqMap, int left, int right) {

        // Handle base case when right index is less than left
        if (right < left) return right;

        // Set pivot as the frequency of the mid element
        int mid = (left + right) / 2;
        int pivot = freqMap.get(keys[mid]);
        int pivotIndex = left;

        swapElements(keys, mid, right);   //  Move Pivot to the end of the Array

        // Iterate through the elements and partition based on frequency
        for (int i = left; i < right; i++)
            if (freqMap.get(keys[i]) < pivot) swapElements(keys, i, pivotIndex++);

        // Move the pivot to its correct position in the sorted array
        swapElements(keys, pivotIndex, right);

        // Return the index where the pivot has been placed
        return pivotIndex;
    }

    /**
     * Swaps elements at positions i and j in the array.
     * <p>
     * Time Complexity:    O(1) - Constant time operation
     * Space Complexity:   O(1) - No additional space used
     *
     * @param keys The array of elements.
     * @param i    The first index.
     * @param j    The second index.
     */
    private void swapElements(int[] keys, int i, int j) {
        int temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    //  ----------------------------------------------------------------------------------------------------

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
    //  ----------------------------------------------------------------------------------------------------

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

    //  ----------------------------------------------------------------------------------------------------

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

    //  ----------------------------------------------------------------------------------------------------

}