package exercises.sortingsearching;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold
 * B. Write a method to merge B into A in sorted order.
 */
public class SortedMerge {

  public static void main(String[] args) {
    int[] A = new int[]{ 7, 15, 23, 0, 0, 0 };
    int[] B = new int[]{ 10, 12, 18 };
    System.out.println("A before sorted merge:" + Arrays.toString(A));
    sortedMerge(A, B);
    System.out.println("A after sorted merge" + Arrays.toString(A));
  }

  static void sortedMerge(int[] A, int[] B) {
    if ((A == null || A.length == 0) || (B == null || B.length == 0)) {
      return;
    }

    int insertAt = A.length - 1;
    int pointerForB = B.length - 1;
    int pointerForA = A.length - B.length - 1;

    while (insertAt >= 0) {
      if (pointerForA >= 0 && pointerForB >= 0) {
        // we have elems left from both A and B
        int elemA = A[pointerForA];
        int elemB = B[pointerForB];
        if (elemB > elemA) {
          A[insertAt] = elemB;
          pointerForB--;
        } else {
          A[insertAt] = elemA;
          pointerForA--;
        }
      } else if (pointerForA >= 0) {
        // only consider elems from A
        A[insertAt] = A[pointerForA];
        pointerForA--;
      } else {
        // only consider elems from B
        A[insertAt] = B[pointerForB];
        pointerForB--;
      }
      insertAt--;
    }
  }

}
