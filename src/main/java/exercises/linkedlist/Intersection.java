
package exercises.linkedlist;

/**
 * Given two (singly) linked lists, determine if the two lists intersect. Return the inter­secting node. Note that the
 * intersection is defined based on reference, not value.That is, if the kth node of the rst linked list is the exact
 * same node (by reference) as the jth node of the second linked list, then they are intersecting.
 *
 * @author emanno
 * @version 1.0 Apr 26, 2017
 */
public class Intersection {

	/**
	 * Wrapper class used to return last node and length of a linked list
	 */
	private static class LinkedListInfo {
		public final Node lastNode;
		public final int length;


		public LinkedListInfo ( Node lastNode, int length ) {
			super();
			this.lastNode = lastNode;
			this.length = length;
		}

	}


	public static void main ( String[] args ) {
		Intersection unit = new Intersection();

		Node tail = new Node( 4 );
		tail.appendToTail( 5 );

		Node l1 = new Node( 1 );
		l1.appendToTail( 3 );
		l1.appendToTail( tail );

		Node l2 = new Node( 7 );
		l2.appendToTail( 15 );
		l2.appendToTail( 8 );
		l2.appendToTail( 9 );
		l2.appendToTail( tail );

		Node intersection = unit.intersect( l1, l2 );
		if ( intersection == null ) {
			System.out.println( "No intersection" );
		}
		else {
			System.out.println( "Intersection (based on reference) at node with value " + intersection.data );
		}
	}


	private Node intersect ( Node n1, Node n2 ) {
		if ( n1 == n2 )
			return n1; // linked lists are the same
		LinkedListInfo l1Info = this.computeLinkedListInfo( n1 );
		LinkedListInfo l2Info = this.computeLinkedListInfo( n2 );

		if ( l1Info.lastNode == l2Info.lastNode ) {
			// linked lists intersect because they have the same last node
			// must find node at which they intersect

			int diffInLength = Math.abs( l1Info.length - l2Info.length );

			if ( diffInLength != 0 ) {
				if ( l1Info.length > l2Info.length ) {
					// move diffInLength spaces inside l1
					n1 = this.moveBy( n1, diffInLength );
				}
				else {
					// move diffInLength spaces inside l2
					n2 = this.moveBy( n2, diffInLength );
				}
			}

			// find node at which intersection occurs
			while ( n1.next != null && n2.next != null ) {
				if ( n1.next == n2.next ) {
					return n1.next;
				}
				n1 = n1.next;
				n2 = n2.next;
			}
		}

		// no intersection
		return null;
	}


	private Node moveBy ( Node startNode, int noOfPlaces ) {
		for ( int i = 0; i < noOfPlaces; i++ ) {
			startNode = startNode.next;
		}
		return startNode;
	}


	private LinkedListInfo computeLinkedListInfo ( Node head ) {
		Node lastNode = head;
		int length = 0;
		while ( lastNode.next != null ) {
			length++;
			lastNode = lastNode.next;
		}

		return new LinkedListInfo( lastNode, length );
	}

}
