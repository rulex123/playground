
package exercises.string;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or a phrase
 * that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to
 * be limited to just dictionary words.<br>
 * EXAMPLE: <br>
 * Input: Tact Coa<br>
 * Output: True (permutations: "taco cat","atco cta",etc.)
 *
 * @author emanno
 * @version 1.0 Aug 4, 2017
 */
public class PalindromePermutation {

	public static void main ( String[] args ) {
		System.out.println( palindromePermutation( "Tact Coa" ) );
		System.out.println( palindromePermutation( "Hello There" ) );
	}


	public static boolean palindromePermutation ( String string ) {

		// first, make the input string lower case, as we are treating permutations as case-insensitive
		char[] chars = string.toLowerCase().toCharArray();

		int oddCount = 0;
		Integer[] freqTable = new Integer['z' - 'a' + 1];
		for ( char c : chars ) {
			if ( c >= 'a' && c <= 'z' ) {
				int index = c - 'a';
				freqTable[ index ] = (freqTable[ index ] == null) ? 1 : (freqTable[ index ] + 1);
				oddCount = (freqTable[ index ] % 2 == 0) ? (oddCount - 1) : (oddCount + 1);
			}
		}

		return oddCount <= 1;
	}

}
