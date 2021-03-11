package interviewcake.arrays;

import exercises.sortingsearching.Utils;

/**
 * You're working on a secret team solving coded transmissions.
 *
 * Your team is scrambling to decipher a recent message, worried it's a plot to break into a major
 * European National Cake Vault. The message has been mostly deciphered, but all the words are
 * backward! Your colleagues have handed off the last step to you.
 *
 * Write a method reverseWords() that takes a message as an array of characters and reverses the
 * order of the words in place.
 */
public class ReverseWords {

  public static void main(String[] args) {
    char[] message = new char[]{ 'c', 'a', 'k', 'e', ' ',
                                 'p', 'o', 'u', 'n', 'd', ' ',
                                 's', 't', 'e', 'a', 'l' };
    reverseWords(message);
    System.out.println(new String(message));
  }

  static void reverseWords(char[] message) {
    if (message == null || message.length == 0) {
      return;
    }

    // first, reverse the whole message
    reverse(message, 0, message.length -1);

    int from = 0;
    int to = 0;
    while (to < message.length) {
      if (to == ' ') {
        // we are at the end of a word, so reverse it
        reverse(message, from, to - 1);
        // reset from index
        from = to + 1;
      }
      to++;
    }

    // reverse last word
    reverse(message, from, to -1);
  }

  private static void reverse(char[] characters, int from, int to) {
    for (int i = from, j = to; i < j; i++, j--) {
      // swap
      char tmp = characters[j];
      characters[j] = characters[i];
      characters[i] = tmp;
    }

  }
}
