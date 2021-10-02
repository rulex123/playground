package exercises.stackandqueue;

/**
 * How would you design a stack which, in addition to push and pop, has a function min which returns the minimum
 * element? Push, pop and min should all operate in O(1) time
 *
 * @author emanno
 * @version 1.0 Jun 28, 2017
 */
public class StackMin {

    private Node top;

    public static void main(String[] args) {
        StackMin unit = new StackMin();
        unit.push(7);
        unit.push(14);
        unit.push(2);
        unit.push(8);
        unit.push(1);

        System.out.println("Min value in stack is " + unit.min());
        unit.pop();
        System.out.println("Min value in stack is " + unit.min());

    }

    public void push(int data) {
        Node newTop = new Node(data);

        int newMin = data;
        if (this.top != null) {
            newTop.next = this.top;
            newMin = data < this.top.min ? data : this.top.min;
        }

        newTop.min = newMin;
        this.top = newTop;
    }


    public int pop() {
        if (this.top == null) {
            throw new NullPointerException();
        }

        int result = this.top.data;
        Node newTop = this.top.next;
        this.top = newTop;

        return result;
    }


    public int peek() {
        if (this.top == null) {
            throw new NullPointerException();
        }

        return this.top.data;
    }


    public int min() {
        if (this.top == null) {
            throw new NullPointerException();
        }

        return this.top.min;
    }


    public void print() {
        StringBuilder sb = new StringBuilder();
        if (this.top == null) {
            sb.append("null");
        }

        for (Node currNode = this.top; currNode != null; currNode = currNode.next) {
            sb.append(currNode.data);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static class Node {
        public int data;
        public int min;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

}
