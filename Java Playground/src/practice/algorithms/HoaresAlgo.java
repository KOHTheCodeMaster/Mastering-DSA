package practice.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HoaresAlgo {

    public static void main(String[] args) {

        HoaresAlgo obj = new HoaresAlgo();

        obj.major();

    }

    private void major() {

        demoHoaresAlgo();

    }

    private void demoHoaresAlgo() {

//        int[] nums = {4, 3, 3, 2, 2, 1, 1, 1};
//        int[] nums = {512, 65, 66, 36, 69, 14, 52, 532, 84, 565, 85, 22, 23, 24, 89, 57, 58, 414}; // Size 18
        int[] nums = {100, 100, 100, 100, 90, 80, 80, 70, 70, 70, 60, 40, 50, 30, 30, 20, 20, 10};
        int k = 10;  //  Top K Elements

        // Count the frequency of each element in nums and store in freqMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) freqMap.merge(x, 1, Integer::sum);   //  Initialize freqMap: [Frequency Count -> Num]

        // Get unique keys from freqMap
        int[] keys = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();

        int topKSeparatorIndex = keys.length - (k + 1);    // Index of (n - k)th least frequent element (0-based)

        System.out.println("\nkeys: " + Arrays.toString(keys) + " | L: " + 0 + " | R: " + (keys.length - 1) +
                " | n: " + keys.length + " | k: " + k + " | topKSeparatorIndex: " + topKSeparatorIndex);

        // Call the sorting and partitioning method
        partitionKeysForTopKElements(keys, topKSeparatorIndex);

        System.out.println("\nkeys: " + Arrays.toString(keys));

        int[] resultArr = Arrays.copyOfRange(keys, topKSeparatorIndex + 1, keys.length);
        System.out.println("\nTop " + k + " Frequent Elements: " + Arrays.toString(resultArr));

    }

    /**
     * Finds the top k elements in an array by rearranging elements such that the (n-k)th smallest
     * element is at its final sorted position, resulting in the top k elements to its right.
     *
     * <p>
     * Example:
     * Given arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]    |   n = 10  |   k = 3
     * - Top 3rd Element  ->      8 |   Top 3 Elements -> [8, 9, 10]                        |   Index Pos. -> [7, 8, 9]
     * - 8th Smallest Element->   8 |   Elements to Right of 8th Smallest -> [9, 10]        |   Index Pos. -> [8, 9]
     * - 7th Smallest Element->   7 |   Elements to Right of 7th Smallest -> [8, 9, 10]     |   Index Pos. -> [7, 8, 9]
     * - When 7th Smallest Element is put at its sorted position in arr, Top 3 Elements are located at its right.
     *
     * <p>
     * When (n-k)th smallest element is placed at its sorted position in arr, top k elements are located to its right.
     * To find the top k elements, first we identify the (n-k)th smallest element,
     * and place it at its sorted position in the array, ensuring left elements are smaller and right ones
     * are bigger than it. All elements to its right then provide the top k elements.
     *
     * <p>
     * For example, if the array represents elements in the range [1-10] and n = 10, k = 3,
     * the goal is to find the top 3 elements, which are [8, 9, 10]. By putting the (n-k)th Smallest
     * (7th least frequent) element at its sorted position (7th position), the elements to its right provide
     * the Top K frequent elements.
     *
     * <p>
     * Algorithm:
     * 1. Perform a partial sort: Move elements from less frequent to the most frequent until
     * the (n - k)th least frequent element is at (n - k)th position in the sorted array.
     * 2. All elements to the left are less frequent.
     * 3. All elements to the right are more frequent.
     *
     * @param keys               The array of elements that will be partitioned based on topKSeparatorIndex
     * @param topKSeparatorIndex represents the index where the (n-k)th least frequent element
     *                           should be placed after the partial sort. This index acts as a separator,
     *                           ensuring that the top k elements are located to its right.
     */
    private void partitionKeysForTopKElements(int[] keys, int topKSeparatorIndex) {

        int left = 0;
        int right = keys.length - 1;

        while (left != right) {

            int pivotIndex = partitionArray(keys, left, right);

            // Check if pivotIndex is the desired topKSeparatorIndex position
            // Recursively partition the left or right side based on topKSeparatorIndex to find correct position for topKSeparatorIndex
            if (pivotIndex == topKSeparatorIndex) return;
            else if (pivotIndex > topKSeparatorIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }

    }

    /**
     * Partitions the array, placing the pivot (right most element) at its correct sorted position.
     *
     * @param arr   The array of elements to be partitioned.
     * @param left  The left index of the subarray to be partitioned.
     * @param right The right index of the subarray to be partitioned.
     * @return The index where the pivot has been placed in its final sorted position.
     */
    private int partitionArray(int[] arr, int left, int right) {

        // Handle base case when right index is less than left
        if (right < left) return right;

        // Set pivot as the rightmost element
        int pivot = arr[right]; //  Todo: Choose Pivot as Middle or Random Element instead of last element for optimization
        int pivotIndex = left;

        // Iterate through the elements and keep swapping elements lesser than pivot to the left
        for (int i = left; i < right; i++) if (arr[i] < pivot) swapElements(arr, pivotIndex++, i);

        // Move the pivot to its correct position (pivotIndex) in the sorted array (arr),
        // as all elements to the left of the pivot are already in their place
        swapElements(arr, pivotIndex, right);

        // Return the index where the pivot has been placed
        return pivotIndex;

    }

    /**
     * Swaps elements at positions i and j in the array.
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

}
