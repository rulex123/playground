package exercises.array;

import java.util.Arrays;

/**
 * Given 2 sorted arrays, A and B, where A is long enough to hold the contents of A and B, write a function to copy the
 * contents of B into A without using any buffer or additional memory.
 *
 * @author emanno
 * @version 1.0 Sep 4, 2017
 */
public class MergeSortedArraysWithoutBuffer {

    public static void main(String[] args) {
        int[] arrayA = new int[]{
                1, 3, 5, 7, 0, 0, 0
        };
        int[] arrayB = new int[]{
                2, 4, 6
        };

        mergeArrays(arrayA, arrayB, 4);
        System.out.println(Arrays.toString(arrayA));

    }


    public static void mergeArrays(int[] arrayA, int[] arrayB, int arrayARealLenght) {
        if (arrayA == null)
            throw new NullPointerException("container array is null");

        if (arrayB == null)
            return;

        if (arrayARealLenght + arrayB.length != arrayA.length)
            throw new IllegalArgumentException("container array has incorrect size");

        int indexB = arrayB.length - 1;
        int indexA = arrayARealLenght - 1;
        int insertAtIndex = arrayA.length - 1;

        while (indexA >= 0 && indexB >= 0) {
            if (arrayB[indexB] >= arrayA[indexA]) {
                arrayA[insertAtIndex] = arrayB[indexB];
                indexB--;
            } else {
                arrayA[insertAtIndex] = arrayA[indexA];
                indexA--;
            }
            insertAtIndex--;
        }

        while (indexB >= 0) {
            arrayA[insertAtIndex] = arrayB[indexB];
            indexB--;
            insertAtIndex--;
        }
    }

}
