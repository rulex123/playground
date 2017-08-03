
package exercises.linkedlist;


/**
 * Given a singly linked list, write a function to find the nth-to-last
 * element of the list
 *
 * Example:
 * 1->2->3->4->5->6
 * n=1 output=5
 * n=2 output=4
 * n=3 output=3
 * etc.
 *
 *
 * @author emanno
 * @version 1.0 Jun 26, 2017
 */
public class NthToLastElement {

	public static void main ( String[] args ) {
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );

		System.out.println( nthToLastElement( head, 3 ) );
	}


	public static Integer nthToLastElement ( Node node, int n ) {
		if ( node == null || node.next == null )
			return null;

		Node curr = node;
		Node follower = node;

		// initially, move curr node to n positions forward
		for ( int i = 0; i < n; i++ ) {
			curr = curr.next;
			if ( curr == null )
				// check if value of n causes us to go past last node
				throw new IllegalArgumentException( "Invalid value for n!" );
		}

		// then, move curr and follower at same pace
		while ( curr.next != null ) {
			curr = curr.next;
			follower = follower.next;
		}

		return follower.data;
	}

}
