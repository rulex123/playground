package exercises.sortingsearching;

import java.util.Arrays;

/**
 * In an array of integers, a "peak" is an element which is greater than or equal to the adjacent
 * integers and a "valley" is an element which is less than or equal to the adjacent integers.
 * For example, in the array {5,8,6,2,3,4,6}, {8,6} are peaks and {5,2} are valleys. Given an
 * array of integers, sort the array into an alternating sequence of peaks and valleys.
 * EXAMPLE
 * Input: {5,3,1,2,3}
 * Output: {5,1,3,2,3}
 */
public class PeaksAndValleys {

    public static void main(String[] args) {
        int[] array = new int[]{7, 1, 5, 4, 2, 6, 3};
        peakAndValleys_optimal(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 2, 2, 2, 2, 3}; // input array with duplicates
        peakAndValleys_optimal(array);
        System.out.println(Arrays.toString(array));
    }

    static void peaksAndValleys(int[] array) {
        if (array == null || array.length <= 2) { // if array contains only 2 numbers, then any
            // ordering will do
            return;
        }

        // sort the input array in ascending order first
        Arrays.sort(array);

        // left half is where we have all our valleys
        // right half is where we have all our peaks
        int midpoint = (array.length - 1) / 2;

        // now weave the peaks and valleys
        int rightHalfPtr = midpoint + 1;
        int leftHalfPtr = 1;
        while (rightHalfPtr < array.length) {
            Utils.swap(array, rightHalfPtr, leftHalfPtr);
            leftHalfPtr += 2;
            rightHalfPtr++;
        }
    }

    static void peakAndValleys_optimal(int[] array) {
        if (array == null || array.length <= 2) { // if array contains only 2 numbers, then any
            // ordering will do
            return;
        }

        for (int i = 1; i < array.length; i += 2) {
            // get the index of the biggest element when considering triplet
            // made up of current element, previous element and next element
            int indexOfMax = indexOfBiggestElem(array, i - 1, i, i + 1);

            if (indexOfMax != i) {
                // swap elements at odd indices to be peaks
                Utils.swap(array, i, indexOfMax);
            }
        }
    }

    private static int indexOfBiggestElem(int[] array, int prevElemIndex, int currElemIndex,
                                          int nextElemIndex) {
        int prevElem = prevElemIndex < array.length ? array[prevElemIndex] : Integer.MIN_VALUE;
        int currElem = currElemIndex < array.length ? array[currElemIndex] : Integer.MIN_VALUE;
        int nextElem = nextElemIndex < array.length ? array[nextElemIndex] : Integer.MIN_VALUE;

        int max = Math.max(prevElem, Math.max(currElem, nextElem));
        if (max == prevElem) {
            return prevElemIndex;
        } else if (max == currElem) {
            return currElemIndex;
        } else {
            return nextElemIndex;
        }
    }

}
