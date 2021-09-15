
package exercises.misc;

import java.util.Arrays;

/**
 * Given an integer representing a given amount of change, write a function to compute the total number of coins
 * required to make that amount of change. You can assume that there is always a 1 cent coin. <br>
 * eg. (assuming American coins: 1, 5, 10, and 25 cents)<br>
 * makeChange(1) = 1 (1) <br>
 * makeChange(6) = 2 (5 + 1) <br>
 * makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
 *
 * @author emanno
 * @version 1.0 Aug 17, 2017
 */
public class MakingChange {

	public static void main ( String[] args ) {
		System.out.println( "min number of coins is " + makeChange( 12, new int[] {
				10, 6, 1
		} ) );
	}


	public static int makeChange ( int change, int[] denominations ) {
		// array to track min number of coins to make up all numbers between 1..change
		int[] minCoinsCounts = new int[change + 1];

		// array to track coins used to make up all numbers between 1..change
		int[] coinsUsed = new int[change + 1];
		Arrays.fill( coinsUsed, -1 );

		for ( int i = 1; i <= change; i++ ) {
			int minCoinsCount = Integer.MAX_VALUE;
			int coinUsed = -1;

			for ( int j = 0; j < denominations.length; j++ ) {
				if ( i - denominations[ j ] >= 0 ) {
					int coinsCount = minCoinsCounts[ i - denominations[ j ] ] + 1;
					if ( coinsCount < minCoinsCount ) {
						minCoinsCount = coinsCount;
						coinUsed = j;
					}
				}
			}
			// return -1 in case the change cannot be made up using the available denominations
			minCoinsCounts[ i ] = minCoinsCount == Integer.MAX_VALUE ? -1 : minCoinsCount;
			coinsUsed[ i ] = coinUsed;
		}

		printCoinsUsed( change, denominations, coinsUsed );
		return minCoinsCounts[ change ];
	}


	private static void printCoinsUsed ( int change, int[] denominations, int[] coinsUsed ) {
		if ( coinsUsed[ coinsUsed.length - 1 ] == -1 ) {
			System.out.println( "no solution" );
		}

		while ( change > 0 ) {
			System.out.println( denominations[ coinsUsed[ change ] ] );
			change = change - denominations[ coinsUsed[ change ] ];
		}
	}

}
