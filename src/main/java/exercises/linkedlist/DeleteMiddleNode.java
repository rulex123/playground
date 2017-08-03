
package exercises.linkedlist;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node
 *
 * @author emanno
 * @version 1.0 Jun 26, 2017
 */
public class DeleteMiddleNode {

	public static void main ( String[] args ) {
		Node nodeToDelete1 = new Node( 3 );
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( nodeToDelete1 );
		head.appendToTail( 4 );

		printLinkedList( head );
		deleteMiddleNode( nodeToDelete1 );
		System.out.println( "after delete" );
		printLinkedList( head );

		System.out.println( "----------" );

		Node nodeToDelete2 = new Node( 5 );
		head = new Node( 3 );
		head.appendToTail( nodeToDelete2 );
		head.appendToTail( 6 );

		printLinkedList( head );
		deleteMiddleNode( nodeToDelete2 );
		System.out.println( "after delete" );
		printLinkedList( head );

	}


	public static void deleteMiddleNode ( Node nodeToDelete ) {
		int value = nodeToDelete.next.data;

		Node nextNode = null;
		if ( nodeToDelete.next.next != null ) {
			nextNode = nodeToDelete.next.next;
		}

		nodeToDelete.data = value;
		nodeToDelete.next = nextNode;
	}


	public static void printLinkedList ( Node node ) {
		while ( node != null ) {
			System.out.println( node.data );
			node = node.next;
		}
	}

}
