
package exercises.bitmanipulation;

/**
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 *
 * @author emanno
 * @version 1.0 Jul 26, 2017
 */
public class Conversion {

	public static void main ( String[] args ) {
		System.out.println( conversion( 0b11001101, 0b01001101 ) ); // expected 1
		System.out.println( conversion( 0b11111111, 0b00000000 ) ); // expected 8
		System.out.println( conversion( 0b00101100, 0b10011100 ) ); // expected 3
		System.out.println( conversion( 0b11111111111111111101100110001000, 0b100010101100101 ) ); // expected 26
	}

	public static int conversion ( int a, int b ) {
		int xor = a ^ b;
		int count = 0;
		while ( xor != 0 ) {
			count += xor & 1;
			xor = xor >>> 1;
		}
		return count;
	}

}
