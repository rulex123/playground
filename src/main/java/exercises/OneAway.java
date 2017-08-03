
package exercises;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or
 * replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away. EXAMPLE
 * pale, ple true pales, pale -> true pale, bale -> true pale, bae -> false
 *
 * @author emanno
 * @version 1.0 Apr 19, 2017
 */
public class OneAway {

	public static void main ( String[] args ) {
		OneAway unit = new OneAway();

		String s1 = "pale";
		String s2 = "bae";

		if ( s1.length() == s2.length() ) {
			System.out.println( unit.oneEditReplace( s1, s2 ) );
		}
		else if ( s1.length() == s2.length() - 1 ) {
			System.out.println( unit.oneEditInsert( s1, s2 ) );
		}
		else if ( s1.length() == s2.length() + 1 ) {
			System.out.println( unit.oneEditInsert( s2, s1 ) );
		}

	}


	/**
	 * Can I insert a character in s1 to make it s2?
	 *
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean oneEditInsert ( String s1, String s2 ) {
		int index1 = 0;
		int index2 = 0;

		while ( index1 < s1.length() && index2 < s2.length() ) {
			if ( s1.charAt( index1 ) != s2.charAt( index2 ) ) {
				if ( index1 != index2 ) {
					// indexes were already out of sync, so it's not the first shift
					return false;
				}
				index2++;
			}
			else {
				index1++;
				index2++;
			}
		}

		return true;
	}


	private boolean oneEditReplace ( String s1, String s2 ) {
		boolean foundDiff = false;
		for ( int i = 0; i < s1.length(); i++ ) {
			if ( s1.charAt( i ) != s2.charAt( i ) ) {
				if ( foundDiff ) {
					return false;
				}
				foundDiff = true;
			}
		}

		return foundDiff;
	}

}
