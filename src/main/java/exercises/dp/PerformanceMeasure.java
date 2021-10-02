package exercises.dp;

import java.util.Random;

public class PerformanceMeasure {

    private static final int NO_OF_RUNS = 10;

    public static void main(String[] args) {
        int[] inputArray = generateRandomArray(30);
        runAndComputeMeanExecution(() -> CountCombinations.computeNoOfCombinations(inputArray));
        runAndComputeMeanExecution(
                () -> CountCombinationsWithCache.computeNoOfCombinations(inputArray));
    }


    private static void runAndComputeMeanExecution(Runnable runnable) {
        long totalExecTime = 0;
        for (int i = 0; i < NO_OF_RUNS; i++) {
            System.out.println("Starting execution no. " + (i + 1));
            long start = System.nanoTime();
            runnable.run();
            long end = System.nanoTime();
            totalExecTime += (end - start);
        }

        long meanExecTime = totalExecTime / NO_OF_RUNS;

        System.out.println("Mean execution time over 10 runs is " + meanExecTime + " nano seconds");
    }


    public static int[] generateRandomArray(int size) {
        System.out.println("Generating random array");
        final int[] array = new Random().ints(size, Integer.MIN_VALUE, Integer.MAX_VALUE).toArray();
        System.out.println("Random array generated");
        return array;
    }

}
