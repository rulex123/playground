package interviewcake.math;

import java.math.BigInteger;

/**
 * I have an array of n+1 numbers. Every number in the range 1..n appears once except for
 * one number that appears twice.
 *
 * Write a method for finding the number that appears twice.
 */
public class WhichAppearsTwice {

  public static void main(String[] args) {
    int[] array = new int[]{ 1, 5, 2, 2, 4, 3 };
    System.out.println(whichAppearsTwice(array));
  }

  static int whichAppearsTwice(int[] array) {
    if (array == null || array.length == 0) {
      return -1; // this is an acceptable return value since all numbers in array are >= 1
    }

    // use triangular series formula to compute the sum of all numbers from 1 to n
    int n = array.length - 1;
    int sumWithoutDup = (n * n + n) / 2;

    // go through array and compute sum of all numbers
    int sumWithDup = 0;
    for (int num : array) {
      sumWithDup += num;
    }

    return sumWithDup - sumWithoutDup;
  }

}
