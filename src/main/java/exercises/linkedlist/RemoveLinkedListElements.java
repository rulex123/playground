
package exercises.linkedlist;

/**
 * Remove all elements from a linked list of integers that have value val. <br>
 * Example Given: <br>
 * 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
 *
 * @author emanno
 * @version 1.0 Sep 30, 2017
 */
public class RemoveLinkedListElements {

	public static void main ( String[] args ) {
		Node head = new Node( 6 );
		head.appendToTail( 2 );
		head.appendToTail( 6 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );
		head.appendToTail( 5 );
		head.appendToTail( 6 );

		DeleteMiddleNode.printLinkedList( head );

		System.out.println( "----------" );

		head = removeElements( head, 6 );

		DeleteMiddleNode.printLinkedList( head );

	}


	public static Node removeElements ( Node head, int value ) {
		if ( head == null )
			return null;

		Node preHead = new Node( 0 );
		preHead.next = head;

		Node curr = head;
		Node prev = preHead;

		while ( curr != null ) {
			if ( curr.data == value ) {
				prev.next = curr.next;
			}
			else {
				prev = prev.next;
			}
			curr = curr.next;
		}

		return preHead.next;
	}

}
