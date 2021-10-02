package interviewcake.math;

import java.util.Random;

/**
 * You have a method rand5() that generates a random integer from 1 to 5. Use it to write a method
 * rand7() that generates a random integer from 1 to 7.
 * <p>
 * rand5() returns each integer with equal probability. rand7() must also return each integer with
 * equal probability.
 */
public class Simulate7SidedDie {

    private static final Random rnd = new Random();
    private static final double REPETITIONS = 100000;

    public static void main(String[] args) {
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;
        int seven = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            int roll = simulate7SidedDie();
            switch (roll) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;
                case 7:
                    seven++;
                    break;
            }
        }

        System.out.println("one (%):" + (one / REPETITIONS) * 100);
        System.out.println("two (%):" + (two / REPETITIONS) * 100);
        System.out.println("three (%):" + (three / REPETITIONS) * 100);
        System.out.println("four (%):" + (four / REPETITIONS) * 100);
        System.out.println("five (%):" + (five / REPETITIONS) * 100);
        System.out.println("six (%):" + (six / REPETITIONS) * 100);
        System.out.println("seven (%):" + (seven / REPETITIONS) * 100);
    }

    static int simulate7SidedDie() {

        while (true) {
            int firstRoll = rand5();
            int secondRoll = rand5();

            // map combination of firstRoll and secondRoll to a number between 1 and 25
            // (which represent all the possible outcomes from rolling the 5 sided die twice)
            int mapped = ((firstRoll % 5) * 5) + ((secondRoll % 5) + 1);
            // we only keep 21 of the possible 25 outcomes, since we need a number of outcomes
            // divisible by 7
            if (mapped <= 21) {
                // derive a number between 1 and 7 from mappedOutcome
                if (mapped % 7 == 0) {
                    return 7;
                } else {
                    return mapped % 7;
                }
            }
        }
    }

    private static int rand5() {
        return rnd.nextInt(5) + 1;
    }
}
