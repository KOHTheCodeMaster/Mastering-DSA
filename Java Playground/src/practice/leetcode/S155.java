package practice.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class S155 {

    private final List<Integer> stack;
    private final LinkedList<Integer> minStack;

    private int top;

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

        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + obj.stack + " | MLL: " + obj.minStack);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + obj.stack + " | MLL: " + obj.minStack);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + obj.stack + " | MLL: " + obj.minStack);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + obj.stack + " | MLL: " + obj.minStack);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + obj.stack + " | MLL: " + obj.minStack);
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top() + " | Stack: " + stack + " | MLL: " + minStack);
//        obj.pop();

    }

    public S155() {
        stack = new ArrayList<>();
        minStack = new LinkedList<>();
        top = -1;
    }

    public void push(int val) {
        stack.add(val);
        top++;
        populateMinLinkedList(val);
    }

    private void populateMinLinkedList(int val) {
        if (minStack.isEmpty() || val < minStack.getFirst()) minStack.addFirst(val);
        else if (val < minStack.getLast()) minStack.add(1, val);
        else minStack.addLast(val);
    }

    public void pop() {
        Integer element = stack.remove(top--);
        minStack.remove(element);
    }

    public int top() {
        return stack.get(top);
    }

    public int getMin() {
        return minStack.getFirst();
    }

}
