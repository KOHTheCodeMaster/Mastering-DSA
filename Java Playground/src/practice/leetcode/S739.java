package practice.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class S739 {

    private int position;

    public static void main(String[] args) {
        S739 obj = new S739();
        obj.major();
    }

    private void major() {

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
//        int[] temperatures = {1, 4, 5, 1, 0, 2, 6};
//        int[] temperatures = {1, 5, 7, 2, 3, 4, 6};
//        int[] temperatures = {1, 2, 3, 4};
//        int[] temperatures = {4, 3, 2, 1, 5, 4, 3, 2, 1, 6};

        int[] answer = solutionUsingStack(temperatures);

        System.out.println("Answer: " + Arrays.toString(answer));

    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionUsingStack(int[] temperatures) {
        /*
            Time Complexity:    O(n) - Linear time to iterate through the temperatures array
            Space Complexity:   O(n) - Stack space used to store temperatures
            Approach:           Using Stack to find the next higher temperature index
         */

        int[] answer = new int[temperatures.length];
        Stack<Integer> dayStack = new Stack<>();

        for (int day = 0; day < temperatures.length; day++) {
            while (!dayStack.isEmpty() && temperatures[dayStack.peek()] < temperatures[day]) {
                // Check if the current temperature is higher than the temperature at the top of the stack
                int prevDay = dayStack.pop();
                answer[prevDay] = day - prevDay;    // no. of days until a warmer temperature is encountered
            }
            dayStack.push(day);
        }

        return answer;

    }

    //  ----------------------------------------------------------------------------------------------------

}
