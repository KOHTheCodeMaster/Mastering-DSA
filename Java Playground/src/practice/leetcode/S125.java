package practice.leetcode;

public class S125 {
    public static void main(String[] args) {
        S125 obj = new S125();
        obj.major();
    }

    private void major() {

//        String s = "A man, a plan, a canal: Panama";
        String s = ".,";

//        boolean result = solutionUsingTwoPointers(s);
//        boolean result = solutionUsingRegex(s);
        boolean result = solutionUsingTwoPointersWithConstantSpace(s);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private boolean solutionUsingTwoPointers(String s) {
        /*
            Time Complexity:    O(n) - Linear time to scan through the input string `s`
            Space Complexity:   O(n) - Extra n Space for cleanedString
            Approach:           Using Two Pointers
         */

        final String s1 = "hello";


        StringBuilder cleanedString = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) if (Character.isLetterOrDigit(c)) cleanedString.append(c);

        //  It is an empty string "" after removing non-alphanumeric characters.
        //  Since an empty string reads the same forward and backward, it is a palindrome.
        if (cleanedString.length() == 0) return true;

        boolean isPallindrome = true;
        int left = 0;
        int right = cleanedString.length() - 1;

        while (left < right) {

            char leftChar = cleanedString.charAt(left);
            char rightChar = cleanedString.charAt(right);

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

    private boolean solutionUsingTwoPointersWithConstantSpace(String s) {
        /*
            Time Complexity:    O(n) - Linear time to scan through the input string `s`
            Space Complexity:   O(1) - Constant Space
            Approach:           Using Two Pointers
         */

        boolean isPallindrome = true;
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {

            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) left++;
            else if (!Character.isLetterOrDigit(rightChar)) right--;
            else if (leftChar != rightChar) {
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
        /*
            Time Complexity:    O(n) - Linear time to scan through the input string `s`
            Space Complexity:   O(n) - Extra Space used for cleanedString
            Approach:           Using Regex
         */

        String cleanedString = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");

        if (s.length() != cleanedString.length()) return false;

        for (int i = 0; i < s.length() / 2; i++)
            if (s.charAt(i) != cleanedString.charAt(s.length() - 1 - i)) return false;

        return true;
    }

    //  ----------------------------------------------------------------------------------------------------

}
