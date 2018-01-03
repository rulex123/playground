
package exercises.matrix;

/**
 * Find the number of negative numbers in a column-wise / row-wise sorted matrix M[][]. Suppose M has N rows and M
 * columns.
 *
 * @author emanno
 * @version 1.0 Dec 30, 2017
 */
public class CountNegativeNumbers {

	public static void main ( String[] args ) {
		int[][] m = new int[3][4];
		m[ 0 ][ 0 ] = -4;
		m[ 0 ][ 1 ] = -3;
		m[ 0 ][ 2 ] = -2;
		m[ 0 ][ 3 ] = -1;

		m[ 1 ][ 0 ] = -3;
		m[ 1 ][ 1 ] = -2;
		m[ 1 ][ 2 ] = -1;
		m[ 1 ][ 3 ] = 0;

		m[ 2 ][ 0 ] = -2;
		m[ 2 ][ 1 ] = -1;
		m[ 2 ][ 2 ] = 0;
		m[ 2 ][ 3 ] = 1;

		System.out.println( countNegativeNumbers( m ) );
	}


	public static int countNegativeNumbers ( int[][] matrix ) {
		if ( matrix == null || matrix.length == 0 || matrix[ 0 ].length == 0 )
			return 0;

		// start inspecting the element at the top right corner of the matrix
		int count = 0;
		int row = 0;
		int col = matrix[ 0 ].length - 1;

		while ( row < matrix.length && col >= 0 ) {
			if ( matrix[ row ][ col ] < 0 ) {
				count = count + (col + 1);
				row++;
			}
			else {
				col--;
			}
		}

		return count;
	}

}
