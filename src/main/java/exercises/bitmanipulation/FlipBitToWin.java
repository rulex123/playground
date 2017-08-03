
package exercises.bitmanipulation;

/**
 * You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest
 * sequence of 1s you could create. <br>
 * EXAMPLE <br>
 * input: 1775 (in binary 11011101111) <br>
 * output: 8
 *
 * @author emanno
 * @version 1.0 Jul 14, 2017
 */
public class FlipBitToWin {

	public static void main ( String[] args ) {
		System.out.println( flipBitToWin( -1 ) ); // expected 32
		System.out.println( flipBitToWin( 0b11011101111 ) ); // expected 8
		System.out.println( flipBitToWin( 0b111101110111 ) ); // expected 8
		System.out.println( flipBitToWin( 0b1110011101111 ) ); // expected 8
		System.out.println( flipBitToWin( 0 ) ); // expected 1
	}


	public static int flipBitToWin ( int num ) {
		if ( num == ~0 )
			return Integer.BYTES * 8; // max possible number of 1s

		int currentLength = 0;
		int prevLength = 0;
		int maxLength = 1; // min possible number of 1s

		while ( num != 0 ) {
			if ( (num & 1) == 1 ) { // bit is 1
				currentLength++;
			}
			else {
				// bit is 0
				// but is the following bit a 1 or 0?
				if ( (num & 2) == 0 ) {
					// following bit is 0
					prevLength = 0;
				}
				else {
					// following bit is 1
					prevLength = currentLength;
				}
				currentLength = 0;
			}
			maxLength = Math.max( currentLength + prevLength + 1, maxLength );
			num >>>= 1;
		}

		return maxLength;
	}



}
