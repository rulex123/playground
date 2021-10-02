package exercises.sortingsearching;

import java.util.Arrays;

/**
 * Given an array of integers, sort it in ascending order using the Merge Sort algorithm
 *
 * @author emanno
 * @version 1.0 Jan 7, 2018
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{
                21, 20, 2, 1, 4, 5, 7, 9, 30
        };
        mergeSort(array);
        System.out.println(Arrays.toString(array));


        array = new int[]{3, 24, 1, 12, 78, -1, -5, 123};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }


    private static void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }


    private static void mergeSort(int[] array, int[] temp, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        // recursively merge each half
        mergeSort(array, temp, start, mid);
        mergeSort(array, temp, mid + 1, end);
        // merge the two sorted halves
        mergeHalves(array, temp, start, end);
    }


    private static void mergeHalves(int[] array, int[] temp, int start, int end) {
        int mid = (start + end) / 2;
        int i = start;
        int j = mid + 1;
        int insertAt = start;
        // copy from both sides for as long as we can
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                temp[insertAt] = array[i];
                i++;
            } else {
                temp[insertAt] = array[j];
                j++;
            }
            insertAt++;
        }

        // copy from left half if anything is left over
        while (i <= mid) {
            temp[insertAt] = array[i];
            insertAt++;
            i++;
        }

        // copy from right half if anything is left over
        while (j <= end) {
            temp[insertAt] = array[j];
            insertAt++;
            j++;
        }

        // copy from temp array back into input array
        insertAt = start;
        for (int k = start; k <= end; k++) {
            array[insertAt] = temp[k];
            insertAt++;
        }
    }

}
