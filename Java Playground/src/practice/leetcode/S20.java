
package practice.leetcode;

import java.util.Stack;

public class S20 {
    public static void main(String[] args) {
        S20 obj = new S20();
        obj.major();
    }

    private void major() {

        String s = "({[(())]})";

        boolean result = solutionUsingStack(s);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingStack(String inputStr) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input string `inputStr`
            Space Complexity:   O(n)
            Approach:           Using Stack
         */

        boolean isValid = true;
        Stack<Character> stack = new Stack<>();

        for (Character c : inputStr.toCharArray()) {

            //  Case: Open Bracket
            if (c.equals('(') || c.equals('{') || c.equals('[')) stack.push(c);
            else {    //  Case: Closed Bracket

                if (!stack.isEmpty() && isValidBracketPair(stack.peek(), c)) stack.pop();
                else {
                    isValid = false;
                    break;
                }
            }

        }

        if (!stack.isEmpty()) isValid = false;

        return isValid;

    }

    private boolean isValidBracketPair(Character openBracket, Character closedBracket) {

        return (openBracket.equals('(') && closedBracket.equals(')')) ||
                (openBracket.equals('{') && closedBracket.equals('}')) ||
                (openBracket.equals('[') && closedBracket.equals(']'));

    }

}
