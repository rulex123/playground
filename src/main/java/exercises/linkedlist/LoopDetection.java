package exercises.linkedlist;

/**
 * Given a circular linked list (i.e. a linked list with a loop), implement an algorithm that
 * returns the node at the beginning of the loop.
 */
public class LoopDetection {

    public static void main(String[] args) {
        // 1->2->3->4->null
        Node head = new Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);

        System.out.println(loopDetection(head));

        // 1 ->2->3->4->5->6->7->4
        head = new Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        Node loopNode = new Node(4);
        head.appendToTail(loopNode);
        head.appendToTail(5);
        head.appendToTail(6);
        head.appendToTail(7);
        head.appendToTail(loopNode);

        System.out.println(loopDetection(head));
    }

    /**
     * Checks if the linked list is circular (i.e. has a loop). If the linked list is circular,
     * returns node at which 2 pointers (slow and fast) meet; if the linked list is
     * not circular, returns null.
     */
    private static Node isCircular(Node node) {
        Node current = node;
        Node runner = node;

        while (runner != null && runner.next != null) {
            current = current.next;
            runner = runner.next.next;

            if (current == runner) {
                // we detected a loop!
                return current;
            }
        }

        // if we got here, it means that the linked list is not circular!
        return null;
    }

    private static Node loopDetection(Node node) {
        if (node == null || node.next == null) {
            return null;
        }

        Node meetNode = isCircular(node);

        if (isCircular(node) == null) {
            return null;
        }

        Node currentPtr = node;
        Node meetNodePtr = meetNode;

        while (true) {
            currentPtr = currentPtr.next;
            meetNodePtr = meetNodePtr.next;

            if (currentPtr == meetNodePtr) {
                // found the beginning of the loop!
                return currentPtr;
            }
        }
    }

}
