
package exercises.sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main ( String[] args ) {
		int[] inputArray = new int[] {
				1, 45, 23, 111, 5, 0, -1
		};

		for ( int i = 1; i < inputArray.length; i++ ) {
			Utils.insert( inputArray, inputArray[ i ], i - 1 );
		}

		System.out.print( Arrays.toString( inputArray ) );
	}


}
