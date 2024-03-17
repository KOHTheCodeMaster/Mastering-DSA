package practice.leetcode;

public class S125 {
    public static void main(String[] args) {
        S125 obj = new S125();
        obj.major();
    }

    private void major() {

        String s = "A man, a plan, a canal: Panama";
//        String s = "12";

        boolean result = solutionUsingTwoPointers(s);
//        boolean result = solutionUsingRegex(s);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------
    private boolean solutionUsingTwoPointers(String s) {
        /*
            Time Complexity:    O(n) - iterates through the input string `s`
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers
         */

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) if (Character.isLetterOrDigit(c)) stringBuilder.append(c);

        //  It is an empty string "" after removing non-alphanumeric characters.
        //  Since an empty string reads the same forward and backward, it is a palindrome.
        if (stringBuilder.length() == 0) return true;

        boolean isPallindrome = true;
        int left = 0;
        int right = stringBuilder.length() - 1;

        while (left < right) {

            char leftChar = stringBuilder.charAt(left);
            char rightChar = stringBuilder.charAt(right);

            if (leftChar != rightChar) {
                isPallindrome = false;
                break;
            } else {
                left++;
                right--;
            }

        }

        return isPallindrome;
    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingRegex(String s) {

        String s2 = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        if (s.length() != s2.length()) return false;

        for (int i = 0; i < s.length(); i++) if (s.charAt(i) != s2.charAt(s.length() - 1 - i)) return false;

        return true;
    }

}
