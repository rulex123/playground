
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

		DeleteMiddleNode.printLinkedList( head );
		System.out.println( "-----------------" );
		removeDuplicates( head );
		DeleteMiddleNode.printLinkedList( head );

	}


	public static void removeDuplicates ( Node node ) {
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

}
