
package exercises.matrix;

/**
 * Given an NxM array where all rows and columns are in sorted order, write a function to determine whether the array
 * contains an element x 
 * 
 * [  0  1  2  3 ]
 * [  4  5  6  7 ]
 * [  8  9 10 11 ] 
 * [ 12 13 14 15 ]
 *
 * @author emanno
 * @version 1.0 Jun 25, 2017
 */
public class MatrixSearch {

	public static void main ( String[] args ) {
		int[][] m = new int[4][4];
		m[ 0 ][ 0 ] = 0;
		m[ 0 ][ 1 ] = 1;
		m[ 0 ][ 2 ] = 2;
		m[ 0 ][ 3 ] = 3;

		m[ 1 ][ 0 ] = 4;
		m[ 1 ][ 1 ] = 5;
		m[ 1 ][ 2 ] = 6;
		m[ 1 ][ 3 ] = 7;

		m[ 2 ][ 0 ] = 8;
		m[ 2 ][ 1 ] = 9;
		m[ 2 ][ 2 ] = 10;
		m[ 2 ][ 3 ] = 11;

		m[ 3 ][ 0 ] = 12;
		m[ 3 ][ 1 ] = 13;
		m[ 3 ][ 2 ] = 14;
		m[ 3 ][ 3 ] = 15;

		System.out.println( doesMatrixContain( m, 5 ) );
	}


	public static boolean doesMatrixContain ( int[][] matrix, int elementToSearch ) {
		if ( matrix == null || matrix.length == 0 || matrix[ 0 ].length == 0 )
			return false;

		// start inspecting the element at the top right corner of the matrix
		int row = 0;
		int col = matrix[ 0 ].length - 1;

		while ( row < matrix.length && col >= 0 ) {
			if ( matrix[ row ][ col ] == elementToSearch )
				return true;
			if ( matrix[ row ][ col ] < elementToSearch )
				row++; // skip to next line in matrix
			else
				col--; // skip to next column in matrix
		}

		return false;
	}

}
