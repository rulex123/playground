
package exercises.stackandqueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a stack, reverse the items in the stack without using any additional data structures.
 *
 * @author emanno
 * @version 1.0 Jul 10, 2017
 */
public class ReverseStack {

	public static void main ( String[] args ) {
		Stack<Integer> stack = new Stack<>();
		stack.push( 4 );
		stack.push( 3 );
		stack.push( 2 );
		stack.push( 1 );

		System.out.println( "before" );
		System.out.println( Arrays.toString( stack.toArray() ) );
		reverseStack( stack );
		System.out.println( "after" );
		System.out.println( Arrays.toString( stack.toArray() ) );

	}


	public static void reverseStack ( Stack<Integer> stack ) {
		if ( stack.isEmpty() )
			return;

		Integer poppedElement = stack.pop();
		reverseStack( stack );
		insertAtBottom( stack, poppedElement );
	}


	public static void insertAtBottom ( Stack<Integer> stack, Integer element ) {
		if ( stack.isEmpty() ) {
			stack.push( element );
			return;
		}

		Integer prevElement = stack.pop();
		insertAtBottom( stack, element );
		stack.push( prevElement );
	}

}
