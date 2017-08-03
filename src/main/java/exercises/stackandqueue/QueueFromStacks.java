
package exercises.stackandqueue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implement a queue from two stacks
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class QueueFromStacks {

	public static class QueueFromTwoStacks {

		Stack<Integer> stack_1 = new Stack<>();
		Stack<Integer> stack_2 = new Stack<>();


		public void offer ( int item ) {
			this.stack_2.push( new Integer( item ) );
		}


		public int poll () {
			if ( this.stack_1.isEmpty() ) {
				if ( this.stack_2.isEmpty() )
					throw new EmptyStackException();

				while ( !this.stack_2.isEmpty() ) {
					this.stack_1.push( this.stack_2.pop() );
				}
			}

			return this.stack_1.pop().intValue();
		}

	}


	public static void main ( String[] args ) {
		QueueFromTwoStacks queue = new QueueFromTwoStacks();
		queue.offer( 1 );
		queue.offer( 2 );
		queue.offer( 3 );
		queue.offer( 4 );

		System.out.println( queue.poll() ); // expected 1
		System.out.println( queue.poll() ); // expected 2
		System.out.println( queue.poll() ); // expected 3
		System.out.println( queue.poll() ); // expected 4

	}

}
