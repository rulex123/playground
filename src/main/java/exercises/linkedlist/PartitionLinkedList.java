package exercises.linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x. You should preserve the original relative order of the nodes in each of the two partitions. <br>
 * For example, given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 *
 * @author emanno
 * @version 1.0 Sep 23, 2017
 */
public class PartitionLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.appendToTail(4);
        head.appendToTail(3);
        head.appendToTail(2);
        head.appendToTail(5);
        head.appendToTail(2);

        DeleteMiddleNode.printLinkedList(head);

        System.out.println("----------");

        head = partitionLinkedList(head, 3);

        DeleteMiddleNode.printLinkedList(head);
    }


    public static Node partitionLinkedList(Node node, int x) {
        if (node == null)
            return null;

        Node startFirstPartition = new Node(0); // dummy node
        Node startSecondPartition = new Node(0); // dummy node
        Node firstPartition = startFirstPartition;
        Node secondPartition = startSecondPartition;

        while (node != null) {
            if (node.data < x) {
                firstPartition.next = node;
                firstPartition = firstPartition.next;
            } else {
                secondPartition.next = node;
                secondPartition = secondPartition.next;
            }
            node = node.next;
        }

        secondPartition.next = null;
        firstPartition.next = startSecondPartition.next;
        return startFirstPartition.next;
    }

}
