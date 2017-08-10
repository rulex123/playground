
package exercises.string;

/**
 * Prints all permutations of a string
 *
 * @author emanno
 * @version 1.0 Apr 7, 2017
 */
public class PermutationsOfString {

	public static void main ( String[] args ) {

		PermutationsOfString unit = new PermutationsOfString();
		unit.permutation( "abc" );

	}


	void permutation ( String str ) {
		this.permutation( str, "" );
	}


	void permutation ( String str, String prefix ) {
		if ( str.length() == 0 ) {
			System.out.println( prefix );
		}
		else {
			for ( int j = 0; j < str.length(); j++ ) {
				String rem = str.substring( 0, j ) + str.substring( j + 1 );
				System.out.println( rem );
				this.permutation( rem, prefix + Character.toString( str.charAt( j ) ) );
			}
		}
	}

}
