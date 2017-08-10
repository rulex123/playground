
package exercises.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given a non-empty array of integers, return the k most frequent elements. For example, given [1,1,1,2,2,3] and k=2,
 * return [1,2].
 *
 * @author emanno
 * @version 1.0 Aug 8, 2017
 */
public class TopKFrequentElements {

	public static void main ( String[] args ) {
		int[] array = new int[] {
				1, 1, 1, 4, 4, 3, 3, 3, 3, 7, 8, 8
		};
		System.out.println( topKFrequentElements( array, 3 ) );
		System.out.println( topKFrequentElements( array, 7 ) );
	}


	public static List<Integer> topKFrequentElements ( int[] array, int k ) {
		Map<Integer, Integer> freqTable = buildFrequencyTable( array );

		// it's pointless to carry out rest of computation in case there aren't enough distinct numbers in the input array
		if ( k >= freqTable.keySet().size() ) {
			return new ArrayList<>( freqTable.keySet() );
		}

		int maxFreq = getMaxFrequency( freqTable );

		// build buckets to hold numbers with same frequency
		List<Integer>[] buckets = new List[maxFreq];
		for ( Entry<Integer, Integer> entry : freqTable.entrySet() ) {
			int index = entry.getValue() - 1;
			if ( buckets[ index ] == null ) {
				buckets[ index ] = new ArrayList<>();
			}
			buckets[ index ].add( entry.getKey() );
		}

		// gather k most frequent numbers
		List<Integer> result = new ArrayList<>();
		for ( int i = buckets.length - 1; i >= 0 && result.size() < k; i-- ) {
			if ( buckets[ i ] != null ) {
				result.addAll( buckets[ i ] );
			}
		}
		return result;
	}


	private static int getMaxFrequency ( Map<Integer, Integer> freqTable ) {
		int maxFreq = 0;
		for ( Integer freq : freqTable.values() ) {
			if ( freq > maxFreq ) {
				maxFreq = freq;
			}
		}
		return maxFreq;
	}


	private static Map<Integer, Integer> buildFrequencyTable ( int[] array ) {
		Map<Integer, Integer> freqTable = new HashMap<Integer, Integer>();
		for ( int num : array ) {
			if ( freqTable.containsKey( num ) ) {
				freqTable.put( num, freqTable.get( num ) + 1 );
			}
			else {
				freqTable.put( num, 1 );
			}
		}
		return freqTable;
	}

}
