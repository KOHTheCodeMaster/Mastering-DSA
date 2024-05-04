package practice.leetcode;

import java.util.Arrays;
import java.util.Collections;

public class S881 {
    public static void main(String[] args) {
        S881 obj = new S881();
        obj.major();
    }

    private void major() {

//        int[] people = {1, 2};
//        int[] people = {3, 2, 2, 1};
//        int[] people = {3, 5, 3, 4};
//        int[] people = {5, 4, 4, 3, 1, 1};
//        int[] people = {5, 1, 4, 2};
        int[] people = {3, 2, 3, 2, 2};
        int limit = 6;

        int result = solutionUsingTwoPointers(people, limit);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------


    private int solutionUsingTwoPointers(int[] people, int limit) {
        /*
            Time Complexity:    O(n) - Linear Time to scan people
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers
         */

        int numOfBoats = 1;
        int boatWeightCapacity = limit;
        int left = 0;
        int right = people.length - 1;
        int passengerCount = 0;
        final int MAX_OCCUPANCY = 2;

        people = Arrays.stream(people).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        // Iterate through the people array using two pointers
        while (left <= right) {

            // Attempt to accommodate the left person
            if (passengerCount < MAX_OCCUPANCY && boatWeightCapacity >= people[left]) {
                boatWeightCapacity -= people[left++];
                passengerCount++;
            } else if (passengerCount < MAX_OCCUPANCY && boatWeightCapacity >= people[right]) {
                // Attempt to accommodate the right person
                boatWeightCapacity -= people[right--];
                passengerCount++;
            } else {
                //  If the boat capacity is full or cannot accommodate any more people:
                //  Reset - Increment the number of boats required, reset boat capacity and passenger count.
                boatWeightCapacity = limit;
                passengerCount = 0;
                numOfBoats++;
            }

        }

        return numOfBoats;

    }

    //  ----------------------------------------------------------------------------------------------------

}
