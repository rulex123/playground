package interviewcake.stackandqueue;

import java.util.Stack;

/**
 * I like parentheticals (a lot).
 *
 * "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get
 * confusing."
 *
 * Write a method that, given a sentence like the one above, along with the position of an opening
 * parenthesis, finds the corresponding closing parenthesis.
 *
 * Example: if the example string above is input with the number 10 (position of the first
 * parenthesis), the output should be 79 (position of the last parenthesis).
 */
public class ParenthesisMatching {

  public static void main(String[] args) {
    String sentence =
        "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get "
        + "confusing.";
    System.out.println(parenthesisMatching(sentence, 10));

    sentence = "Sometimes I open a ( and then forget to close it.";
    System.out.println(parenthesisMatching(sentence, 19));
  }

  static int parenthesisMatching(String sentence, int position) {
    if (sentence == null) {
      throw new IllegalArgumentException("invalid sentence");
    }
    if (position < 0 || sentence.length() < position + 1 || sentence.charAt(position) != '(') {
      throw new IllegalArgumentException("invalid position");
    }

    char[] characters = sentence.toCharArray();
    int parenthesis = 0;
    for (int i = position; i < characters.length; i++) {
      char currChar = characters[i];
      if (currChar == '(') {
        parenthesis++;
      } else if (currChar == ')') {
        parenthesis--;
        if (parenthesis == 0) {
          return i;
        }
      }
    }

    // there is no closing parenthesis!
    return -1;
  }
}
