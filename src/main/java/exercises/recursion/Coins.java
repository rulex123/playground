
package exercises.recursion;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), write code
 * to calculate the number of ways of representing n cents
 *
 * @author emanno
 * @version 1.0 Jun 23, 2017
 */
public class Coins {

	public static void main ( String[] args ) {
		System.out.println( computeChange( 15 ) );
	}


	public static int computeChange ( int cents ) {
		int[] denominations = new int[] {
				25, 10, 5, 1
		};
		return computeChange( cents, denominations, 0 );
	}


	public static int computeChange ( int cents, int[] denominations, int index ) {
		if ( index >= denominations.length - 1 ) {
			return 1; // base case
		}

		int ways = 0;
		int denom = denominations[ index ];
		for ( int i = 0; denom * i <= cents; i++ ) {
			int amountsRemaining = cents - (denom * i);
			ways += computeChange( amountsRemaining, denominations, index + 1 );
		}
		return ways;
	}

}
