package exercises.bitmanipulation;

import java.util.Arrays;

/**
 * Given an array containing all the numbers from 1 to n except two, find the two missing numbers
 * <p>
 * e.g. [4, 2, 3] = 1, 5
 */
public class TwoMissingNumbers {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoMissingNumbers(new int[]{
                3, 1, 2
        })));
        System.out.println(Arrays.toString(twoMissingNumbers(new int[]{
                3, 5, 2
        })));
    }

    static int[] twoMissingNumbers(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }

        // we can derive the value of n
        int n = array.length + 2;
        int sumAllNumbers = n * (n + 1) / 2;

        int actualSum = 0; // potential overflow, might want to switch to long or BigInteger
        for (int i = 0; i < array.length; i++) {
            actualSum += array[i];
        }

        int diffInSums = sumAllNumbers - actualSum;
        int pivot = diffInSums / 2;

        // because the array contains distinct numbers from 1 to n, we know for sure that the 2
        // missing numbers that sum up to the value of diffInSum MUST be either less or greater than
        // (diffInSum/2): if they were equal to (diffInSum/2), then the assumption that the array
        // contains distinct value would not hold true. So once we find a "pivot" around which we
        // group the numbers in the array, we know that one of the missing numbers will be in the
        // left group, and the other in the right group.

        // NOTE: we use bitwise XOR instead of the math formula x*(x+1)/2 to avoid potential problems
        // with big numbers and overflow
        int xorLeft = 0;
        for (int i = 1; i <= pivot; i++) {
            xorLeft ^= i;
        }

        int xorRight = 0;
        for (int i = pivot + 1; i <= n; i++) {
            xorRight ^= i;
        }

        for (int number : array) {
            if (number <= pivot) {
                xorLeft ^= number;
            } else {
                xorRight ^= number;
            }
        }

        return new int[]{xorRight, xorLeft};
    }

}
