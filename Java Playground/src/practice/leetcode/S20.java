
package practice.leetcode;

import java.util.Stack;

public class S20 {
    public static void main(String[] args) {
        S20 obj = new S20();
        obj.major();
    }

    private void major() {

//        String s = "({[(())]})";
        String s = "()[]{}";

//        boolean result = solutionUsingStack(s);
        boolean result = solutionUsingStackWithMapping(s);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingStackWithPairs(String strBrackets) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input string `strBrackets`
            Space Complexity:   O(n)
            Approach:           Using Stack
         */

        boolean isValid = true;
        Stack<Character> bracketsStack = new Stack<>();

        for (Character bracket : strBrackets.toCharArray()) {

            //  Case: Open Bracket
            if (bracket == '(' || bracket == '{' || bracket == '[') bracketsStack.push(bracket);
            else {    //  Case: Closed Bracket
                if (!bracketsStack.isEmpty() && isValidBracketPair(bracketsStack.peek(), bracket)) bracketsStack.pop();
                else {
                    isValid = false;
                    break;
                }
            }

        }

        if (!bracketsStack.isEmpty()) isValid = false;

        return isValid;

    }

    private boolean isValidBracketPair(Character openBracket, Character closedBracket) {

        return (openBracket.equals('(') && closedBracket.equals(')')) ||
                (openBracket.equals('{') && closedBracket.equals('}')) ||
                (openBracket.equals('[') && closedBracket.equals(']'));

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingStackWithMapping(String strBrackets) {
        /*
            Time Complexity:    O(n) - Linear time to scan through input string `strBrackets`
            Space Complexity:   O(n)
            Approach:           Using Stack
         */

        boolean isValid = true;
        Stack<Character> bracketsStack = new Stack<>();

        for (Character bracket : strBrackets.toCharArray()) {
            //  Case: Open Bracket
            if (bracket == '(') bracketsStack.push(')');
            else if (bracket == '{') bracketsStack.push('}');
            else if (bracket == '[') bracketsStack.push(']');
            else {
                //  Case: Closed Bracket
                if (bracketsStack.isEmpty() || bracket != bracketsStack.pop()) {
                    isValid = false;
                    break;
                }
            }
        }

        if (!bracketsStack.isEmpty()) isValid = false;
        return isValid;

    }

    //  ----------------------------------------------------------------------------------------------------

}
