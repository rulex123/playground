
package exercises.linkedlist;

/**
 * Some 'reverse linked list' fun
 *
 * @author emanno
 * @version 1.0 Sep 25, 2017
 */
public class ReverseLinkedList {

	public static void main ( String[] args ) {
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );
		head.appendToTail( 5 );
		head.appendToTail( 6 );

		DeleteMiddleNode.printLinkedList( head );

		System.out.println( "----------" );

		head = reverseLinkedListBetween( head, 3, 6 );
		DeleteMiddleNode.printLinkedList( head );
	}


	/**
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass. <br>
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL. <br>
	 * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
	 */
	public static Node reverseLinkedListBetween ( Node head, int m, int n ) {
		if ( head == null || head.next == null )
			return head;

		if ( m == n )
			return head;

		Node preHead = new Node( 0 );
		preHead.next = head;

		Node preReversed = preHead; // pointer to last node before reversing
		for ( int i = 0; i < m - 1; i++ ) {
			preReversed = preReversed.next;
		}
		Node firstReversed = preReversed.next; // pointer to first reversed node

		// now start reversing nodes
		Node prev = firstReversed;
		Node curr = firstReversed.next;
		for ( int i = 0; i < n - m; i++ ) {
			// reverse the node
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		// join reversed nodes to rest of nodes
		preReversed.next = prev;
		firstReversed.next = curr;

		return preHead.next;
	}


	/**
	 * Reverse linked list (vanilla problem)
	 */
	public static Node reverseLinkedList ( Node head ) {
		if ( head == null || head.next == null )
			return head;

		Node prev = null;
		Node curr = head;

		while ( curr != null ) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}

}
