package exercises.recursion;

import java.util.Arrays;

public class TripleStep {

    public static void main(String[] args) {
        int noOfSteps = 16;
        int[] cache = new int[noOfSteps + 1];
        Arrays.fill(cache, -1);
        System.out.println(countWays(noOfSteps, cache));
    }


    static int countWays(int n, int[] cache) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            if (cache[n] != -1) {
                return cache[n];
            } else {
                cache[n] = countWays(n - 1, cache) + countWays(n - 2, cache) + countWays(n - 3,
                        cache);
                return cache[n];
            }
        }
    }
}
