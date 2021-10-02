package exercises.dp;

public class CountCombinations {

    /**
     * Given an array containing integers, compute the number of combinations of integers
     * (combination containing 0 integers is a valid combination)
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(computeNoOfCombinations(array));
    }

    public static int computeNoOfCombinations(int[] array) {
        return computeNoOfCombinations(array, 0);
    }

    private static int computeNoOfCombinations(int[] array, int index) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("given array is null or empty");
        }

        if (index == array.length) {
            return 1;
        }

        int combinationsExcludingCurrent = computeNoOfCombinations(array, index + 1);
        int combinationsIncludingCurrent = computeNoOfCombinations(array, index + 1);
        return combinationsIncludingCurrent + combinationsExcludingCurrent;
    }
}