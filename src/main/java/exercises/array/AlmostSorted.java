package exercises.array;

import exercises.sortingsearching.Utils;

/**
 * Given an array with elements, can you sort this array in ascending order using only one of the following operations?
 * <li>Swap two elements.</li>
 * <li>Reverse one sub-segment.</li>
 *
 * @author emanno
 * @version 1.0 Jan 23, 2018
 */
public class AlmostSorted {

    public static void main(String[] args) {
        almostSorted(new int[]{
                2, 3, 5, 1, 7, 8, 9, 4
        });

        almostSorted(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8
        });

        almostSorted(new int[]{
                1, 2, 3, 5, 4, 6, 7
        });

        almostSorted(new int[]{
                3, 1, 2
        });

        almostSorted(new int[]{
                1, 2, 6, 5, 4, 3
        });

        almostSorted(new int[]{
                6, 5, 4, 3, 2, 1
        });

        almostSorted(new int[]{
                1, 5, 4, 3, 2, 6
        });

        almostSorted(new int[]{
                1, 2, 3, 7, 6, 5, 4, 8, 9
        });

    }


    private static void almostSorted(int[] array) {
        int indexOfFirstElToSwap = findIndexOfFirstElementOutOfOrder(array);

        if (indexOfFirstElToSwap != -1) {
            int indexOfSecondElToSwap = findIndexOfMinElementStartingAt(array, indexOfFirstElToSwap + 1);

            Utils.swap(array, indexOfFirstElToSwap, indexOfSecondElToSwap);

            if (findIndexOfFirstElementOutOfOrder(array) > -1) {
                Utils.swap(array, indexOfSecondElToSwap, indexOfFirstElToSwap); // revert change in array

                if (!checkIfReversingSubarrayMakesArraySorted(array)) {
                    System.out.println("no");
                }

            } else {
                System.out.println(String.format("swap %d %d", indexOfFirstElToSwap + 1, indexOfSecondElToSwap + 1)); // 1
                // swap
                // is
                // sufficient
                // to
                // make
                // array
                // sorted
            }
        } else {
            System.out.println("yes"); // the array is already sorted, yay! :)
        }
    }


    private static boolean checkIfReversingSubarrayMakesArraySorted(int[] array) {
        int i = 0;
        while (i < array.length - 1 && array[i] < array[i + 1]) {
            i++;
        }

        int j = i; // at this point i will be the start of the second segment
        while (j < array.length - 1 && array[j] > array[j + 1]) {
            j++;
        }

        // check if the whole array is in reverse order
        if (i == 0 && j == array.length - 1) {
            printReverseMessage(i, j);
            return true;
        }

        // check if the array is made of a first ascending segment, and a second descending segment
        if (j == array.length - 1) {
            return checkSeam(array, i, j);
        }

        int k = j + 1; // at this point j will point to the end of second segment
        while (k < array.length - 1 && array[k] < array[k + 1]) {
            k++;
        }

        // array is made of 3 segments, but is the last segment fully sorted?
        if (k != array.length - 1) {
            return false;
        }

        return checkSeams(array, i, j);
    }


    private static void printReverseMessage(int i, int j) {
        System.out.println("yes");
        System.out.println(String.format("reverse %d %d", i + 1, j + 1));
    }


    private static boolean checkSeam(int[] array, int lowIndex, int highIndex) {
        int lowEl = lowIndex > 0 ? array[lowIndex - 1] : array[lowIndex];
        if (lowEl < array[highIndex]) {
            printReverseMessage(lowIndex, highIndex);
            return true;
        }
        return false;
    }


    private static boolean checkSeams(int[] array, int lowIndex, int highIndex) {
        int lowEl = lowIndex > 0 ? array[lowIndex - 1] : array[lowIndex];
        if (array[highIndex] > lowEl && array[lowIndex] < array[highIndex + 1]) {
            printReverseMessage(lowIndex, highIndex);
            return true;
        }
        return false;
    }


    private static int findIndexOfFirstElementOutOfOrder(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return i; // return index of element out of place
            }
        }
        return -1; // used to indicate that there is no element out of order (i.e. array is sorted)
    }


    private static int findIndexOfMinElementStartingAt(int[] array, int startFromIndex) {
        int min = array[startFromIndex];
        int indexOfMin = startFromIndex;
        for (int i = startFromIndex + 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

}
