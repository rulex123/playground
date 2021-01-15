
package exercises.sortingsearching;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of integers, sort it in ascending order using the Quick Sort algorithm
 *
 * @author emanno
 * @version 1.0 Jan 6, 2018
 */
public class QuickSort {

	public static void main ( String[] args ) {
		int[] array = new int[] {
				4, 1, 7, -2, 8, 11, -3, 5
		};
		quickSort( array, 0, 7 );
		System.out.println( Arrays.toString( array ) );
	}


	private static void quickSort ( int[] array, int start, int end ) {
		if ( start < end ) {
			int index = partition( array, start, end );
			quickSort( array, start, index - 1 );
			quickSort( array, index + 1, end );
		}
	}


	private static int partition ( int[] array, int start, int end ) {
		int pivotIndex = new Random().ints( start, end + 1 ).findFirst().getAsInt();
		Utils.swap( array, pivotIndex, end );

		int pivot = array[ end ];
		int partitionIndex = start;
		for ( int i = start; i < end; i++ ) {
			if ( array[ i ] <= pivot ) {
				Utils.swap( array, i, partitionIndex );
				partitionIndex++;
			}
		}
		Utils.swap( array, partitionIndex, end );
		return partitionIndex;
	}

}
