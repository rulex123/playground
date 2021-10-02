package exercises.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array, write a function to find any contiguous subarray that sums to zero, if one exists
 *
 * @author emanno
 * @version 1.0 Jun 26, 2017
 */
public class ZeroSumSubarray {

    public static void main(String[] args) {
        int[] subarray = zeroSum(new int[]{
                4, 2, -3, 1, 6
        });
        System.out.println(Arrays.toString(subarray));

        subarray = zeroSum(new int[]{
                4, 2, 0, 1, 6
        });
        System.out.println(Arrays.toString(subarray));

        subarray = zeroSum(new int[]{
                -3, 2, 3, 1, 6
        });
        System.out.println(Arrays.toString(subarray));

        subarray = zeroSum(new int[]{
                -3, 2, 1, 5, 6
        });
        System.out.println(Arrays.toString(subarray));

    }

    public static int[] zeroSum(int[] array) {
        if (array == null || array.length == 0)
            return null;

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];

            // array has an element which is zero
            if (array[i] == 0) {
                return new int[]{
                        0
                };
            }

            // array begins with a bunch of elements that sum to 0
            if (sum == 0) {
                return Arrays.copyOfRange(array, 0, i + 1);
            }

            // subarray that sums to zero somewhere within the original array
            if (sumToIndex.get(sum) != null) {
                return Arrays.copyOfRange(array, sumToIndex.get(sum) + 1, i + 1);
            }

            sumToIndex.put(sum, i);
        }

        return null;
    }

}
