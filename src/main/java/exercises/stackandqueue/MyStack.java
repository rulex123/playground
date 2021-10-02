package exercises.stackandqueue;

/**
 * My implementation of the stack data structure
 *
 * @param <T>
 * @author emanno
 * @version 1.0 Jun 28, 2017
 */
public class MyStack<T> {

    private StackNode<T> top;
    private int size;

    public T pop() throws NullPointerException {
        if (this.top == null)
            throw new NullPointerException();

        T result = this.top.data;

        // reset top of stack
        this.top = this.top.next;

        // decrease size
        this.size--;

        return result;
    }

    public void push(T value) {
        StackNode<T> newTop = new StackNode<T>(value);
        newTop.next = this.top;
        this.top = newTop;
        this.size++;
    }

    public T peek() throws NullPointerException {
        if (this.top == null)
            throw new NullPointerException();
        return this.top.data;
    }

    public int size() {
        if (this.top == null) {
            return 0;
        } else {
            return this.size;
        }
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    private static class StackNode<T> {
        public T data;
        public StackNode<T> next;


        public StackNode(T data) {
            super();
            this.data = data;
        }

    }


}
