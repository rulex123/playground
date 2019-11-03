package exercises.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoinChange {

  /**
   * 1) Given a change, return the optimal number of coins [1,5,10,25] to make the change.
   *
   * <p>2) Given a change and a set of coins, return the optimal number of coins to make the change.
   * Assume that it's always possible to make the given change with the given set of coins.
   *
   * @param args
   */
  public static void main(String[] args) {
    coinChange_optimalNoOfCoins(25);
    System.out.println("========");
    coinChange_optimalNoOfCoins(27);
    System.out.println("========");
    coinChange_optimalNoOfCoins(35);
    System.out.println("========");
    coinChange_optimalNoOfCoins(37);

    System.out.println("********");
    List<Integer> coins = new ArrayList<>();
    coins.add(1);
    coins.add(10);
    coins.add(25);
    coinChange_optimalNoOfCoins(31, coins);

    coins.clear();
    coins.add(1);
    coins.add(5);
    coins.add(25);
    coinChange_optimalNoOfCoins(30, coins);

    System.out.println("********");
    coinChange_optimalNoOfCoins_DP(31, new int[] {1, 10, 25});
    coinChange_optimalNoOfCoins_DP(30, new int[] {1, 5, 25});
  }

  // constant time complexity
  private static void coinChange_optimalNoOfCoins(int change) {
    int[] coins = {25, 10, 5, 1};
    int remainder = change;
    int numberOfCoins = 0;

    for (int i = 0; i < coins.length; i++) {
      int coin = coins[i];
      if (remainder >= coin) {
        numberOfCoins += remainder / coin;
        System.out.println(remainder / coin + " coin(s) of " + coin + " cents");
        remainder = remainder % coin;
      }
    }

    System.out.println("Min number of coins to make " + change + " change is " + numberOfCoins);
  }

  // cannot use the greedy algorithm. consider this example for instance:
  // coinChange_optimalNoOfCoins(31, [1, 10, 25]) = 7 if we used the greedy algorithm (25 + 1 + 1
  // + 1 + 1 + 1 + 1), but instead the optimal number of coins is 4 (10 + 10 + 10 + 1)
  // exponential time complexity
  private static void coinChange_optimalNoOfCoins(int change, List<Integer> availableCoins) {
    List<Integer> combinations = new ArrayList<>();
    coinChange_optimalNoOfCoins(change, availableCoins, 0, combinations);
    System.out.println(combinations);
    System.out.println(
        "Min number of coins to make " + change + " change is " + Collections.min(combinations));
  }

  private static void coinChange_optimalNoOfCoins(
      int change, List<Integer> availableCoins, int coinsUsedSoFar, List<Integer> combinations) {

    if (change == 0) { // finished exploring a valid combination
      combinations.add(coinsUsedSoFar);
      return;
    }

    if (availableCoins.isEmpty()) return; // dead end

    if (availableCoins.get(0) > change) return; // dead end

    // try making change without ith coin
    int removedCoin = availableCoins.remove(0);
    coinChange_optimalNoOfCoins(change, availableCoins, coinsUsedSoFar, combinations);

    // now try making change with the ith coin
    availableCoins.add(0, removedCoin);
    coinChange_optimalNoOfCoins(
        change - removedCoin, availableCoins, coinsUsedSoFar + 1, combinations);
  }

  private static void coinChange_optimalNoOfCoins_DP(int change, int[] availableCoins) {
    int[][] matrix = new int[change + 1][availableCoins.length];

    for (int i = 1; i <= change; i++) {
      for (int j = 0; j < availableCoins.length; j++) {
        if (availableCoins[j] > i) {
          if (j > 0) matrix[i][j] = matrix[i][j - 1];
          continue;
        }

        int remainder = i - availableCoins[j];
        int noOfCoins = 1 + matrix[remainder][j];
        matrix[i][j] = noOfCoins;
      }
    }

    int[] coinsToMakeChange = matrix[change];
    System.out.println(
        "Min number of coins to make "
        + change
        + " change is "
        + Collections.min(
                Arrays.stream(coinsToMakeChange).boxed().collect(Collectors.toList())));
  }
}
