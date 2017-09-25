
package exercises.linkedlist;

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit.The digits are
 * stored in reverse order, such that the 1 's digit is at the head of the list. Write a function that adds the two
 * numbers and returns the sum as a linked list. EXAMPLE Input:(7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295. Output:2
 * -> 1 -> 9.That is,912.
 *
 * @author emanno
 * @version 1.0 Apr 24, 2017
 */
public class SumLists {


	public static void main ( String[] args ) {

		Node l1 = new Node( 7 );
		l1.appendToTail( 1 );
		l1.appendToTail( 6 );

		Node l2 = new Node( 5 );
		l2.appendToTail( 9 );
		l2.appendToTail( 2 );

		l1.print();
		l2.print();

		int carry = 0;
		Node result = null;
		while ( l1 != null || l2 != null ) { // until either linked list has a node
			int data1 = l1 != null ? l1.data : 0;
			int data2 = l2 != null ? l2.data : 0;
			int value = (data1 + data2 + carry) % 10; // calculate value for node to add to result
			carry = (data1 + data2 + carry) >= 10 ? 1 : 0; // set carry for next iteration
			if ( result == null ) {
				result = new Node( value ); // create head of result linked list
			}
			else {
				result.appendToTail( value ); // append node to result linked list
			}
			l1 = l1 != null ? l1.next : null;
			l2 = l2 != null ? l2.next : null;
		}

		// we broke out of the while look, so we exhausted both input linked lists
		if ( carry == 1 ) {
			result.appendToTail( carry );
		}

		result.print();

	}

}