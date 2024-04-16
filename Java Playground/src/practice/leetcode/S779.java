
package practice.leetcode;

public class S779 {

    public static void main(String[] args) {
        S779 obj = new S779();
        obj.major();
    }

    private void major() {

        int n = 5;
        int k = 9;

        int result = solutionUsingBitManipulation(n, k);
//        int result = solutionUsingBruteForce(n, k);

        System.out.println("Result: " + result);

    }

    private int solutionUsingBitManipulation(int n, int k) {
//        return Integer.bitCount(k - 1) % 2;
        return recursiveApproach(n, k);

    }

    //  ----------------------------------------------------------------------------------------------------

    private int recursiveApproach(int n, int k) {
        if (n == 1) return 0;     // Base Case
        int prev = recursiveApproach(n - 1, (k + 1) / 2);   // Recursive Step
        return (prev == 0) ? (k % 2 == 0 ? 1 : 0) : (k % 2 == 0 ? 0 : 1);
    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingBruteForce(int n, int k) {
        /*
            Time Complexity:    O(n)
            Space Complexity:   O(n)
            Approach:           Brute Force (Memory Limit Exceeded)
         */

        /*
            n = 6   |   k = 30
            0
            01
            0110
            01101001
            0110100110010110
            01101001100101101001011001101001
            01101001100101101001011001101001100101100110100101101001100101
            011010011001011010010110011010  -> 30
         */

        if (n == 1) return 0;   //  Handle Base Edge Case
//        System.out.println("(1 << n) - 1 : " + (1 << n - 1));

        int kMaxValue = (1 << n - 1);   //  K Max. -> 2^(n-1)
        StringBuilder binaryPattern = new StringBuilder("01");

        for (int index = 1; index < kMaxValue; index++) {

//            System.out.println("Index: " + index + " | Binary Pattern: " + binaryPattern +
//                    " | Pattern Length: " + binaryPattern.length() + " | K: " + k);

            if (k <= binaryPattern.length()) break;

            char digit = binaryPattern.charAt(index);
            if (digit == '0') binaryPattern.append("01");
            else if (digit == '1') binaryPattern.append("10");

        }

        return binaryPattern.charAt(k - 1) == '0' ? 0 : 1;

    }

    //  ----------------------------------------------------------------------------------------------------

    private int solutionUsingBruteForceMemoryError(int n, int k) {
        /*
            Time Complexity:    O(n)
            Space Complexity:   O(n)
            Approach:           Brute Force (Memory Limit Exceeded)
         */

        /*
            n = 6   |   k = 1
            0
            01
            0110
            01101001
            0110100110010110
            01101001100101101001011001101001
            01101001100101101001011001101001

         */

        return generateBinaryPattern(n).charAt(k - 1) == '0' ? 0 : 1;

    }

    private String generateBinaryPattern(int n) {

        StringBuilder binaryPattern = new StringBuilder();

        if (n == 1) return "0";

        String strPrevPattern = generateBinaryPattern(n - 1);

        for (char c : strPrevPattern.toCharArray()) {
            if (c == '0') binaryPattern.append("01");
            else if (c == '1') binaryPattern.append("10");
        }

        return binaryPattern.toString();
    }

    //  ----------------------------------------------------------------------------------------------------

}
