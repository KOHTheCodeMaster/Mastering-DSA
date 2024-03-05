package practice.collections;

import java.util.HashMap;
import java.util.Map;

public class PracticeHashMap {

    public static void main(String[] args) {

        PracticeHashMap obj = new PracticeHashMap();
        obj.major();

    }

    private void major() {

        demoHashMapMerge();

    }

    private void demoHashMapMerge() {

        /*
            map.merge(key, baseValue, (currentValue, baseValueForSum) -> currentValue + baseValueForSum);

            Explanation:

            map: The Map instance on which the merge operation is performed.
            key: The key for which the value is being updated or inserted.
            baseValue: The initial value to be used if the key is not present or has a null value.
                       In the provided lambda, this serves as the default value (base value) to start with.
            currentValue: The current value associated with the key in the map (if the key is present).
            baseValueForSum: The value provided as the second argument to the merge method (in this case, baseValue).
                             It serves as the base value for any computation or combination with the currentValue.

         */
        int[] nums = {10, 10, 10, 20, 20, 30, 30, 30, 30};
        int baseValue = 1;

        // Using HashMap to count the frequency of each number
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Using a traditional loop with explicit merge logic
        for (int x : nums) {
            System.out.println("\nX: " + x);
            freqMap.merge(x, baseValue, (currentValue, baseValueForSum) -> {
                System.out.println("currentValue: " + currentValue + " | baseValueForSum: " + baseValueForSum);
//                System.out.println("currentValue: " + freqMap.get(x) + " | baseValueForSum: " + baseValue);
                return currentValue + baseValueForSum;
            });
        }

        System.out.println(freqMap);
        freqMap = new HashMap<>();

        // Using a more concise approach with method reference
        for (int x : nums) freqMap.merge(x, 1, Integer::sum);

        System.out.println(freqMap);

    }

}
