package interviewcake.greedy;

import java.util.Arrays;
import java.util.Random;

/**
 * Write a method for doing an in-place shuffle of an array.
 * <p>
 * The shuffle must be "uniform," meaning each item in the original array must have the same
 * probability of ending up in each spot in the final array.
 * <p>
 * Assume that you have a method getRandom(floor, ceiling) for getting a random integer that is >=
 * floor and <= ceiling.
 * <p>
 * NOTE: see https://medium.com/@oldwestaction/randomness-is-hard-e085decbcbb2
 */
public class ShuffleInPlace {

    static Random random = new Random();

    public static void main(String[] args) {
        int[] input = new int[]{23, 99, 102, 2, 34, 6, 18, 278, 1, 3, 159, 8};
        shuffleInPlace(input);
        System.out.println(Arrays.toString(input));
    }

    static void shuffleInPlace(int[] input) {
        if (input == null || input.length == 0) {
            return;
        }

        for (int i = 0; i < input.length - 1; i++) {
            int randomIndex = getRandom(i, input.length - 1);
            if (randomIndex != i) {
                int tmp = input[i];
                input[i] = input[randomIndex];
                input[randomIndex] = tmp;
            }
        }
    }

    static int getRandom(int floor, int ceiling) {
        int numbersInRange = ceiling - floor + 1;
        // bound is exclusive
        return random.nextInt(numbersInRange) + floor;
    }
}
