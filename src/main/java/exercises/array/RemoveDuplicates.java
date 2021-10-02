package exercises.array;

import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
 * length. Do not allocate extra space for another array, you must do this in place with constant memory. <br>
 * For example, given input array nums = [1,1,2], your function should return length = 2, with the first two elements of
 * nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 *
 * @author emanno
 * @version 1.0 Sep 19, 2017
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] array = new int[]{
                1, 1, 2, 3, 3, 4, 5, 6, 6, 7
        };
        System.out.println(removeDuplicates(array));
        System.out.println(Arrays.toString(array));
    }


    public static int removeDuplicates(int[] array) {
        if (array == null)
            throw new NullPointerException("input array is null");

        if (array.length == 0)
            return 0;

        int insertAt = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[insertAt] != array[i]) {
                insertAt++;
                array[insertAt] = array[i];
            }
        }

        return insertAt + 1;
    }
}
