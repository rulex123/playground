package exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Remove the duplicates from the input array, and return the element with the highest number of duplicates. <br>
 * Input A=[ 1, 2, 1, 3, 4, 5, 6, 5, 7, 11, 12, 11, 8, 9, 10, 9, 1 ] <br>
 * Output n=1, B=[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ]
 *
 * @author emanno
 * @version 1.0 Dec 22, 2017
 */
public class PaoloExercise {
    public static void main(String[] args) {

        Integer[] input = new Integer[]{
                1, 2, 1, 3, 4, 5, 6, 5, 7, 11, 12, 11, 8, 9, 10, 9, 1
        };

        Object[] result = removeDuplicates(input);
        System.out.println(result[0]);
        System.out.println(Arrays.toString((Integer[]) result[1]));

        input = new Integer[]{
                10, 11, 12, 13, 14, 15
        };

        result = removeDuplicates(input);
        System.out.println(result[0]);
        System.out.println(Arrays.toString((Integer[]) result[1]));

    }


    private static int findIndexOfFirstNonDuplicateElement(int startAt, Integer[] array,
                                                           Map<Integer, Integer> freqTable) {
        for (int i = startAt; i < array.length; i++) {
            int element = array[i];
            if (freqTable.containsKey(element)) {
                freqTable.put(element, freqTable.get(element) + 1);
            } else {
                freqTable.put(element, 1);
                return i;
            }
        }

        return array.length;
    }


    private static Object[] removeDuplicates(Integer[] array) {
        Map<Integer, Integer> freqTable = new HashMap<>();

        int insertAt = 0;
        for (int i = 0; i < array.length; i++) {
            if (freqTable.containsKey(array[i])) {
                freqTable.put(array[i], freqTable.get(array[i]) + 1);
                int toInsert = findIndexOfFirstNonDuplicateElement(i + 1, array, freqTable);

                if (toInsert == array.length) {
                    // fill the rest of the array with nulls
                    for (int j = insertAt; j < array.length; j++) {
                        array[j] = null;
                    }
                } else {
                    // copy element
                    insertAt = (insertAt == 0) ? i : insertAt;
                    array[insertAt] = array[toInsert];
                    insertAt++;
                }
                i = toInsert;
            } else {
                freqTable.put(array[i], 1);
                if (insertAt != 0) {
                    array[insertAt] = array[i];
                    insertAt++;
                }
            }
        }

        // get the element with highest number of duplicates
        int noOfDuplicates = Integer.MIN_VALUE;
        Integer elm = null;
        for (Integer element : freqTable.keySet()) {
            if (freqTable.get(element) > noOfDuplicates) {
                noOfDuplicates = freqTable.get(element);
                elm = element;
            }
        }

        return new Object[]{
                elm, array
        };
    }

}
