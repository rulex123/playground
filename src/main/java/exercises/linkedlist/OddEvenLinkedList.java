
package exercises.linkedlist;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
 * about the node number and not the value in the nodes. You should try to do it in place. The program should run in
 * O(1) space complexity and O(nodes) time complexity. <br>
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL. <br>
 * Note: The relative order inside both the even and odd groups should remain as it was in the input. The first node is
 * considered odd, the second node even and so on.
 *
 * @author emanno
 * @version 1.0 Sep 19, 2017
 */
public class OddEvenLinkedList {

	public static void main ( String[] args ) {
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );
		head.appendToTail( 5 );
		head.appendToTail( 6 );

		DeleteMiddleNode.printLinkedList( head );
		System.out.println( "----------" );

		head = oddEvenLinkedList( head );
		DeleteMiddleNode.printLinkedList( head );

	}


	public static Node oddEvenLinkedList ( Node head ) {
		if ( head == null || head.next == null )
			return head;

		Node currNode = head;
		Node firstEvenNode = head.next;
		Node lastOddNode = null;
		int nodesCounter = 0;

		while ( currNode.next != null ) {
			nodesCounter++; // increment counter

			// logic to determine the last odd node
			if ( currNode.next.next == null ) {
				if ( nodesCounter % 2 == 0 ) {
					lastOddNode = currNode.next;
				}
				else {
					lastOddNode = currNode;
				}
			}

			Node nextNode = currNode.next; // save original next node before resetting curr.next
			currNode.next = currNode.next.next; // reset curr.next
			currNode = nextNode; // swap current node with original next
		}

		// set last odd node to point to first even node
		lastOddNode.next = firstEvenNode;
		return head;
	}

}
