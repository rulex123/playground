package exercises.array;

/**
 * Write a function that returns the median of two sorted arrays. The arrays might be of
 * different sizes, and the algorithm used must have logarithmic runtime complexity.
 */
public class MedianOf2SortedArrays {

  public static void main(String[] args) {
    int[] arr1 = new int[]{ 1, 2, 4, 12 };
    int[] arr2 = new int[]{ 3, 5, 7, 9, 11, 13, 15, 17, 19, 21 };
    System.out.println(median(arr1, arr2));

    arr1 = new int[]{ 1, 3, 7 };
    arr2 = new int[]{ 4, 10, 12 };
    System.out.println(median(arr1, arr2));
  }

  private static double median(int[] arr1, int[] arr2) {
    if ((arr1 == null || arr1.length == 0) || (arr2 == null || arr2.length == 0)) {
      throw new IllegalArgumentException("Please provide 2 non-empty arrays as input!");
    }

    int[] shortArray = arr1.length < arr2.length ? arr1 : arr2;
    int[] longArray = arr1.length < arr2.length ? arr2 : arr1;

    int start = 0;
    int end = shortArray.length;

    while (start <= end) {
      // pick a partition point in shortest array
      int partition1 = (start + end) / 2;
      // calculate a partition in longer array, so that the two partitions would form 2 halves of
      // the overall array
      int partition2 = (shortArray.length + longArray.length + 1) / 2 - partition1;

      // get the 4 numbers situated around the partition points
      int leftArr1 = partition1 > 0 ? shortArray[partition1 - 1] : Integer.MIN_VALUE;
      int rightArr1 =
          (partition1 == shortArray.length) ? Integer.MAX_VALUE : shortArray[partition1];
      int leftArr2 = partition2 > 0 ? longArray[partition2 - 1] : Integer.MIN_VALUE;
      int rightArr2 = (partition2 == arr2.length) ? Integer.MAX_VALUE : longArray[partition2];

      if (leftArr1 <= rightArr2 && rightArr1 >= leftArr2) {
        // found the correct partition!
        if ((shortArray.length + longArray.length) % 2 == 0) {
          return (Math.max(leftArr1, leftArr2) + Math.min(rightArr1, rightArr2)) / 2.0;
        } else {
          return Math.max(leftArr1, leftArr2);
        }
      } else if (rightArr1 < leftArr2) {
        // move partition in shorter array to the right
        start = partition1 + 1;
      } else {
        // move partition in shorter array to the left
        end = partition1 - 1;
      }
    }

    throw new IllegalStateException("Something has gone wrong!");
  }


}
