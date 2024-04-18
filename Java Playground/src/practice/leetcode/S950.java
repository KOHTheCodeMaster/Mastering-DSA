
package practice.leetcode;

import java.util.*;

public class S950 {

    public static void main(String[] args) {
        S950 obj = new S950();
        obj.major();
    }

    private void major() {

        int[] deck = {17, 13, 11, 2, 3, 5, 7};

        int[] result = solutionUsingSortedDeck(deck);

        System.out.println("Result: " + Arrays.toString(result));

    }

    //  ----------------------------------------------------------------------------------------------------

    private int[] solutionUsingSortedDeck(int[] deck) {
        /*
            Time Complexity:    O(n log n) - due to sorting
            Space Complexity:   O(n) - for the resultDeck array
            Approach:           Brute Force - iterating through the sorted deck
         */

        int[] resultDeck = new int[deck.length];
        int resultIndex = 0;    //  Pointer to keep track of the position in resultDeck
        int deckIndex = 0;      //  Pointer to iterate through the sorted deck array
        boolean skip = false;   //  Flag to alternate skipping and adding elements

        Arrays.sort(deck);

        while (deckIndex < deck.length) {

            if (resultDeck[resultIndex] != 0) {
                // If the current position in resultDeck is occupied, move to the next position
                resultIndex++;
                resultIndex %= deck.length;
            } else if (resultDeck[resultIndex] == 0 && !skip) {
                // If the current position in resultDeck is vacant and not supposed to be skipped, add the element from the sorted deck
                resultDeck[resultIndex] = deck[deckIndex++];

                //  Move Index to next vacant spot
                resultIndex++;
                resultIndex %= deck.length;

                // Set skip to true for the next iteration
                skip = true;
            } else {
                // If the current position in resultDeck is vacant and skipped, skip adding the element
                resultIndex++;
                resultIndex %= deck.length;
                skip = !skip;
            }

        }

        return resultDeck;

    }

    //  ----------------------------------------------------------------------------------------------------

}
