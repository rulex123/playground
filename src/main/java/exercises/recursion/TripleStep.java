
package exercises.recursion;

public class TripleStep {

	public static void main ( String[] args ) {
		System.out.println( countWays( 4 ) );

	}


	static int countWays ( int n ) {
		if ( n < 0 ) {
			return 0;
		}
		else if ( n == 0 ) {
			return 1;
		}
		else {
			return countWays( n - 1 ) + countWays( n - 2 ) + countWays( n - 3 );
		}

	}
}

// 0,1,2,3,4,5,6,7,8
