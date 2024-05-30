
package practice.leetcode;

public class S744 {
    public static void main(String[] args) {
        S744 obj = new S744();
        obj.major();
    }

    private void major() {

//        char[] letters = {'c', 'f', 'j'};
//        char[] letters = {'a', 'b', 'c', 'd', 'e'};
        char[] letters = {'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd', 'd', 'd', 'd', 'd', 'e'};
        char target = 'd';

        char result = solutionUsingUpperBoundBinarySearch(letters, target);

        System.out.println("Result: " + result);

    }

    //  ----------------------------------------------------------------------------------------------------

    public char solutionUsingUpperBoundBinarySearch(char[] letters, char target) {
        /*
            Time Complexity:    O(log n) - Binary Search - Each iteration halves the search space
            Space Complexity:   O(1) - Constant Memory
            Approach:           Binary Search - Upper Bound
         */

        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }

        return left == letters.length ? letters[0] : letters[left];

    }

    //  ----------------------------------------------------------------------------------------------------

}
