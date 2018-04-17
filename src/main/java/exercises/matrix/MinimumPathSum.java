
package exercises.matrix;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the
 * sum of all numbers along its path. Note: You can only move either down or right at any point in time.
 *
 * @author emanno
 * @version 1.0 Sep 30, 2017
 */
public class MinimumPathSum {

	public static void main ( String[] args ) {

		int[][] m = new int[3][3];
		m[ 0 ][ 0 ] = 1;
		m[ 0 ][ 1 ] = 2;
		m[ 0 ][ 2 ] = 3;

		m[ 1 ][ 0 ] = 4;
		m[ 1 ][ 1 ] = 5;
		m[ 1 ][ 2 ] = 6;

		m[ 2 ][ 0 ] = 7;
		m[ 2 ][ 1 ] = 8;
		m[ 2 ][ 2 ] = 9;

		System.out.println( minimumPathSum( m ) );

	}


	public static int minimumPathSum ( int[][] grid ) {
		if ( grid == null || (grid[ 0 ] == null || grid[ 0 ].length == 0) )
			throw new IllegalArgumentException( "invalid grid" );

		int[][] minSumMatrix = new int[grid.length][grid[ 0 ].length];

		// fill 1st row
		minSumMatrix[ 0 ][ 0 ] = grid[ 0 ][ 0 ];
		for ( int i = 1; i < grid[ 0 ].length; i++ ) {
			minSumMatrix[ 0 ][ i ] = grid[ 0 ][ i ] + minSumMatrix[ 0 ][ i - 1 ];
		}

		// fill 1st column
		for ( int i = 1; i < grid.length; i++ ) {
			minSumMatrix[ i ][ 0 ] = grid[ i ][ 0 ] + minSumMatrix[ i - 1 ][ 0 ];
		}

		// fill the rest of the matrix
		for ( int i = 1; i < minSumMatrix.length; i++ ) {
			for ( int j = 1; j < minSumMatrix[ 0 ].length; j++ ) {
				int minPathSum = grid[ i ][ j ] + Math.min( minSumMatrix[ i - 1 ][ j ], minSumMatrix[ i ][ j - 1 ] );
				minSumMatrix[ i ][ j ] = minPathSum;
			}
		}

		return minSumMatrix[ minSumMatrix.length - 1 ][ minSumMatrix[ 0 ].length - 1 ];
	}


}
