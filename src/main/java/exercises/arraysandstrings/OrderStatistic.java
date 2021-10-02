package exercises.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an unsorted array (no duplicates, positive integers) and an index k, return the kth
 * smallest number in the input array
 */
public class OrderStatistic {

    public static void main(String[] args) {
        int[] array = {6, 9, 1, 35, 7, 2, 14, 0}; // sorted: 1, 2, 6, 7, 9, 14

        System.out.println(orderStatistic(array, 1));
        System.out.println(orderStatistic(array, 2));
        System.out.println(orderStatistic(array, 3));
        System.out.println(orderStatistic(array, 4));
        System.out.println(orderStatistic(array, 5));
        System.out.println(orderStatistic(array, 6));
        System.out.println(orderStatistic(array, 7));
        System.out.println(orderStatistic(array, 8));

        System.out.println("========");

        System.out.println(orderStatisticLinear(array, 1));
        System.out.println(orderStatisticLinear(array, 2));
        System.out.println(orderStatisticLinear(array, 3));
        System.out.println(orderStatisticLinear(array, 4));
        System.out.println(orderStatisticLinear(array, 5));
        System.out.println(orderStatisticLinear(array, 6));
        System.out.println(orderStatisticLinear(array, 7));
        System.out.println(orderStatisticLinear(array, 8));
    }

    private static int orderStatistic(int[] array, int k) {
        if (checkInput(array, k) < 0) return -1;

        // O(n^2) solution first
        List<Integer> mins = new ArrayList<>(k);
        for (int i = 0; i < k; i++) { // iterate k times
            mins.add(array[0]);

            for (int j = 0; j < array.length; j++) { // iterate over all input array
                if (i == 0) { // we are finding absolute min in input array
                    if (array[j] < mins.get(0)) {
                        mins.set(0, array[j]);
                    }
                } else { // we need to take into account the mins that we already saw
                    if (array[j] <= mins.get(i - 1)) {
                        continue; // skip mins that we already saw
                    } else {
                        if (mins.get(i) <= mins.get(i - 1)) {
                            // reset value used for comparison to first number bigger than last min
                            mins.set(i, array[j]);
                        }

                        if (array[j] < mins.get(i)) {
                            mins.set(i, array[j]);
                        }
                    }
                }
            }
        }

        return mins.get(k - 1);
    }

    private static int orderStatisticLinear(int[] array, int k) {
        if (checkInput(array, k) < 0) return -1;

        int[] partialSort = new int[array.length];
        int startIndex = 0;
        int endIndex = array.length - 1;
        int compare = array[0]; // pick first number in array

        for (int i = 1; i < array.length; i++) {
            if (array[i] < compare) {
                partialSort[startIndex] = array[i];
                startIndex++;
            } else {
                partialSort[endIndex] = array[i];
                endIndex--;
            }
        }

        // now startIndex tells us the position of the number we picked, relative to every other number
        // in the array
        partialSort[startIndex] = compare;

        // now we can look at half of the array, based on where k is relative to startIndex
        if (startIndex == k - 1) {
            return partialSort[startIndex];
        } else if (startIndex < k - 1) { // look at right side
            return orderStatisticLinear(
                    Arrays.copyOfRange(partialSort, startIndex + 1, partialSort.length),
                    (k - 1) - startIndex);
        } else { // look at left side
            return orderStatisticLinear(Arrays.copyOfRange(partialSort, 0, startIndex), k);
        }
    }

    private static int checkInput(int[] array, int k) {
        if (k < 1 | k > array.length) {
            return -1;
        }

        if (array == null || array.length == 0) {
            return -1;
        }

        return 0;
    }
}
