package exercises.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers where each value is a<= x <=len(array), write a function that finds all the duplicates in
 * the array
 *
 * @author emanno
 * @version 1.0 Jun 25, 2017
 */
public class FindDuplicates {

    public static void main(String[] args) {
        // array with dups
        int[] array = new int[]{
                1, 3, 1, 2, 2, 5
        };

        System.out.println(Arrays.toString(findDuplicates(array)));

        // array w/o dups
        array = new int[]{
                1, 2, 3, 4, 5
        };

        System.out.println(Arrays.toString(findDuplicates(array)));

    }


    public static Integer[] findDuplicates(int[] array) {
        Set<Integer> dups = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            int indexToFlip = Math.abs(array[i]) - 1;
            if (array[indexToFlip] < 0) {
                dups.add(Math.abs(array[i]));
            } else {
                array[indexToFlip] = -array[indexToFlip];
            }
        }

        Integer[] result = dups.toArray(new Integer[]{});

        // reset input array to original state
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.abs(array[i]);
        }

        return result;
    }

}
