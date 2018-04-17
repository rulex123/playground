
package exercises.sort;

import java.util.Arrays;

/**
 * Given an array of integers, sort it in ascending order using the Selection Sort algorithm
 *
 * @author emanno
 * @version 1.0 Feb 4, 2018
 */
public class SelectionSort {

	public static void main ( String[] args ) {

		int[] inputArray = new int[] {
				33, 65, 1, 19, 7, 6, 123
		};

		for ( int i = 0; i < inputArray.length - 1; i++ ) {
			int[] minInfo = getMinInfo( inputArray, i + 1 );
			if ( minInfo[ 1 ] < inputArray[ i ] ) {
				Utils.swap( inputArray, i, minInfo[ 0 ] );
			}
		}

		System.out.print( Arrays.toString( inputArray ) );
	}


	public static int[] getMinInfo ( int[] inputArray, int startAt ) {
		int indexOfMin = startAt;
		int min = inputArray[ startAt ];
		for ( int i = startAt; i < inputArray.length; i++ ) {
			if ( inputArray[ i ] < min ) {
				min = inputArray[ i ];
				indexOfMin = i;
			}
		}

		return new int[] {
				indexOfMin, min
		};
	}

}
