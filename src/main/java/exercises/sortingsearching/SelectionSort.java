
package exercises.sortingsearching;

import java.util.Arrays;

/**
 * Given an array of integers, sort it in ascending order using the Selection Sort algorithm
 *
 * @author emanno
 * @version 1.0 Feb 4, 2018
 */
public class SelectionSort {

  public static void main(String[] args) {

    int[] inputArray = new int[]{
        33, 65, 1, 19, 7, 6, 123
    };
		selectionSort(inputArray);
    System.out.print(Arrays.toString(inputArray));
  }

  static void selectionSort(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    for (int i = 0; i < array.length - 1; i++) {
      int[] minInfo = getMinInfo(array, i + 1);
      int minIndex = minInfo[0];
      int min = minInfo[1];
      if (min < array[i]) {
        Utils.swap(array, i, minIndex);
      }
    }

  }

  static int[] getMinInfo(int[] inputArray, int startAt) {
    int indexOfMin = startAt;
    int min = inputArray[startAt];
    for (int i = startAt; i < inputArray.length; i++) {
      if (inputArray[i] < min) {
        min = inputArray[i];
        indexOfMin = i;
      }
    }

    return new int[]{
        indexOfMin, min
    };
  }

}
