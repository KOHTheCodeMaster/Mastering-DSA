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

        int kSmallest = 6;
//        int[] nums = {4, 3, 3, 2, 2, 1, 1, 1};
//        int[] nums = {512, 65, 66, 36, 69, 14, 52, 532, 84, 565, 85, 22, 23, 24, 89, 57, 58, 414}; // Size 18
        int[] nums = {100, 100, 100, 100, 90, 80, 80, 70, 70, 70, 60, 40, 50, 30, 30, 20, 20, 10};

        // Count the frequency of each element in nums and store in freqMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) freqMap.merge(x, 1, Integer::sum);   //  Initialize freqMap with Frequency Count -> Num

        // Get unique keys from freqMap
        int[] keys = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();

//        int k = 17;
//        int kSmallest = keys.length - k;

        // Call the sorting and partitioning method
        int kSmallestIndex = sortKeysWithFreqInAscendingOrder(keys, freqMap, 0, keys.length - 1, kSmallest);

        int[] resultArr = Arrays.copyOfRange(keys, kSmallestIndex + 1, keys.length);
        System.out.println(Arrays.toString(resultArr));

    }

    private int sortKeysWithFreqInAscendingOrder(int[] keys, Map<Integer, Integer> freqMap, int left, int right, int kSmallest) {

        // Print the current state of keys, left, right, kSmallest, and pivot
        System.out.println("keys: " + Arrays.toString(keys) + " | L: " + left + " | R: " + right +
                " | kSmallest: " + kSmallest + " | Pivot: " + right);

//        int[] arrSortedKeys = freqMap.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
//        System.out.println("Sorted Keys: " + Arrays.toString(arrSortedKeys) + "\n");

        int pivotIndex = partition(keys, freqMap, left, right, kSmallest);

        System.out.println("pivotIndex: " + pivotIndex);
        System.out.println("keys: " + Arrays.toString(keys) + " | L: " + left + " | R: " + right +
                " | kSmallest: " + kSmallest + " | Pivot: " + right);

        return pivotIndex;
    }

    private int partition(int[] keys, Map<Integer, Integer> freqMap, int left, int right, int kSmallest) {

        int pivot = right;
        int pivotIndex = left;
        int j = left;

        // Iterate through the elements and partition based on frequency
        while (j < right)
            if (keys[j] < keys[pivot]) swap(keys, pivotIndex++, j++);
            else j++;

        // Move the pivot to its correct position (pivotIndex) in the sorted array (keys),
        // as all elements to the left of the pivot are already in their place
        swap(keys, pivotIndex, pivot);

//        System.out.println("partition -> keys: " + Arrays.toString(keys) + " | pivotIndex: " + pivotIndex);

        // Check if pivotIndex is the desired kSmallest position
        if (pivotIndex == kSmallest - 1) return pivotIndex;
        // Recursively partition the left or right side based on kSmallest to find the correct position for kSmallest
        else if (pivotIndex > kSmallest - 1) pivotIndex = partition(keys, freqMap, left, pivotIndex - 1, kSmallest);
        else pivotIndex = partition(keys, freqMap, pivotIndex + 1, right, kSmallest);

        return pivotIndex;

    }

    private void swap(int[] keys, int i, int j) {

        int temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;

    }

    private void demoPartition(int[] keys, Map<Integer, Integer> freqMap, int left, int right, int kSmallest) {

        System.out.println("keys: " + Arrays.toString(keys) + " | L: " + left + " | R: " + right +
                " | kSmallest: " + kSmallest + " | Pivot: " + right);

        int pivotIndex = partition(keys, freqMap, left, right, kSmallest);

        System.out.println("pivotIndex: " + pivotIndex);
        System.out.println("keys: " + Arrays.toString(keys) + " | L: " + left + " | R: " + right +
                " | kSmallest: " + kSmallest + " | Pivot: " + right);

    }

}
