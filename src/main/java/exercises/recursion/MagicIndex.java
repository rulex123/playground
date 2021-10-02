package exercises.recursion;

public class MagicIndex {

    public static void main(String[] args) {

        int[] array = new int[8];
        array[0] = -13;
        array[1] = -7;
        array[2] = -2;
        array[3] = 1;
        array[4] = 3;
        array[5] = 5;
        array[6] = 17;
        array[7] = 34;

        System.out.println(magicIndex(array, 0, array.length - 1));

        array = new int[5];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 4;
        array[4] = 5;

        System.out.println(magicIndex(array, 0, array.length - 1));
    }

    private static int magicIndex(int[] array, int from, int to) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("invalid input array!");
        }

        if (from >= to) {
            return -1; // no magic index!
        }

        int midIndex = (from + to) / 2;
        int valueAtMidpoint = array[midIndex];
        if (valueAtMidpoint == midIndex) {
            return midIndex; // found magic index!
        }

        if (valueAtMidpoint > midIndex) {
            return magicIndex(array, from, midIndex - 1);
        } else {
            return magicIndex(array, midIndex + 1, to);
        }
    }
}
