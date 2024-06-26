package practice.leetcode;

import java.util.Stack;

public class S155_2 {

    private final Stack<int[]> stack;

    public static void main(String[] args) {
        S155_2 obj = new S155_2();
        obj.major();
    }

    private void major() {

        solutionUsingStack();

    }

    //  ----------------------------------------------------------------------------------------------------
    private void solutionUsingStack() {

        //  3 1 2 0 4 5
        S155_2 obj = new S155_2();
        obj.push(3);
        obj.push(1);
        obj.push(2);
        obj.push(0);
        obj.push(4);
        obj.push(5);

        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
        obj.pop();
        System.out.println("Min: " + obj.getMin() + " | Top: " + obj.top());
//        obj.pop();

    }

    public S155_2() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) stack.push(new int[]{val, val});
        else stack.push(new int[]{val, Math.min(val, stack.peek()[1])});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

}
