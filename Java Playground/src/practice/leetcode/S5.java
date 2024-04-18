
package practice.leetcode;

import java.util.*;

public class S5 {

    public static void main(String[] args) {
        S5 obj = new S5();
        obj.major();
    }

    private void major() {

        /*

            A B C D C A B

            A X Y Z B C D C B E  B  F
            0 1 2 3 4 5 6 7 8 9 10 11

            A B C D C A X Y Z B

            A B C B A B

            X Y X C B B A B B C D Y X Y

         */

//        String s = "axyzbcdcbebfwwwwwwwwwww";
//        String s = "ac";
//        String s = "xaabacxcabaaxcabaax";
//        String s = "abcdbbfcba";
        String s = "babaddtattarrattatddetartrateedredividerb";
//        String s = "aacabdkacaa";

        String result = solutionUsingTwoPointers(s);
//        String result = solution2(s);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    private String solutionUsingTwoPointers(String s) {
        /*
            Time Complexity:    O(n^2) - Iterate String s
            Space Complexity:   O(n) - for the result string
            Approach:           Using Two Pointers
         */

        //  Handle Edge Case
        if (s == null || s.length() <= 1) return s;

        String longestPalindrome = s.substring(0, 1);
        char[] charArr = s.toCharArray();
        Set<Character> visitedSet = new HashSet<>();

        Map<Character, List<Integer>> charIndicesMap = generateCharIndicesMap(charArr);

        for (int i = 0; i < s.length(); i++) {
            //  Iterate each character of string
            Character currentChar = s.charAt(i);
            List<Integer> indexList = charIndicesMap.get(currentChar);

            if (visitedSet.contains(currentChar)) continue; //  Skip: currentChar is already visited
            else visitedSet.add(currentChar);

            if (indexList.size() <= 1) continue;    //  Skip strings starting with current character since it occurs only once

            //  Skip: if longestPalindrome length is already greater than that of remaining string to be iterated
            if (longestPalindrome.length() >= s.length() - i + 1) break;

            longestPalindrome = findLongestPalindrome(s, indexList, longestPalindrome);

        }

//        System.out.println("longestPalindrome: " + longestPalindrome);

        return longestPalindrome;

    }

    private String findLongestPalindrome(String s, List<Integer> indexList, String longestPalindrome) {
        //  Process strings starting and ending with currentChar
        int leftIndex = 0;
        int left = indexList.get(leftIndex);
        int rightIndex = indexList.size() - 1;
        int right = indexList.get(rightIndex);

        //  Check if longestPalindrome length is greater than max possible length of strings starting and ending with currentChar
        if (longestPalindrome.length() >= right - left + 1) return longestPalindrome;

        while (left < right) {

            String str = s.substring(left, right + 1);
            boolean isPalindrome = isValidPalindrome(str);
            if (isPalindrome) {

                longestPalindrome = longestPalindrome.length() > str.length() ? longestPalindrome : str;

                //  Check length of next string for current character by moving left to next index and right at the end of indexList
                //  nextLeft = indexList.get(leftIndex + 1);    |   nextRight = indexList.get(indexList.size() - 1);
                //  Add 1 for string length since positions are 0-indexed
                int nextStrIterationLength = indexList.get(indexList.size() - 1) - indexList.get(leftIndex + 1) + 1;

                if (longestPalindrome.length() >= nextStrIterationLength) break;
                else {
                    //  Move Left to next & Reset Right
                    left = indexList.get(++leftIndex);
                    rightIndex = indexList.size() - 1;
                    right = indexList.get(rightIndex);
                }
            } else right = indexList.get(--rightIndex);

            if (left == right) {
                //  Move Left to next & Reset Right
                left = indexList.get(++leftIndex);
                rightIndex = indexList.size() - 1;
                right = indexList.get(rightIndex);
            }

        }
        return longestPalindrome;
    }

    private Map<Character, List<Integer>> generateCharIndicesMap(char[] charArr) {

        Map<Character, List<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < charArr.length; i++) {
            Character c = charArr[i];

            List<Integer> indexList = indexMap.getOrDefault(c, new ArrayList<>());
            indexList.add(i);

            indexMap.put(c, indexList);
        }

        return indexMap;
    }

    private boolean isValidPalindrome(String str) {

        boolean isPalindrome = true;

        int left = 0;
        int right = str.length() - 1;

        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;

    }

    //  ----------------------------------------------------------------------------------------------------

}
