package exercises.stackandqueue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implement a queue from two stacks
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class QueueFromStacks {

    public static void main(String[] args) {
        QueueFromTwoStacks<Integer> queue = new QueueFromTwoStacks<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll()); // expected 1
        System.out.println(queue.peek()); // expected 2
        System.out.println(queue.poll()); // expected 2
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue.poll()); // expected 3
        System.out.println(queue.poll()); // expected 4

    }

    public static class QueueFromTwoStacks<T> {
        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();


        public void offer(T element) {
            this.stack1.push(element);
        }


        public T poll() {
            this.checkForEmptyStacks();
            if (!this.stack2.isEmpty()) {
                return this.stack2.pop();
            }
            this.fillStack2();
            return this.stack2.pop();
        }


        public T peek() {
            this.checkForEmptyStacks();
            if (!this.stack2.isEmpty()) {
                return this.stack2.peek();
            }
            this.fillStack2();
            return this.stack2.peek();
        }


        private void checkForEmptyStacks() {
            if (this.stack2.isEmpty() && this.stack1.isEmpty()) {
                throw new EmptyStackException();
            }
        }


        private void fillStack2() {
            while (!this.stack1.isEmpty()) {
                this.stack2.push(this.stack1.pop());
            }
        }
    }

}
