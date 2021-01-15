
package exercises.recursion;

/**
 * Write a recursive function to multiply two positive integers without using the * operator. You can use addition,
 * subtraction, and bit shifting, but you should minimize the number of those operations.
 *
 * @author emanno
 * @version 1.0 May 9, 2017
 */
public class RecursiveMultiply {

	public static void main ( String[] args ) {
		System.out.println( recursiveMultiply( 16, 4 ) );
		System.out.println( iterativeMultiply( 16, 4 ) );

	}


	/*
	 * Recursive implementation: linear time complexity, based on the smaller of the two input
	 * numbers
	 */
	private static int recursiveMultiply( int a, int b ) {
		if ( a == 0 || b == 0 ) {
			return 0;
		}

		if ( a == 1 ) {
			return b;
		}

		if ( b == 1 ) {
			return a;
		}

		int smaller = a < b ? a : b;
		int bigger = a < b ? b : a;

		return bigger + recursiveMultiply( bigger, smaller - 1 );
	}

	/**
	 * Iterative implementation
	 */
	private static int iterativeMultiply(int a, int b) {
		if (a == 0 || b == 0)
			return 0;

		if (a == 1)
			return b;

		if (b == 1)
			return a;

		int smaller = a < b ? a : b;
		int bigger = a > b ? a : b;

		int result = bigger;
		for (int i = 0; i < smaller - 1; i++) {
			result += bigger;
		}

		return result;
	}

}
