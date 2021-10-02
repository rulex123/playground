package exercises.dp;

public class CountCombinationsWithCache {

    /**
     * Given an array containing integers, compute the number of combinations of integers
     * (combination containing 0 integers is a valid combination)
     */
    public static void main(String[] args) {
        int[] array = PerformanceMeasure.generateRandomArray(50);
        System.out.println(computeNoOfCombinations(array));
    }

    public static long computeNoOfCombinations(int[] array) {
        long[] cache = new long[array.length];
        return computeNoOfCombinations(array, 0, cache);
    }

    private static long computeNoOfCombinations(int[] array, int index, long[] cache) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("given array is null or empty");
        }

        // base case
        if (index == array.length) {
            return 1;
        }

        if (cache[index] == 0) {
            // check the cache to see if we already computed the number of
            // combinations starting from this place
            long combinationsExcludingCurrent = computeNoOfCombinations(array, index + 1, cache);
            long combinationsIncludingCurrent = computeNoOfCombinations(array, index + 1, cache);
            cache[index] = combinationsIncludingCurrent + combinationsExcludingCurrent;
        }
        return cache[index];
    }
}