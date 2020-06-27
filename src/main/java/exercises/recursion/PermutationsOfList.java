package exercises.recursion;

import exercises.sort.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsOfList {

  /**
   * Write a function that returns all permutations of a given list.
   *
   * f({1,2,3}) = {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}}
   */
  public static void main(String[] args) {
    int[] input = new int[]{ 1, 2, 3 };
    final List<int[]> result = permutationsOfList(input);
    result.stream().forEach(p -> System.out.println(Arrays.toString(p)));
  }

  private static List<int[]> permutationsOfList(int[] input) {
    List<int[]> result = new ArrayList<>();
    if (input == null || input.length == 0) {
      return result;
    }
    permutationsOfList(input, 0, result);
    return result;
  }

  private static void permutationsOfList(int[] input, int startAt,
                                         List<int[]> result) {
    if (startAt == input.length) {
      result.add(input.clone());
      return;
    }

    for (int i = startAt; i < input.length; i++) {
      Utils.swap(input, i, startAt);
      permutationsOfList(input, startAt + 1, result);
      Utils.swap(input, i, startAt);
    }
  }

}
