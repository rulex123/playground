package exercises.sortingsearching;

/**
 * You are given an array-like data structure Listy which lacks a size method. It does, however,
 * have an elementAt(i) method that returns the element at index i in O(1) time. If i is beyond
 * the bounds of the data structure, it returns -1. (For this reason, the data structure only
 * supports positive integers). Given a Listy which contains sorted, positive integers, find the
 * index at which an element x occurs. If x occurs multiple times, you may return any index.
 */
public class SortedSearchNoSize {

  public static void main(String[] args) {
    Listy listy = new Listy(1, 2, 4, 5);

  }

  static int sortedSearchNoSize(Listy listy, int elem) {
    if (listy == null) {
      throw new IllegalArgumentException("invalid listy");
    }
    if (elem < 0) {
      throw new IllegalArgumentException("invalid elem");
    }

    int floor = 0;
    int ceiling = 2;
    int outOfBounds = -1;

    while (floor <= ceiling) {
      int floorElem = listy.elementAt(floor);
      int ceilingElem = listy.elementAt(ceiling);
      int mid = (floor + ceiling) / 2;

      if (ceilingElem != -1) {
        // we are within bounds
        if (elem >= floorElem && elem <= ceilingElem) {
          // we are in the right segment of listy
          int midElem = listy.elementAt(mid);
          if (midElem == elem) {
            return mid;
          } else if (elem < midElem) {
            ceiling = mid - 1;
            continue;
          } else {
            floor = mid + 1;
            continue;
          }
        } else if (floor == 0 && elem < floorElem) {
          return -1;
        } else {
          // keep looking for the right segment, paying attention to whether we already detected
          // a limit in ceiling
          if (outOfBounds != -1) {
            ceiling = outOfBounds;
          } else {
            floor = ceiling;
            ceiling = ceiling * 2;
          }
          continue;
        }
      } else {
        // we are out of bounds, adjust ceiling to midway between floor and ceiling
        outOfBounds = ceiling; // save out of bounds index for later
        ceiling = (floor + ceiling) / 2; // readjust ceiling value
      }
    }

    // element not found!
    return -1;
  }

  static class Listy {

    private final int[] array;

    Listy(int... items) {
      array = new int[items.length];
      for (int i = 0; i < items.length; i++) {
        if (items[i] < 0) {
          throw new IllegalArgumentException("negative numbers are not allowed!");
        }
        array[i] = items[i];
      }
    }

    int elementAt(int index) {
      if (index < 0 || index > array.length - 1) {
        return -1;
      }
      return array[index];
    }
  }
}
