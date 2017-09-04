
package exercises.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given k sorted arrays, merge them into a single sorted array
 *
 * @author emanno
 * @version 1.0 Sep 3, 2017
 */
public class MergeSortedArrays {

	public static void main ( String[] args ) {
		List<int[]> input = new ArrayList<>();
		input.add( new int[] {
				1, 1, 3, 5, 7
		} );
		input.add( new int[] {
				2, 4, 4, 6, 8
		} );
		input.add( new int[] {
				0, 3, 7, 10
		} );

		System.out.println( Arrays.toString( mergeArrays( input ) ) );

	}


	public static Integer[] mergeArrays ( List<int[]> arrays ) {
		if ( arrays == null )
			return null;

		int[] positions = new int[arrays.size()];
		List<Integer> result = new ArrayList<>();

		while ( true ) {
			boolean done = true; // tells whether we have looked at all numbers across all arrays
			int minIndex = -1;
			for ( int i = 0; i < positions.length; i++ ) {
				if ( positions[ i ] == arrays.get( i ).length )
					continue; // skip to next array
				else
					done = false; // at least one of the input arrays hasn't been scanned completely

				if ( minIndex == -1 )
					minIndex = i;

				if ( arrays.get( i )[ positions[ i ] ] <= arrays.get( minIndex )[ positions[ minIndex ] ] )
					minIndex = i;
			}

			if ( done )
				break; // we are done!

			result.add( arrays.get( minIndex )[ positions[ minIndex ] ] ); // store the number in result array
			positions[ minIndex ] += 1; // increment position in arrays where min number was found
		}

		return result.toArray( new Integer[0] );
	}


	public static Integer[] mergeArrays_usingPriorityQueue ( List<int[]> arrays ) {
		return null;
	}

}
