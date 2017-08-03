
package exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class PaoloExercise {
	public static void main ( String[] args ) {

		List<Integer> input = Arrays.asList( 1, 1, 2, 3, 6, 6, 7, 3, 4, 10, 1, 2, 3 );

		Set<Integer> result = new HashSet<>( input );

		// map of element -> no of duplicates
		HashMap<Integer, Integer> counts = new HashMap<>();
		for ( Integer i : input ) {
			counts.put( i, counts.get( i ) == null ? 1 : counts.get( i ) + 1 );
		}

		// get element with highest number of duplicates
		int maxNoOfDuplicates = 0;
		int elementWithMaxNoOfDuplicates = 0;
		for ( Entry<Integer, Integer> entry : counts.entrySet() ) {
			if ( entry.getValue() > maxNoOfDuplicates ) {
				maxNoOfDuplicates = entry.getValue();
				elementWithMaxNoOfDuplicates = entry.getKey();
			}
		}

		// print results
		System.out.println( result );
		System.out.println( elementWithMaxNoOfDuplicates );

	}
}
