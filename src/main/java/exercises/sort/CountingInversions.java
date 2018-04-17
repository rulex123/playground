
package exercises.sort;

/**
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already
 * sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum. Formally
 * speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j <br>
 * Example: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
 *
 * @author emanno
 * @version 1.0 Jan 7, 2018
 */
public class CountingInversions {

	public static void main ( String[] args ) {
		int[] array = new int[] {
				1, 1, 1, 2, 2
		};
		System.out.println( countInversions( array ) ); // expected 0

		array = new int[] {
				2, 1, 3, 1, 2
		};
		System.out.println( countInversions( array ) ); // expected 4
	}


	private static long countInversions ( int[] array ) {
		int[] temp = new int[array.length];
		return countInversions( array, temp, 0, array.length - 1 );
	}


	private static long countInversions ( int[] array, int[] temp, int start, int end ) {
		if ( start == end ) {
			return 0;
		}

		long count = 0;
		int mid = (start + end) / 2;
		count += countInversions( array, temp, start, mid );
		count += countInversions( array, temp, mid + 1, end );
		count += mergeHalves( array, temp, start, end );

		return count;
	}


	private static long mergeHalves ( int[] array, int[] temp, int start, int end ) {
		long count = 0;
		int mid = (start + end) / 2;
		int i = start;
		int j = mid + 1;
		int insertAt = start;
		while ( i <= mid && j <= end ) {
			if ( array[ i ] <= array[ j ] ) {
				temp[ insertAt ] = array[ i ];
				i++;
			}
			else {
				temp[ insertAt ] = array[ j ];
				j++;
				count += mid + 1 - i;
			}
			insertAt++;
		}
		System.arraycopy( array, i, temp, insertAt, mid + 1 - i );
		System.arraycopy( array, j, temp, insertAt, end + 1 - j );
		System.arraycopy( temp, start, array, start, end - start + 1 );

		return count;
	}

}
