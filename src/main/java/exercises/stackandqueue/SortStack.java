
package exercises.stackandqueue;

public class SortStack {

	/**
	 * Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary
	 * stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the
	 * following operations: push, pop, peek and isEmpty.
	 *
	 * @param args
	 */
	public static void main ( String[] args ) {
		MyStack<Integer> stack = new MyStack<>();
		stack.push( 22 );
		stack.push( 1 );
		stack.push( 15 );
		stack.push( 7 );
		stack.push( 11 );

		sortStack( stack );

		System.out.println( stack.peek() );
		stack.pop();
		System.out.println( stack.peek() );
		stack.pop();
		System.out.println( stack.peek() );
		stack.pop();
		System.out.println( stack.peek() );
		stack.pop();
		System.out.println( stack.peek() );
	}


	public static void sortStack ( MyStack<Integer> stack ) {
		if ( stack == null || stack.isEmpty() )
			return;

		MyStack<Integer> tempStack = new MyStack<>();
		while ( !stack.isEmpty() ) {
			Integer element = stack.pop();

			while ( !tempStack.isEmpty() && element < tempStack.peek() ) {
				stack.push( tempStack.pop() );
			}

			tempStack.push( element );
		}

		// copy elements back to original stack
		while ( !tempStack.isEmpty() ) {
			stack.push( tempStack.pop() );
		}
	}

}
