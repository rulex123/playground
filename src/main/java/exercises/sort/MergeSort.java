
package exercises.sort;

import java.util.Arrays;

/**
 * Given an array of integers, sort it in ascending order using the Merge Sort algorithm
 *
 * @author emanno
 * @version 1.0 Jan 7, 2018
 */
public class MergeSort {

	public static void main ( String[] args ) {
		int[] array = new int[] {
				21, 20, 2, 1, 4, 5, 7, 9, 30
		};
		mergeSort( array );
		System.out.println( Arrays.toString( array ) );
	}


	private static void mergeSort ( int[] array ) {
		int[] temp = new int[array.length];
		mergeSort( array, temp, 0, array.length - 1 );
	}


	private static void mergeSort ( int[] array, int[] temp, int start, int end ) {
		if ( start == end ) {
			return;
		}

		int mid = (start + end) / 2;
		mergeSort( array, temp, start, mid );
		mergeSort( array, temp, mid + 1, end );
		mergeHalves( array, temp, start, end );

	}


	private static void mergeHalves ( int[] array, int[] temp, int start, int end ) {
		int mid = (start + end) / 2;
		int i = start;
		int j = mid + 1;
		int insertAt = start;
		while ( i <= mid && j <= end ) {
			if ( array[ i ] < array[ j ] ) {
				temp[ insertAt ] = array[ i ];
				i++;
			}
			else {
				temp[ insertAt ] = array[ j ];
				j++;
			}
			insertAt++;
		}
		System.arraycopy( array, i, temp, insertAt, mid + 1 - i );
		System.arraycopy( array, j, temp, insertAt, end + 1 - j );
		System.arraycopy( temp, start, array, start, end - start + 1 );
	}

}
