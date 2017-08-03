
package exercises.linkedlist;

/**
 * Given a linked list, write a function that prints the nodes of the list in reverse order
 *
 * @author emanno
 * @version 1.0 Jun 26, 2017
 */
public class PrintReversedLinkedList {

	public static void main ( String[] args ) {
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 4 );
		head.appendToTail( 8 );
		head.appendToTail( 16 );
		head.appendToTail( 32 );
		head.appendToTail( 64 );
		head.appendToTail( 128 );

		printReversedLinkedList( head );
	}


	public static void printReversedLinkedList ( Node node ) {
		if ( node == null )
			return;

		printReversedLinkedList( node.next );
		System.out.println( node.data );
	}

}
