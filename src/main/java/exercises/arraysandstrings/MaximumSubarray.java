package exercises.arraysandstrings;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum. <br>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 *
 * @author emanno
 * @version 1.0 Sep 27, 2017
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] array = new int[]{
                -2, 1, -3, 4, -1, 2, 1, -5, 4
        };

        System.out.println(maximumSubarray(array));
    }


    public static int maximumSubarray(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int maxEndingHere = array[0];
        int maxSoFar = array[0];

        for (int i = 1; i < array.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + array[i], array[i]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }

        return maxSoFar;
    }

}
