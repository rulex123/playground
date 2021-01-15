package exercises.sortingsearching;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of times, write
 * code to find an element in the array. You may assume that the array was originally sorted in
 * increasing order.
 * EXAMPLE
 * Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * Output: 8 (the index of 5 in the array)
 */
public class SearchInRotatedArray {

  public static void main(String[] args) {
    int[] array = new int[]{ 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
    System.out.println(searchInRotatedArray(array, 5));
    System.out.println(searchInRotatedArray(array, 10));
    System.out.println(searchInRotatedArray(array, 15));
    System.out.println(searchInRotatedArray(array, 20));
    System.out.println(searchInRotatedArray(array, 45));

    System.out.println("------");

    array = new int[]{ 2, 2, 2, 3, 4, 2 };
    System.out.println(searchInRotatedArray(array, 4));
    System.out.println(searchInRotatedArray(array, 2));
    System.out.println(searchInRotatedArray(array, 3));

  }

  static int searchInRotatedArray(int[] array, int elem) {
    if (array == null || array.length == 0) {
      return -1;
    }

    return searchInRotatedArray(array, elem, 0, array.length - 1);
  }

  static int searchInRotatedArray(int[] array, int elem, int startIndex, int endIndex) {
    if (startIndex > endIndex) {
      // element is not in the array!
      return -1;
    }

    int midIndex = (startIndex + endIndex) / 2;
    int midElem = array[midIndex];
    if (midElem == elem) {
      // found it!
      return midIndex;
    }

    // decide which half of the array to recurse over
    int startElem = array[startIndex];
    int endElem = array[endIndex];

    if (startElem < midElem) { // left half is ordered
      if (isWithinRange(elem, startElem, midElem)) {
        return searchInRotatedArray(array, elem, startIndex, midIndex - 1); // search left half
      } else {
        return searchInRotatedArray(array, elem, midIndex + 1, endIndex); // search right half
      }
    } else if (midElem < endElem) { // right half is ordered
      if (isWithinRange(elem, midElem, endElem)) {
        return searchInRotatedArray(array, elem, midIndex + 1, endIndex); // search right half
      } else {
        return searchInRotatedArray(array, elem, startIndex, midIndex - 1); // search left half
      }
    } else { // there are repeats
      if (startElem == midElem) {
        if (midElem != endElem) { // left half is all repeats, search in right half
          return searchInRotatedArray(array, elem, midIndex + 1, endIndex); // search right half
        } else {
          // search both halves
          int index =
              searchInRotatedArray(array, elem, startIndex, midIndex - 1); // search left half
          if (index == -1) {
            return searchInRotatedArray(array, elem, midIndex + 1, endIndex); // search right half
          } else {
            return index;
          }
        }
      }
    }

    return -1;
  }

  private static boolean isWithinRange(int elem, int startElem,
                                       int endElem) {
    if (elem >= startElem && elem <= endElem) {
      return true;
    }
    return false;
  }

}
