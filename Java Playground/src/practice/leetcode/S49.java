package practice.leetcode;

import java.util.*;

public class S49 {

    public static void main(String[] args) {
        S49 obj = new S49();
        obj.major();
    }

    private void major() {

//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        String[] strs = {"eat", "tan"};
        String[] strs = {"bdddddddddd", "bbbbbbbbbbc"};
//        String[] strs = {"abc", "bca", "dbc"};

        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists);

    }

    public List<List<String>> groupAnagrams(String[] strs) {

//        return solutionUsingHashMap(strs);

        return solutionUsingHashMapWithCharFreqAsKey(strs);

//        return solutionUsingCharFreqArr(strs);

    }

    //  ----------------------------------------------------------------------------------------------------

    private List<List<String>> solutionUsingHashMap(String[] strs) {
        /*
            Time Complexity:    O(n * m)
            Space Complexity:   O(n * m)
            Approach:           Using HashMap
            Note:               n is the number of words and m is the average length of the words.
        */

        List<List<String>> groupedAnagrams;

        //  Store CharFreqMap as Key & List of words that has the same CharFreqMap as Value
        Map<Map<Character, Integer>, List<String>> charFreqMapToWords = new HashMap<>();

        for (String currentWord : strs) {
            Map<Character, Integer> currentWordCharFreqMap = generateCharFreqMap(currentWord);

            //  Get the list of words for CurrentWord's CharFreqMap
            List<String> wordsWithSameCharFreq = charFreqMapToWords.getOrDefault(currentWordCharFreqMap, new ArrayList<>());
            wordsWithSameCharFreq.add(currentWord);

            //  Update charFreqMapToWords with currentWord Added to the list for currentWordCharFreqMap
            charFreqMapToWords.put(currentWordCharFreqMap, wordsWithSameCharFreq);
        }

        groupedAnagrams = new ArrayList<>(charFreqMapToWords.values());
        return groupedAnagrams;

    }

    private Map<Character, Integer> generateCharFreqMap(String word) {

        Map<Character, Integer> charFreqMap = new HashMap<>();

        for (Character c : word.toCharArray()) charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);

        return charFreqMap;

    }

    //  ----------------------------------------------------------------------------------------------------

    private List<List<String>> solutionUsingHashMapWithCharFreqAsKey(String[] strs) {
        /*
            Time Complexity:    O(n * m)
            Space Complexity:   O(n * m)
            Approach:           Using HashMap
            Note:               n is the number of words and m is the average length of the words.
        */

        List<List<String>> groupedAnagrams;

        //  Store CharFreqMap as Key & List of words that has the same CharFreqMap as Value
        Map<String, List<String>> strKeyCharFreqToWords = new HashMap<>();

        for (String currentWord : strs) {
            String currentWordStrKeyCharFreq = generateStrKeyCharFreq(currentWord);

            List<String> wordsWithSameStrKeyCharFreq = strKeyCharFreqToWords.getOrDefault(currentWordStrKeyCharFreq, new ArrayList<>());
            wordsWithSameStrKeyCharFreq.add(currentWord);

            strKeyCharFreqToWords.put(currentWordStrKeyCharFreq, wordsWithSameStrKeyCharFreq);
        }

        groupedAnagrams = new ArrayList<>(strKeyCharFreqToWords.values());
        return groupedAnagrams;

    }

    private String generateStrKeyCharFreq(String word) {

        StringBuilder strKeyCharFreq = new StringBuilder();
        int[] arrCharFreq = new int[26];

        for (Character c : word.toCharArray()) arrCharFreq[c - 'a']++;

        //  Store Char Freq Pattern in the form of String | E.g.: AC - 1 0 1 0 0 0 ... -> 101000...
        for (int x : arrCharFreq) {
            /*
              To Separate the special edge case of bdddddddddd, bbbbbbbbbbc
              where the Char Freq Str becomes same for 2 different strings
              Hence, adding extra space before & after 2 digit freq as salt in the key to make Char Freq Str Key different for each string
              Before Salt: bdddddddddd -> 010100000000000000000000000  |  bbbbbbbbbbc -> 010100000000000000000000000
              After  Salt: bdddddddddd -> 010 10 000...  |  bbbbbbbbbbc -> 0 10 1000...
            */
            if (x > 9) strKeyCharFreq.append(" ");
            strKeyCharFreq.append(x);
            if (x > 9) strKeyCharFreq.append(" ");
        }

        return strKeyCharFreq.toString();

    }

    //  ----------------------------------------------------------------------------------------------------

    private List<List<String>> solutionUsingCharFreqArr(String[] strs) {
        /*
            Time Complexity:    O(n * m)
            Space Complexity:   O(n * m)
            Approach:           Using Character Frequency Array
            Note:               n is the number of words and m is the average length of the words.
        */

        List<List<String>> groupedAnagrams = new ArrayList<>();

        //  Generate CharFreqArr for all words of strs
        int[][] charFreqArrays = generateCharFreqArrOfAllWords(strs);
        boolean[] isProcessed = new boolean[strs.length];

        List<String> currentAnagramGroup = new ArrayList<>();
        int leftPointer = 0;
        int rightPointer = 0;

        //  Iterate each word of strs
        while (leftPointer < strs.length) {

            int[] leftWordFreqArr = charFreqArrays[leftPointer];

            if (isProcessed[leftPointer]) {
                leftPointer++;
                rightPointer = leftPointer;
                continue;
            } else if (leftPointer == rightPointer) currentAnagramGroup.add(strs[leftPointer]);

            rightPointer++;

            if (rightPointer == strs.length) {
                //  Move leftPointer by 1 & reset rightPointer with leftPointer
                isProcessed[leftPointer] = true;
                leftPointer++;
                rightPointer = leftPointer;

                groupedAnagrams.add(currentAnagramGroup);
                currentAnagramGroup = new ArrayList<>();

            } else if (Arrays.equals(leftWordFreqArr, charFreqArrays[rightPointer])) {
                currentAnagramGroup.add(strs[rightPointer]);
                isProcessed[rightPointer] = true;
            }

        }

        return groupedAnagrams;
    }

    private int[][] generateCharFreqArrOfAllWords(String[] words) {

        int[][] arrOfCharFreqArr = new int[words.length][26];

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];

            int[] tempArrCharFreq = new int[26];
            for (char c : currentWord.toCharArray()) tempArrCharFreq[c - 'a']++;

            arrOfCharFreqArr[i] = tempArrCharFreq;
        }

        return arrOfCharFreqArr;
    }

    //  ----------------------------------------------------------------------------------------------------

}
