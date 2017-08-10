
package exercises.bitmanipulation;

/**
 * Given two integers, write a function to determine whether or not their binary representation differ by a single bit.
 *
 * @author emanno
 * @version 1.0 Aug 9, 2017
 */
public class GrayCode {

	public static void main ( String[] args ) {
		System.out.println( grayCode( 0, 1 ) ); // expected true
		System.out.println( grayCode( 1, 2 ) ); // expected false
		System.out.println( grayCode( 2, 3 ) ); // expected true
	}


	public static boolean grayCode ( int a, int b ) {
		int xor = a ^ b;
		return (xor & (xor - 1)) == 0;
	}

}
