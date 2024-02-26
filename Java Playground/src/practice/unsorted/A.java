package practice.unsorted;

import java.util.Arrays;

public class A {

    public static void main(String[] args) {

        A obj = new A();
        obj.major();

    }

    private void major() {

        helper1();

    }

    private void helper1() {

        int[] arr = new int[]{-6, -5, -4, 0, 2, 5};
        int startIndex = 1;
        int endIndex = 5;
        int key = 4;

        int index = Arrays.binarySearch(arr, startIndex, endIndex, key);
//        int index = Arrays.binarySearch(arr, key);
        int index2 = index;

        if (index2 < 0) index2 = -(index2 + 1);

        System.out.println("Index: " + index + " | Index2: " + index2);

    }


}
