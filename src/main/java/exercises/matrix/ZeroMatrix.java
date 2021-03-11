
package exercises.matrix;

/**
 * Write an algorithm such that if an element in an NxM matrix is 0, its entire row and column are set to 0.
 *
 * @author emanno
 * @version 1.0 Aug 10, 2017
 */
public class ZeroMatrix {

	public static void main ( String[] args ) {
		ZeroMatrix unit = new ZeroMatrix();
		int[][] m = new int[3][3];
		m[ 0 ][ 0 ] = 1;
		m[ 0 ][ 1 ] = 2;
		m[ 0 ][ 2 ] = 3;

		m[ 1 ][ 0 ] = 4;
		m[ 1 ][ 1 ] = 5;
		m[ 1 ][ 2 ] = 6;

		m[ 2 ][ 0 ] = 0;
		m[ 2 ][ 1 ] = 8;
		m[ 2 ][ 2 ] = 9;

		RotateMatrix.printMatrix( m );
		unit.zeroMatrix( m );
		System.out.println();
		RotateMatrix.printMatrix( m );
	}

	private void zeroMatrix ( int[][] m ) {
		if (m == null) return;
    if (m.length == 0 || m[0].length == 0) return;

	  boolean[] rows = new boolean[m.length];
		boolean[] columns = new boolean[m[ 0 ].length];

		// compute which columns and rows need to be set to zero
		for ( int i = 0; i < m.length; i++ ) {
			for ( int j = 0; j < m[ i ].length; j++ ) {
				if ( m[ i ][ j ] == 0 ) {
					rows[ i ] = true;
					columns[ j ] = true;
				}
			}
		}

		// set rows and columns to zero
		for ( int i = 0; i < rows.length; i++ ) {
			if ( rows[ i ] ) {
				// switch the whole row to zero
				for ( int j = 0; j < m[ i ].length; j++ ) {
					m[ i ][ j ] = 0;
				}
			}
			else {
				for ( int j = 0; j < m[ i ].length; j++ ) {
					if ( columns[ j ] )
						m[ i ][ j ] = 0;
				}
			}
		}
	}

}
