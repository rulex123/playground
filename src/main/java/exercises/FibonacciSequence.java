
package exercises;

/**
 * Given an integer n, write a function to compute the nth Fibonacci number.
 */
public class FibonacciSequence {

  // 0, 1, 1, 2, 3, 5, 8, 13, 21 etc
  public static void main(String[] args) {
    FibonacciSequence unit = new FibonacciSequence();
    int nthNumber = 9;
    System.out.println(unit.getFibonacciNumberUsingRecursion(nthNumber));
    System.out.println(unit.getFibonacciNumber(nthNumber));

  }


  private int getFibonacciNumberUsingRecursion(int nthNumber) {
    if (nthNumber == 1) {
      return 0;
    } else if (nthNumber == 2) {
      return 1;
    } else {
      int minusOne = nthNumber - 1;
      int minusTwo = nthNumber - 2;
      return this.getFibonacciNumberUsingRecursion(minusOne) + this
          .getFibonacciNumberUsingRecursion(minusTwo);
    }
  }


  private int getFibonacciNumber(int nthNumber) {
    if (nthNumber == 1) {
      return 0;
    } else if (nthNumber == 2) {
      return 1;
    }

    int minusTwo = 0;
    int minusOne = 1;
    int next = 0;
    for (int i = 3; i <= nthNumber; i++) {
      next = minusTwo + minusOne;
      minusTwo = minusOne;
      minusOne = next;
    }

    return next;
  }

}
