package practice.leetcode;

public class S155_3 {

    private Node head;

    public static void main(String[] args) {
        S155_3 obj = new S155_3();
        obj.major();
    }

    private void major() {

        solutionUsingStack();

    }

    //  ----------------------------------------------------------------------------------------------------
    private void solutionUsingStack() {

        //  3 1 2 0 4 5
        S155_3 obj = new S155_3();
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

    public void push(int x) {
        if (head == null) head = new Node(x, x, null);
        else head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private static class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

}
