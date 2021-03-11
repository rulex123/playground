package interviewcake.hashtables;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an efficient method that checks whether any permutation of an input string is a
 * palindrome.
 *
 * You can assume the input string only contains lowercase letters.
 *
 * Examples:
 *
 * "civic" should return true
 * "ivicc" should return true
 * "civil" should return false
 * "livci" should return false
 */
public class PermutationPalindrome {

  public static void main(String[] args) {
    System.out.println(permutationPalindrome("civic"));
    System.out.println(permutationPalindrome("ivicc"));
    System.out.println(permutationPalindrome("civil"));
    System.out.println(permutationPalindrome("livci"));
  }

  static boolean permutationPalindrome(String string) {
    if (string == null || string.isEmpty()) {
      return false;
    }

    Set<Character> unpairedChars = new HashSet<>();
    for (int i = 0; i < string.length(); i++) {
      char currChar = string.charAt(i);
      if (unpairedChars.contains(currChar)) {
        unpairedChars.remove(currChar);
      } else {
        unpairedChars.add(currChar);
      }
    }

    return unpairedChars.size() < 2 ? true : false;
  }
}
