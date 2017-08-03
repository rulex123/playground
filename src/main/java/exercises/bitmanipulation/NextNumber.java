
package exercises.bitmanipulation;

/**
 * Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in
 * their binary representation.
 *
 * @author emanno
 * @version 1.0 Jul 20, 2017
 */
public class NextNumber {

	public static void main ( String[] args ) {
		// nextNumber( 13948 ); // 0b11011001111100
		System.out.println( 0xD );
		// System.out.println( Integer.toBinaryString( 0xaa ) );
	}


	public static void nextNumber ( int num ) {
		if ( num < 1 )
			throw new IllegalArgumentException( "input number must be positive" );

		System.out.println( "next bigger number is " + getBiggerNumber( num ) );
		System.out.println( "previous smaller number is " + getSmallerNumber( num ) );

	}


	public static int getSmallerNumber ( int num ) {

		int c = num;
		int c0 = 0;
		int c1 = 0;

		while ( (c & 1) == 1 ) {
			c1++;
			c = c >> 1;
		}

		// if input number is 00..0011..11 then there is no smaller number with the same number of ones
		if ( c == 0 )
			return -1;

		while ( (c & 1) == 0 ) {
			c0++;
			c = c >> 1;
		}

		int mask = (~0) << (c0 + c1 + 1);
		num = num & mask; // clear bits up to position (c0+c1+1)
		mask = (1 << (c1 + 1)) - 1;
		mask = mask << (c0 - 1);
		num = num | mask; // set following (c1 + 1) bits to 1
		return num;
	}


	public static int getBiggerNumber ( int num ) {

		int c = num;
		int c0 = 0;
		int c1 = 0;

		while ( (c & 1) == 0 ) {
			c0++;
			c = c >> 1;
		}

		while ( (c & 1) == 1 ) {
			c1++;
			c = c >> 1;
		}

		// if input number is 11..1100..00 then there is no bigger number with the same number of ones
		if ( c0 + c1 == 31 )
			return -1;

		int mask = 1 << (c0 + c1);
		num = num | mask; // flip right-most non trailing zero bit
		mask = ~((1 << (c0 + c1)) - 1);
		num = num & mask; // clear all bits to the right of (c0+c1)
		mask = (1 << (c1 - 1)) - 1;
		num = num | mask; // insert (c1-1) ones on the right

		return num;
	}

}
