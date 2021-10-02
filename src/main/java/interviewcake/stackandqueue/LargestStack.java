package interviewcake.stackandqueue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * You want to be able to access the largest element in a stack.
 * <p>
 * Use the built-in Stack class to implement a new class MaxStack with a method getMax() that
 * returns the largest element in the stack. getMax() should not remove the item.
 * <p>
 * Your stacks will contain only integers.
 */
public class LargestStack {

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);

        System.out.println(maxStack.getMax()); // only one element in the stack, which is also max (5)

        maxStack.push(2);
        maxStack.push(3);
        System.out.println(maxStack.getMax()); // max remains unchanged (5)
        maxStack.push(7);
        maxStack.push(1);
        System.out.println(maxStack.getMax()); // there is a new max (7)
        maxStack.push(13);
        System.out.println(maxStack.getMax()); // there is a new max (13)
        maxStack.pop();
        System.out.println(maxStack.getMax()); // we go back to previous max (7)
    }

    static class MaxStack {

        private Stack<Node> stack = new Stack<>();

        int pop() {
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }
            Node top = stack.pop();
            return top.data;
        }

        void push(int data) {
            if (stack.isEmpty()) {
                Node newTop = new Node(data, data);
                stack.push(newTop);
                return;
            }

            Node currTop = stack.peek();
            int newMax = data > currTop.max ? data : currTop.max;
            Node newTop = new Node(data, newMax);
            stack.push(newTop);
        }

        int getMax() {
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }
            Node currTop = stack.peek();
            return currTop.max;
        }

        private static class Node {

            int data;
            int max;

            private Node(int data, int max) {
                this.data = data;
                this.max = max;
            }
        }

    }
}
