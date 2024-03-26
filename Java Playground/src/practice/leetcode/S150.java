package practice.leetcode;

import java.util.Stack;

public class S150 {

    private int position;

    public static void main(String[] args) {
        S150 obj = new S150();
        obj.major();
    }

    private void major() {

        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

//        int result = solutionUsingStack(tokens);
        int result = solutionUsingRecursion(tokens);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

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

    private int solutionUsingRecursion(String[] tokens) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input array `tokens` recursively from end
            Space Complexity:   O(n) - Stack space for recursive calls
            Approach:           Recursive Token Evaluation
         */

        position = tokens.length - 1;
        return evaluate(tokens);

    }

    private int evaluate(String[] tokens) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input array `tokens` recursively from end
            Space Complexity:   O(n) - Stack space for recursive calls
            Approach:           Recursive Token Evaluation
         */

        String token = tokens[position--];

        switch (token) {
            case "+":
                return evaluate(tokens) + evaluate(tokens);

            case "-":   //  A - B = (-B) + A
                return (-evaluate(tokens)) + evaluate(tokens);

            case "*":
                return evaluate(tokens) * evaluate(tokens);

            case "/":
                int denominator = evaluate(tokens);
                return evaluate(tokens) / denominator;

            default:
                return Integer.parseInt(token);
        }
    }

}
