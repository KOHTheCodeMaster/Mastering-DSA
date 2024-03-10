package practice.leetcode;

import java.util.*;

public class S36 {
    public static void main(String[] args) {
        S36 obj = new S36();
        obj.major();
    }

    private void major() {

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board2 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '6', '.', '8', '.', '.', '7', '9'}
        };

        boolean isValidSudoku = isValidSudoku(board2);
        System.out.println(isValidSudoku);

    }

    private boolean isValidSudoku(char[][] board) {

//        return solutionUsingFreqMap(board);

        return solutionUsingBruteForce(board);

    }

    private boolean solutionUsingBruteForce(char[][] board) {

        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

    private boolean solutionUsingFreqMap(char[][] board) {

        boolean isValidSudoku = true;

        Map<Integer, Map<Integer, Integer>> rowToNumFreqMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> colToNumFreqMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> cubeFrequencyMap = new HashMap<>();

        for (int i = 0; i < board.length; i++) {

            if (!isValidSudoku) break;

            char[] row = board[i];
            isValidSudoku = validateFrequency(rowToNumFreqMap, row, i);

            if (!isValidSudoku) break;

            char[] column = getIColumn(board, i);
            isValidSudoku = validateFrequency(colToNumFreqMap, column, i);

            if (!isValidSudoku) break;

            isValidSudoku = validateCubeFrequencyMap(cubeFrequencyMap, row, i);

//            for (int cubeNum = 0; cubeNum < cubeFrequencyMap.size(); cubeNum++)
//                System.out.println("cubeNum: " + cubeNum + " | Frequencies: " + (cubeFrequencyMap.get(cubeNum)));
//            System.out.println();

        }

//        isValidSudoku = validateCubes2(board);

        return isValidSudoku;
    }

    private boolean validateCubeFrequencyMap(Map<Integer, Map<Integer, Integer>> cubeFrequencyMap, char[] row, int roWNum) {

        boolean isValidCube = true;
        int colIndex = 0;
        int cubeNum = (roWNum / 3) * 3;
        roWNum %= 3;

        outer:
        for (int i = 0; i < 3; i++, cubeNum++, colIndex += 3) {

            Map<Integer, Integer> freqMap = cubeFrequencyMap.getOrDefault(cubeNum, new HashMap<>());

            for (int x : Arrays.copyOfRange(row, colIndex, colIndex + 3)) {
                if (x == '.') continue;
                int xFreq = freqMap.merge(x, 1, Integer::sum);

                if (xFreq > 1) {
                    isValidCube = false;
                    break outer;
                }
            }

            cubeFrequencyMap.put(cubeNum, freqMap);
        }

        return isValidCube;

    }

    private boolean validateFrequency(Map<Integer, Map<Integer, Integer>> counterToNumFreqMap, char[] nums, int i) {

        boolean isValidRowFrequency = true;

        for (int x : nums) {
            if (x == '.') continue;

            Map<Integer, Integer> numFreqMap = counterToNumFreqMap.getOrDefault(i, new HashMap<>());    //  Get num frequency map for ith row or column
            int xFreq = numFreqMap.merge(x, 1, Integer::sum);   //  Update x frequency
            counterToNumFreqMap.put(i, numFreqMap); //  Update num frequency map for ith nums

            if (xFreq >= 2) {
                isValidRowFrequency = false;
                break;
            }
        }

        return isValidRowFrequency;
    }

    private char[] getIColumn(char[][] board, int i) {

        char[] column = new char[board.length];

        for (int j = 0; j < board.length; j++) column[j] = board[j][i];

        return column;
    }


    //  Brute Force Method to Validate Each Cube
    private boolean validateCubes2(char[][] board) {

        boolean isValidCube = true;

        for (int cubeNum = 0; cubeNum < board.length; cubeNum++) {

            Map<Integer, Integer> freqMap = new HashMap<>();

            for (int rowNum = 0; rowNum < 3; rowNum++) {
                for (int colNum = 0; colNum < 3; colNum++) {

                    int x = board[rowNum][colNum];
                    if (x == '.') continue;

                    int xFreq = freqMap.merge(x, 1, Integer::sum);

                    if (xFreq >= 2) {
                        isValidCube = false;
                        break;
                    }
                }
            }

        }

        return isValidCube;
    }

    //  Unnecessary Method To Create Cube Map
    private void generateCubeMap2(Map<Integer, char[][]> cubeMap, char[] row, int roWNum) {

        int colIndex = 0;
        int cubeNum = (roWNum / 3) * 3;
        roWNum %= 3;

        for (int i = 0; i < 3; i++, cubeNum++, colIndex += 3) {

            char[][] chars = cubeMap.getOrDefault(cubeNum, new char[3][3]);
            chars[roWNum] = Arrays.copyOfRange(row, colIndex, colIndex + 3);
            cubeMap.put(cubeNum, chars);

        }

    }

    //  ----------------------------------------------------------------------------------------------------

    //  ----------------------------------------------------------------------------------------------------

}
