package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class S155 {

    private final List<Integer> list;
    private final List<Integer> minList;

    public static void main(String[] args) {
        S155 obj = new S155();
        obj.major();
    }

    private void major() {

        solutionUsingStack();

    }

    //  ----------------------------------------------------------------------------------------------------
    private void solutionUsingStack() {

        //  3 1 2 0 4 5
        S155 obj = new S155();
        obj.push(3);
        obj.push(1);
        obj.push(2);
        obj.push(0);
        obj.push(4);
        obj.push(5);

        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + obj.list + " | Min List: " + obj.minList);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + obj.list + " | Min List: " + obj.minList);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + obj.list + " | Min List: " + obj.minList);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + obj.list + " | Min List: " + obj.minList);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + obj.list + " | Min List: " + obj.minList);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | List: " + list + " | Min List: " + minList);
//        obj.pop();

    }

    public S155() {
        list = new ArrayList<>();
        minList = new ArrayList<>();
    }

    public void push(int val) {
        list.add(val);
        if (minList.isEmpty() || val <= minList.get(minList.size() - 1)) minList.add(val);
    }

    public void pop() {
        if (list.isEmpty()) throw new IllegalStateException("Stack Is EMPTY!");

        int element = list.remove(list.size() - 1);
        if (!minList.isEmpty() && element == minList.get(minList.size() - 1)) minList.remove(minList.size() - 1);
    }

    public int top() {
        if (list.isEmpty()) throw new IllegalStateException("Stack Is EMPTY!");
        return list.get(list.size() - 1);
    }

    public int getMin() {
        return minList.get(minList.size() - 1);
    }

}
