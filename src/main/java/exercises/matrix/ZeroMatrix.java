
package exercises.matrix;

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

		m[ 2 ][ 0 ] = 7;
		m[ 2 ][ 1 ] = 8;
		m[ 2 ][ 2 ] = 0;

		unit.setZeroes( m );
	}


	private void setZeroes ( int[][] m ) {
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

		RotateMatrix.printMatrix( m );

		// switch to zero
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

		System.out.println();

		RotateMatrix.printMatrix( m );

	}

}
