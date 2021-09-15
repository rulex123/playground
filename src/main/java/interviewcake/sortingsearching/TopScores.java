package interviewcake.sortingsearching;

import java.util.Arrays;

/**
 * You created a game that is more popular than Angry Birds.
 *
 * Each round, players receive a score between 0 and 100, which you use to rank them from highest
 * to
 * lowest. So far you're using an algorithm that sorts in O(nlogn) time, but players are
 * complaining that their rankings aren't updated fast enough. You need a faster sorting algorithm.
 *
 * Write a method that takes:
 *
 * an array of unsortedScores
 * the highestPossibleScore in the game
 * and returns a sorted array of scores in less than O(nlogn) time.
 *
 * We're defining n as the number of unsortedScores because we're expecting the number of players
 * to keep climbing.
 *
 * And, we'll treat highestPossibleScore as a constant instead of factoring it into our big O time
 * and space costs because the highest possible score isn't going to change. Even if we do redesign
 * the game a little, the scores will stay around the same order of magnitude.
 */
public class TopScores {

  public static void main(String[] args) {
    int[] unsortedScores = { 91, 37, 89, 41, 37, 53, 65, 91, 53 };
    final int HIGHEST_POSSIBLE_SCORE = 100;
    int[] sortedScores = topScores(unsortedScores, HIGHEST_POSSIBLE_SCORE);

    System.out.println(Arrays.toString(sortedScores));
  }

  static int[] topScores(int[] scores, int highestScore) {
    if (scores == null || scores.length == 0) {
      return new int[]{};
    }

    int[] scoreFrequencies = new int[highestScore];
    for (int i = 0; i < scores.length; i++) {
      int currScore = scores[i];
      scoreFrequencies[currScore] = scoreFrequencies[currScore] + 1;
    }

    int[] sortedScores = new int[scores.length];// NOTE: we could instead insert into the input
    // array to save on extra space
    int insertAt = 0;
    for (int i = 0; i < scoreFrequencies.length; i++) {
      int currFreq = scoreFrequencies[i];
      if (currFreq == 0) {
        continue;
      }
      for (int j = 0; j < currFreq; j++) {
        sortedScores[insertAt] = i;
        insertAt++;
      }
    }
    return sortedScores;
  }
}
