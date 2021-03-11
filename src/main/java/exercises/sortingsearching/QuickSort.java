
package exercises.sortingsearching;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of integers, sort it in ascending order using the Quick Sort algorithm
 *
 * @author emanno
 * @version 1.0 Jan 6, 2018
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] array = new int[]{
        4, 1, 7, -2, 8, 11, -3, 5
    };
    quickSort(array, 0, array.length - 1);
    System.out.println(Arrays.toString(array));
  }


  private static void quickSort(int[] array, int start, int end) {
    if (array == null || array.length == 0) {
      return;
    }

    if (start < end) {
      int index = partition(array, start, end);
      if (start < index - 1) {
        // sort left half
        quickSort(array, start, index - 1);
      }
      if (index < end) {
        // sort right half
        quickSort(array, index, end);
      }
    }
  }

  private static int partition(int[] array, int start, int end) {
    // pick pivot randomly
    int pivotIndex = new Random().ints(start, end + 1).findFirst().getAsInt();
    int pivot = array[pivotIndex];

    while (start <= end) {
      // find element on the left of pivot that should be on the right of pivot
      while (array[start] < pivot) {
        start++;
      }

      // find element on the right of pivot that should be on the left of pivot
      while (array[end] > pivot) {
        end--;
      }

      // swap elements and move indices
      if (start <= end) {
        Utils.swap(array, start, end);
        start++;
        end--;
      }
    }
    return start;
  }

}
