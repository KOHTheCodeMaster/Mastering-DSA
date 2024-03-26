package practice.leetcode;

import java.util.Stack;

public class S150 {

    public static void main(String[] args) {
        S150 obj = new S150();
        obj.major();
    }

    private void major() {

        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        int result = solutionUsingStack(tokens);

        System.out.println("Result: " + result);

    }

    private int solutionUsingStack(String[] tokens) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input array `tokens`
            Space Complexity:   O(n) - Stack
            Approach:           Using Stack
         */

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            //  Case: Operator
            if (isOperator(token)) {

                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(performOperation(operand1, operand2, token));

            } else stack.push(Integer.parseInt(token));    //  Case: Number
        }

        return stack.peek();

    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int performOperation(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    //  ----------------------------------------------------------------------------------------------------

}
