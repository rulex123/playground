
package exercises.array;

/**
 * Given an array of positive number, find maximum sum subarray such that elements in this subarray are not adjacent to
 * each other.
 *
 * @author emanno
 * @version 1.0 Oct 1, 2017
 */
public class MaximumSubarrayOfNonAdjacentElements {

	public static void main ( String[] args ) {
		int[] array = new int[] {
				4, 1, 1, 4, 2, 1
		};
		System.out.println( maxSubarrayOfNonAdjacentElements( array ) );
	}


	public static int maxSubarrayOfNonAdjacentElements ( int[] array ) {
		if ( array == null || array.length == 0 )
			return 0;

		// what is the best we can do (i.e. max sum) till here if the current number were to be included
		int bestIfInclusive = array[ 0 ];

		// what is the best we can do (i.e. max sum) till here if the current number were to be excluded
		int bestIfExclusive = 0;

		int temp = 0;
		for ( int i = 1; i < array.length; i++ ) {
			temp = bestIfInclusive;
			bestIfInclusive = Math.max( array[ i ] + bestIfExclusive, bestIfInclusive );
			bestIfExclusive = temp;
		}

		return Math.max( bestIfInclusive, bestIfExclusive );
	}

	// i = 1, bestIfInclusive = 4, temp = 4, bestIfInclusive = max(1 + 0, 4), bestIfExclusive = 4 
	// i = 2, temp = 4, bestIfInclusive =  
	
	
}
