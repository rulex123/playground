
package exercises.bitmanipulation;

/**
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double, print the binary representation. If
 * the number cannot be represented accurately in binary with at most 32 characters, print "ERROR".
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class BinaryToString {

	public static void main ( String[] args ) {
		System.out.println( binaryToString( 0.25 ) );
		System.out.println( binaryToString( 0.025 ) );
		System.out.println( binaryToString( 0.75 ) );
		System.out.println( binaryToString( 0.750001 ) );
	}


	public static String binaryToString ( double num ) {
		if ( num >= 1 || num <= 0 )
			throw new IllegalArgumentException( "number isn't between 0 and 1" );

		StringBuilder result = new StringBuilder();
		result.append( "0." );
		double takeAway = 0.5;
		while ( num > 0 ) {
			if ( result.length() == (32 + 2) ) // check if we are at max number of characters allowed to represent num
				return "ERROR";

			if ( num >= takeAway ) {
				result.append( 1 );
				num -= takeAway;
			}
			else {
				result.append( 0 );
			}

			takeAway /= 2;
		}

		return result.toString();
	}
}
