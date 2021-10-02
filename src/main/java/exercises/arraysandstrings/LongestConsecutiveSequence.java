package exercises.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array, find the length of the longest sequence of consecutive numbers in the array. Example:
 * [4,2,1,6,5]=3 [4,5,6]
 * [5,5,3,1]=1 [5],[3] or [1]
 *
 * @author emanno
 * @version 1.0 Jun 26, 2017
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] array1 = new int[]{
                4, 2, 1, 6, 5
        };
        int[] array2 = new int[]{
                5, 5, 3, 1
        };

        System.out.println(lengthOfLongestConsecutiveSequence(array1));
        System.out.println(lengthOfLongestConsecutiveSequence(array2));

    }


    public static int lengthOfLongestConsecutiveSequence(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        // build set of numbers in input array, so that we can look them
        // up in constant time
        Set<Integer> numbers = new HashSet<>();
        for (int i : array) {
            numbers.add(i);
        }

        int maxLength = 0;
        for (int n : numbers) {
            if (numbers.contains(n - 1))
                // skip to next number, as either we've seen this sequence already,
                // or we are going to see it later on
                continue;

            int length = 0;
            while (numbers.contains(n++))
                length++;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

}
