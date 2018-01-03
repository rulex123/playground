
package exercises.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target. You may
 * assume that each input would have exactly one solution, and you may not use the same element twice. <br>
 * Example: <br>
 * Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1] (the order of the
 * indices in the result array doesn't matter)
 *
 * @author emanno
 * @version 1.0 Sep 16, 2017
 */
public class TwoSum {

	public static void main ( String[] args ) {
		int[] indices = twoSum( new int[] {
				2, 11, 15, 7
		}, 9 );
		System.out.println( Arrays.toString( indices ) ); // indices 0 and 3

		indices = twoSum( new int[] {
				3, 4, 1, 2
		}, 10 );
		System.out.println( Arrays.toString( indices ) ); // no solution
	}


	public static int[] twoSum ( int[] nums, int target ) {
		if ( nums == null || nums.length == 0 )
			return null;

		Map<Integer, Integer> numToIndexMap = new HashMap<>();
		for ( int i = 0; i < nums.length; i++ ) {
			int complement = target - nums[ i ];
			if ( numToIndexMap.containsKey( complement ) ) {
				return new int[] {
						i, numToIndexMap.get( complement ) // found the only solution
				};
			}
			else {
				numToIndexMap.put( nums[ i ], i );
			}
		}

		return null; // no solution
	}

}
