
package exercises.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a stack using two queues
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class StackFromQueues {

	public static class EmptyStackException extends RuntimeException {
		private static final long serialVersionUID = 2116305097722419575L;
	}

	public static class StackFromTwoQueues {
		private Queue<Integer> queue_1 = new LinkedList<>();
		private Queue<Integer> queue_2 = new LinkedList<>();


		public void push ( int item ) {
			this.queue_2.add( new Integer( item ) );
			while ( !this.queue_1.isEmpty() ) {
				this.queue_2.add( this.queue_1.poll() );
			}

			Queue<Integer> temp = this.queue_2;
			this.queue_2 = this.queue_1;
			this.queue_1 = temp;
		}


		public int pop () {
			if ( this.queue_1.isEmpty() )
				throw new EmptyStackException();

			return this.queue_1.poll().intValue();
		}

	}

	/**
	 * Implement a stack using a single queue
	 *
	 * @author emanno
	 * @version 1.0 Jul 11, 2017
	 */
	public static class StackFromSingleQueue {

		private final Queue<Integer> queue = new LinkedList<>();


		public void push ( int item ) {
			this.queue.add( new Integer( item ) );
			if ( this.queue.size() > 1 ) {
				// pop out all but last element and enque them again
				for ( int i = 0; i <= this.queue.size() - 2; i++ ) {
					this.queue.add( this.queue.poll() );
				}
			}
		}


		public Integer pop () {
			if ( this.queue == null || this.queue.isEmpty() )
				throw new EmptyStackException();

			return this.queue.poll().intValue();
		}
	}


	public static void main ( String[] args ) {
		StackFromSingleQueue stackFromSingleQ = new StackFromSingleQueue();
		stackFromSingleQ.push( 1 );
		stackFromSingleQ.push( 2 );
		stackFromSingleQ.push( 3 );
		stackFromSingleQ.push( 4 );

		System.out.println( stackFromSingleQ.pop() ); // expected 4
		System.out.println( stackFromSingleQ.pop() ); // expected 3
		System.out.println( stackFromSingleQ.pop() ); // expected 2
		System.out.println( stackFromSingleQ.pop() ); // expected 1

		System.out.println( "--------" );

		StackFromTwoQueues stackFromTwoQs = new StackFromTwoQueues();
		stackFromTwoQs.push( 5 );
		stackFromTwoQs.push( 6 );
		stackFromTwoQs.push( 7 );
		stackFromTwoQs.push( 8 );

		System.out.println( stackFromTwoQs.pop() ); // expected 8
		System.out.println( stackFromTwoQs.pop() ); // expected 7
		System.out.println( stackFromTwoQs.pop() ); // expected 6
		System.out.println( stackFromTwoQs.pop() ); // expected 5

	}

}
