
package exercises;

public class StringCompression {

	public static void main ( String[] args ) {

		StringCompression unit = new StringCompression();
		System.out.println( unit.compress( "aabcccccaaaz" ) );

	}


	private String compress ( String s ) {
		StringBuilder sb = new StringBuilder();

		int charCount = 0;
		for ( int i = 0; i < s.length(); i++ ) {
			charCount++;

			if ( (i + 1) >= s.length() || (s.charAt( i ) != s.charAt( i + 1 )) ) {
				sb.append( s.charAt( i ) );
				sb.append( charCount );
				// reset counter for char
				charCount = 0;
			}

		}

		return sb.length() == s.length() ? s : sb.toString();
	}

}
