package exercises.arraysandstrings;

public class MissingItemListDifference {

    /**
     * Given an unsorted array of unique integers (size n + 1) and a second array that is identical to
     * the first array but missing one integer (size n), find and output the missing integer
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arrayA = {23, 5, 1, 34, 127, 7, 18, 2, 26};

        int[] arrayB = {23, 5, 1, 34, 127, 7, 2, 26};
        System.out.println(missingItemListDifference(arrayA, arrayB));

        int[] arrayC = {23, 5, 34, 127, 7, 18, 2, 26};
        System.out.println(missingItemListDifference(arrayA, arrayC));

        int[] arrayD = {23, 5, 1, 34, 7, 18, 2, 26};
        System.out.println(missingItemListDifference(arrayA, arrayD));
    }

    private static int missingItemListDifference(int[] arrayA, int[] arrayB) {
        if ((arrayA == null || arrayA.length == 0) || (arrayB == null || arrayB.length == 0))
            throw new IllegalArgumentException("one or both input arrays are null or empty!");

        if (arrayA.length != arrayB.length + 1)
            throw new IllegalArgumentException(
                    "size of first input array is not n+1 (where n is the "
                            + "size of the second input array)");

        return missingItemListDifference(0, arrayA.length, arrayA, arrayB);
    }

    private static int missingItemListDifference(int from, int to, int[] arrayA, int[] arrayB) {
        if (from == to) return arrayA[from];

        int midPoint = Math.floorDiv(from + to, 2);

        if (arrayA[midPoint] == arrayB[midPoint]) {
            from = midPoint + 1;
            return missingItemListDifference(from, to, arrayA, arrayB);
        } else {
            to = midPoint;
            return missingItemListDifference(from, to, arrayA, arrayB);
        }
    }
}
