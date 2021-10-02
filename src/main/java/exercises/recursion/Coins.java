package exercises.recursion;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), write code
 * to calculate the number of ways of representing n cents
 *
 * @author emanno
 * @version 1.0 Jun 23, 2017
 */
public class Coins {

    public static void main(String[] args) {
        int[] denominations = new int[]{
                1, 5, 10, 25
        };
        System.out.println(computeChange(15, denominations));

        denominations = new int[]{
                5, 1, 2
        };
        System.out.println(computeChange(6, denominations));

    }


    public static int computeChange(int cents, int[] denominations) {
        // use a bottom up approach to build 2D matrix containing ways in which we can give change for all numbers between 1
        // and cents
        int[][] ways = new int[denominations.length][cents + 1];

        for (int i = 0; i < denominations.length; i++) {
            for (int j = 1; j <= cents; j++) {
                int coinDenomination = denominations[i];

                if (j - coinDenomination < 0) {
                    if (i == 0) { // we are on the first row of the matrix, so we can't rely on ways to give change using
                        // previous denominations
                        ways[i][j] = 0;
                    } else {
                        ways[i][j] = ways[i - 1][j]; // we can rely on ways to give change using
                        // previous denominations
                    }
                } else {
                    int above = (i == 0) ? 0 : ways[i - 1][j]; // how many ways are there to give change WITHOUT the current
                    // denomination?
                    int left = (j - coinDenomination == 0) ? 1 : ways[i][(j - coinDenomination)]; // how many ways are there
                    // to give change for the
                    // remainder?
                    ways[i][j] = left + above;
                }
            }
        }

        return ways[denominations.length - 1][cents];
    }

}
