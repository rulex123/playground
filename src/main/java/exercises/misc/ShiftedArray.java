
package exercises.misc;

/**
 * A sorted array of distinct integers shiftArr is shifted to the left by an unknown offset and you don’t have a
 * pre-shifted copy of it. For instance, the sequence 1, 2, 3, 4, 5 becomes 3, 4, 5, 1, 2, after shifting it twice to
 * the left. Given shiftArr and an integer num, implement a function shiftedArrSearch that finds and returns the index
 * of num in shiftArr. If num isn’t in shiftArr, return -1. Assume that the offset doesn’t equal to 0 (i.e. assume the
 * array is shifted at least once) or to arr.length - 1 (i.e. assume the shifted array isn’t fully reversed). Explain
 * your solution and analyze its time and space complexities.
 *
 * @author emanno
 * @version 1.0 Aug 7, 2017
 */
public class ShiftedArray {

	public static void main ( String[] args ) {
		System.out.println( searchArray( new int[] {
				4, 7, 9, 13, 0, 2
		}, 4 ) );
		System.out.println( searchArray( new int[] {
				4, 7, 9, 13, 0, 2
		}, 13 ) );
		System.out.println( searchArray( new int[] {
				4, 7, 9, 13, 0, 2
		}, 9 ) );
		System.out.println( searchArray( new int[] {
				4, 7, 9, 13, 0, 2
		}, 16 ) );

	}


	private static int searchArray ( int[] shiftedArray, int num ) {
		int start = 0;
		int end = shiftedArray.length - 1;
		int indexOfPivot = -1;

		while ( true ) {
			indexOfPivot = (indexOfPivot == -1) ? findIndexOfSmallestElement( shiftedArray ) : (start + end) / 2;
			if ( shiftedArray[ indexOfPivot ] == num ) {
				return indexOfPivot;
			}
			else {
				if ( num >= shiftedArray[ indexOfPivot + 1 ] && num <= shiftedArray[ end ] ) {
					start = indexOfPivot + 1;
				}
				else if ( num >= shiftedArray[ start ] && num <= shiftedArray[ indexOfPivot - 1 ] ) {
					end = indexOfPivot - 1;
				}
				else {
					return -1;
				}
			}
		}
	}


	private static int findIndexOfSmallestElement ( int[] shiftedArray ) {
		int start = 0;
		int end = shiftedArray.length - 1;

		while ( start <= end ) {
			int mid = (start + end) / 2;
			if ( shiftedArray[ mid ] < shiftedArray[ mid - 1 ] ) {
				return mid;
			}
			if ( shiftedArray[ mid ] > shiftedArray[ start ] ) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}

		throw new RuntimeException( "something went wrong!" );
	}

}
