package practice.unsorted;

import java.util.ArrayList;
import java.util.List;

public class Q26 {

    public static void main(String[] args) {

        Q26 obj = new Q26();
        obj.major();
        
//        System.arraycopy();
//        int[] arr = new int[5];
//        Arrays.copyOf(arr, 4);


        List<List<Integer>> lists = new ArrayList<>(10);
        lists.add(5, new ArrayList<>());
//        lists.set()

    }

    private void major() {

        int[][] arr = new int[][]{
                {1, 1, 2},
                {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
        };

        for (int[] nums : arr) {
            int k = removeDuplicates(nums);
            System.out.println("K: " + k);
        }

    }

    public int removeDuplicates(int[] nums) {

        /*
            0, 0, 1, 1, 1, 2, 2, 3, 3, 4

            0, 1, 2, 3, 1, 0, 2, 1, 3, 4

         */

        int k = 0;

        return k;

    }

}
