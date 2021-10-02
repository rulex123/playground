package exercises.sortingsearching;

import java.util.Arrays;

/**
 * Given an array of integers, sort it in ascending order using the Bubble Sort algorithm
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] inputArray = new int[]{
                33, 65, 1, 19, 7, 6, 123
        };
        bubbleSort(inputArray);
        System.out.print(Arrays.toString(inputArray));
    }

    static void bubbleSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        while (true) {
            int noOfSwaps = 0;
            for (int i = 0; i < array.length - 1; i++) {
                int curr = array[i];
                int next = array[i + 1];

                if (next < curr) {
                    Utils.swap(array, i, i + 1);
                    noOfSwaps++;
                }
            }
            if (noOfSwaps == 0) {
                // we are done!
                break;
            }
        }
    }
}
