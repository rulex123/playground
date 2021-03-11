
package exercises.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a method to compute all permutations of a string whose characters are not necessarily unique. The list of
 * permutations should not have duplicates.
 *
 * @author emanno
 * @version 1.0 May 7, 2017
 */
public class PermutationsWithDups {

	public static void main ( String[] args ) {
		String input = "acca";
		System.out.println( printPerms( input ) );
	}


	public static List<String> printPerms ( String input ) {
		List<String> result = new ArrayList<>();
		printPerms( buildFreqMap( input ), "", input.length(), result );
		return result;
	}


	/*
	 * This implementation selects one char from input, then computes all permutations for the remaining chars in input,
	 * and finally forms permutations by prepending the selected char to those permutations
	 */
	private static void printPerms ( Map<Character, Integer> charMap, String prefix, int remaining,
			List<String> result ) {

		// base case
		if ( remaining == 0 ) {
			result.add( prefix );
			return;
		}

		for ( Character c : charMap.keySet() ) {
			int count = charMap.get( c );
			if ( count > 0 ) {
				charMap.put( c, count - 1 );
				printPerms( charMap, prefix + c, remaining - 1, result );
				charMap.put( c, count );
			}
		}
	}


	private static Map<Character, Integer> buildFreqMap ( String input ) {
		Map<Character, Integer> freqMap = new HashMap<>();

		char[] chars = input.toCharArray();
		for ( int i = 0; i < chars.length; i++ ) {
			int charFreq = freqMap.containsKey( chars[ i ] ) ? freqMap.get( chars[ i ] ) + 1 : 1;
			freqMap.put( chars[ i ], charFreq );
		}

		return freqMap;
	}

}
