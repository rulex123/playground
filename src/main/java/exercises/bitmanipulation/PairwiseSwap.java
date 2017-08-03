
package exercises.bitmanipulation;

/**
 * Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g. bit 0 and bit 1
 * are swapped, bit 2 and bit 3 are swapped, and so on).)
 *
 * @author emanno
 * @version 1.0 Jul 26, 2017
 */
public class PairwiseSwap {

	public static void main ( String[] args ) {
		System.out.println( Integer.toBinaryString( pairwiseSwap( 0b10101010 ) ) ); // expected 01010101
		System.out.println( Integer.toBinaryString( pairwiseSwap( 0b01100011 ) ) ); // expected 10010011
	}


	public static int pairwiseSwap ( int num ) {
		return ((num & 0xAAAAAAAA) >>> 1) | ((num & 0x55555555) << 1);
	}
}
