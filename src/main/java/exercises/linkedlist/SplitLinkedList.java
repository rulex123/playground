
package exercises.linkedlist;

/**
 * Given a linked list, write a function that divides it into two halves. This function should modify the original list
 * and then return a pointer to the second half of the list.
 *
 * @author emanno
 * @version 1.0 Jul 9, 2017
 */
public class SplitLinkedList {

	public static void main ( String[] args ) {
		Node firstNodeOfSecondHalf = new Node( 4 );
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( firstNodeOfSecondHalf );
		head.appendToTail( 5 );

		System.out.println( "before split" );
		DeleteMiddleNode.printLinkedList( head );

		Node result = splitLinkedList( head );

		if ( result != firstNodeOfSecondHalf ) {
			System.err.println( "wrong!" );
		}

		System.out.println( "after split" );
		DeleteMiddleNode.printLinkedList( head );

	}


	public static Node splitLinkedList ( Node node ) {
		// special cases
		if ( node == null || node.next == null )
			return null;

		// setup 2 pointers, current and runner
		Node curr = node;
		Node runner = node.next;

		while ( runner != null && runner.next != null ) {
			curr = curr.next;
			runner = runner.next.next;
		}

		// curr now points to middle node: reset pointer to next element of curr
		Node secondHalfPointer = curr.next;
		curr.next = null;

		// return node that starts second half
		return secondHalfPointer;
	}

}
