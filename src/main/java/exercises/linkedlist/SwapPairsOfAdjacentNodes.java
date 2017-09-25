
package exercises.linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. For example, Given 1->2->3->4, you should
 * return the list as 2->1->4->3. Your algorithm should use only constant space. You may not modify the values in the
 * list, only nodes themselves can be changed.<br>
 * NOTE: if the linked list has an odd number of links, the swapping should start at the head (so the last node stays
 * put)
 *
 * @author emanno
 * @version 1.0 Sep 19, 2017
 */
public class SwapPairsOfAdjacentNodes {

	public static void main ( String[] args ) {
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );
		head.appendToTail( 5 );

		DeleteMiddleNode.printLinkedList( head );

		System.out.println( "----------" );

		head = swapPairsOfAdjacentNodes( head );

		DeleteMiddleNode.printLinkedList( head );

	}


	public static Node swapPairsOfAdjacentNodes ( Node head ) {
		if ( head == null || head.next == null )
			return head;

		Node second = head.next;
		Node third = second.next;

		second.next = head;
		head.next = swapPairsOfAdjacentNodes( third );

		return second;
	}

}
