
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
		System.out.println( recursiveMultiply_linear( 16, 4 ) );
	}


	/*
	 * Obvious implementation: linear time complexity, based on the smaller of the two input numbers
	 */
	private static int recursiveMultiply_linear ( int a, int b ) {
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

		return bigger + recursiveMultiply_linear( bigger, smaller - 1 );
	}
}
