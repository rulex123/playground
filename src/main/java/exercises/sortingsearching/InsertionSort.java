
package exercises.sortingsearching;

import java.util.Arrays;

/**
 * Given an array of intergers, sort it in ascending order using the Insertion Sort algorithm
 *
 * @author emanno
 * @version 1.0 Feb 4, 2018
 */
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
