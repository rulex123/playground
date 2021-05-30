
package exercises.linkedlist;

import java.util.Stack;

/**
 * Implement a function to check if a linked list is a palindrome. Example: palindrome(1->2->3) = false
 * palindrome(1->2->1) = true
 *
 * @author emanno
 * @version 1.0 Apr 25, 2017
 */
public class Palindrome {

	public static void main ( String[] args ) {
		// 1->2->3->4
		Node head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 4 );

		System.out.println( isPalindrome( head ) );

		// 1->2->2->1
		head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 2 );
		head.appendToTail( 1 );

		System.out.println( isPalindrome( head ) );

		// 1->2->3->2->1
		head = new Node( 1 );
		head.appendToTail( 2 );
		head.appendToTail( 3 );
		head.appendToTail( 2 );
		head.appendToTail( 1 );

		System.out.println( isPalindrome( head ) );

	}


	public static boolean isPalindrome ( Node node ) {
		if ( node == null || node.next == null )
			return false;

		Node curr = node;
		Node runner = node;
		Stack<Integer> numsSeenSoFar = new Stack<>();
		numsSeenSoFar.push( node.data );

		Node midNode;
		while ( true ) {
			curr = curr.next;
			runner = runner.next.next;

			if ( runner == null ) {
				midNode = curr;
				break;
			}
			else if ( runner.next == null ) {
				midNode = curr.next;
				break;
			}

			// if we are not at mid-list, push current node's value onto stack
			numsSeenSoFar.push( curr.data );
		}

		// inspect second half of linked list, and see if values we see correspond to
		// the ones we stored in the stack
		while ( midNode != null ) {
			if ( numsSeenSoFar.pop() != midNode.data ) {
				return false;
			}
			else {
				midNode = midNode.next;
			}
		}

		return true;
	}

}
