package exercises.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of length n such that it contains only 0s and 1s. The task is to divide the
 * array into THREE different non-empty parts such that all of these parts represent the same binary
 * value(in decimals). If it is possible, return any [i, j] with i+1 < j, such that: 1. A[0], A[1],
 * ..., A[i] is the first part. 2. A[i+1], A[i+2], ..., A[j-1] is the second part. 3. A[j], A[j+1], ...,
 * A[n- 1] is the third part. Note: All three parts should have equal binary value. However, If it
 * is not possible, return [-1, -1].
 */
public class BinaryArrayPartition {

    public static void main(String[] args) {
        int[] array = {1, 1, 1, 1, 1, 1};
        int[] array_2 = {1, 0, 0, 1, 0, 1};
        int[] array_3 = {1, 0, 0, 0, 1, 0};
        int[] array_4 = {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1};

        System.out.println(Arrays.toString(binaryArrayPartition(array)));
        System.out.println(Arrays.toString(binaryArrayPartition(array_2)));
        System.out.println(Arrays.toString(binaryArrayPartition(array_3)));
        System.out.println(Arrays.toString(binaryArrayPartition(array_4)));
    }

    private static int[] binaryArrayPartition(int[] array) {
        if (array == null) return new int[]{-1, -1};

        List<Integer> indicesOfBitsSet = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                indicesOfBitsSet.add(i);
            }
        }

        // now we have the number of bits set, as well as their positions in the input array
        if (indicesOfBitsSet.size() % 3 != 0) return new int[]{-1, -1};

        // now we can check each group to verify that bits sets are at same relative positions
        int sizeOfGroup = (indicesOfBitsSet.size() / 3);

        for (int i = 0; i < sizeOfGroup - 1; i++) {
            // i represents the position for group one
            int groupTwoPos = (i + sizeOfGroup * 1);
            int groupThreePos = (i + sizeOfGroup * 2);

            int relativeDistanceOne = indicesOfBitsSet.get(i + 1) - indicesOfBitsSet.get(i);
            int relativeDistanceTwo =
                    indicesOfBitsSet.get(groupTwoPos + 1) - indicesOfBitsSet.get(groupTwoPos);
            int relativeDistanceThree =
                    indicesOfBitsSet.get(groupThreePos + 1) - indicesOfBitsSet.get(groupThreePos);

            if (!(relativeDistanceOne == relativeDistanceTwo
                    && relativeDistanceOne == relativeDistanceThree)) {
                return new int[]{-1, -1};
            }
        }

        int firstPivot = indicesOfBitsSet.get(sizeOfGroup - 1);
        int secondPivot = indicesOfBitsSet.get(sizeOfGroup * 2);
        if (indicesOfBitsSet.get(sizeOfGroup * 2) - indicesOfBitsSet.get(sizeOfGroup * 2 - 1) > 1) {
            secondPivot = indicesOfBitsSet.get(sizeOfGroup * 2 - 1) + 1;
        }

        return new int[]{firstPivot, secondPivot};
    }
}
