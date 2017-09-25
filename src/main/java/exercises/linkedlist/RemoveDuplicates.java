
package exercises.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Write code to remove duplicates from an unsorted linked list
 *
 * @author emanno
 * @version 1.0 Jun 27, 2017
 */
public class RemoveDuplicates {

	public static void main ( String[] args ) {
		Node head = new Node( 4 );
		head.appendToTail( 1 );
		head.appendToTail( 7 );
		head.appendToTail( 4 );
		head.appendToTail( 2 );
		head.appendToTail( 1 );

		System.out.println( "Removing duplicates from unsorted list" );
		DeleteMiddleNode.printLinkedList( head );
		System.out.println( "-----------------" );
		removeDuplicates_unsorted( head );
		DeleteMiddleNode.printLinkedList( head );

		head = new Node( 1 );
		head.appendToTail( 1 );
		head.appendToTail( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );
		head.appendToTail( 5 );
		head.appendToTail( 6 );

		System.out.println( "Removing duplicates from sorted list" );
		DeleteMiddleNode.printLinkedList( head );
		System.out.println( "-----------------" );
		head = removeDuplicates_sorted( head );
		DeleteMiddleNode.printLinkedList( head );

	}


	/**
	 * Removes duplicates from an unsorted linked list
	 */
	public static void removeDuplicates_unsorted ( Node node ) {
		Set<Integer> numsSeenSoFar = new HashSet<>();
		Node previous = null;

		while ( node != null ) {
			if ( numsSeenSoFar.contains( node.data ) ) {
				// duplicate, remove
				previous.next = node.next;
			}
			else {
				numsSeenSoFar.add( node.data );
				previous = node;
			}
			node = node.next;
		}
	}


	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
	 * original list. <br>
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. <br>
	 * Given 1->1->1->2->3, return 2->3.
	 */
	public static Node removeDuplicates_sorted ( Node head ) {
		if ( head == null || head.next == null )
			return head;

		Node preHead = new Node( 0 );// doesn't matter what value we put in this node

		Node fastPointer = head;
		Node slowPointer = preHead;
		slowPointer.next = head;

		while ( fastPointer != null ) {
			while ( fastPointer.next != null && fastPointer.data == fastPointer.next.data ) {
				fastPointer = fastPointer.next; // move pointer to last node which is a duplicate
			}

			if ( slowPointer.next != fastPointer ) { // duplicates were detected
				slowPointer.next = fastPointer.next;
				fastPointer = fastPointer.next;
			}
			else { // no duplicates
				fastPointer = fastPointer.next;
				slowPointer = slowPointer.next;
			}
		}

		return preHead.next;
	}

}
