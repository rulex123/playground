
package exercises.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in
 * the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights
 * associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the
 * maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot
 * break an item, either pick the complete item, or don't pick it (0-1 property).
 *
 * @author emanno
 * @version 1.0 Apr 18, 2018
 */
public class IntegerKnapsackProblem {

	public static void main ( String[] args ) {
		List<Item> items = Arrays.asList( new Item( 1, 6 ), new Item( 2, 10 ), new Item( 3, 12 ) );
		System.out.println(topDownKnapsack( items.toArray( new Item[0] ), 5 ));
	}


	// Recursively check every combination of items by traversing list of items
	// and either including or excluding each item
	public static int naiveKnapsack ( Item[] items, int W ) {
		return naiveKnapsack( items, W, 0 );
	}


	// Overloaded recursive function for naiveKnapsack
	private static int naiveKnapsack ( Item[] items, int W, int i ) {
		// Return when we reach the end of the list
		if ( i == items.length )
			return 0;

		// If item is heavier than remaining weight, skip item
		if ( W - items[ i ].weight < 0 )
			return naiveKnapsack( items, W, i + 1 );

		// Try both including and excluding the current item
		return Math.max( naiveKnapsack( items, W - items[ i ].weight, i + 1 ) + items[ i ].value,
				naiveKnapsack( items, W, i + 1 ) );
	}


	// Recursive solution that uses a cache to improve performance
	public static int topDownKnapsack ( Item[] items, int W ) {
		// Map: i -> W -> value
		// Use a map instead of array because the data could be very sparse
		Map<Integer, Map<Integer, Integer>> cache = new HashMap<Integer, Map<Integer, Integer>>();
		return topDownKnapsack( items, W, 0, cache );
	}


	// Overloaded recursive function for topDownKnapsack
	private static int topDownKnapsack ( Item[] items, int W, int i, Map<Integer, Map<Integer, Integer>> cache ) {
		// Return when we reach the end of the list
		if ( i == items.length )
			return 0;

		// Check the cache and return value if we get a hit
		if ( !cache.containsKey( i ) )
			cache.put( i, new HashMap<Integer, Integer>() );
		Integer cached = cache.get( i ).get( W );
		if ( cached != null ) {
			return cached;
		}

		// If item is heavier than remaining weight, skip item
		if ( W - items[ i ].weight < 0 )
			return topDownKnapsack( items, W, i + 1, cache );

		// Try both including and excluding the current item
		int toReturn = Math.max( topDownKnapsack( items, W - items[ i ].weight, i + 1, cache ) + items[ i ].value,
				topDownKnapsack( items, W, i + 1, cache ) );
		cache.get( i ).put( W, toReturn );
		return toReturn;
	}


	// Iterative dynamic programming solution
	public static int bottomUpKnapsack ( Item[] items, int W ) {
		// cache[i][j] = max value for the first i items with a max weight of j
		int[][] cache = new int[items.length + 1][W + 1];
		for ( int i = 1; i <= items.length; i++ ) {
			for ( int j = 0; j <= W; j++ ) {
				// If including item[i-1] would exceed max weight j, don't
				// include the item. Otherwise take the max value of including
				// or excluding the item
				if ( items[ i - 1 ].weight > j )
					cache[ i ][ j ] = cache[ i - 1 ][ j ];
				else
					cache[ i ][ j ] =
							Math.max( cache[ i - 1 ][ j ], cache[ i - 1 ][ j - items[ i - 1 ].weight ] + items[ i - 1 ].value );
			}
		}

		return cache[ items.length ][ W ];
	}

	// Item class
	public static class Item {
		int weight;
		int value;


		public Item ( int weight, int value ) {
			this.weight = weight;
			this.value = value;
		}

	}
}