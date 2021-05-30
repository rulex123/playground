package interviewcake.math;

import java.util.Random;

/**
 * You have a method rand7() that generates a random integer from 1 to 7. Use it to write a method
 * rand5() that generates a random integer from 1 to 5.
 *
 * rand7() returns each integer with equal probability. rand5() must also return each integer with
 * equal probability.
 */
public class Simulate5SidedDie {

  private static final Random rnd = new Random();

  public static void main(String[] args) {
    System.out.println(simulate5SidedDie());
    System.out.println(simulate5SidedDie());
    System.out.println(simulate5SidedDie());
    System.out.println(simulate5SidedDie());
  }

  // each number between 1 and 5 has the same probability (i.e. 1/7) of being returned when rolling
  // the 7 sided die, so we simply re-roll if we get a number that is greater than 5
  static int simulate5SidedDie() {
    while (true) {
      int rndNum = rand7();

      if (rndNum <= 5) {
        return rndNum;
      }
    }
  }

  private static int rand7() {
    return rnd.nextInt(7) + 1;
  }

}
