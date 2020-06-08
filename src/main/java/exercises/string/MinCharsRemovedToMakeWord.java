package exercises.string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinCharsRemovedToMakeWord {

  /**
   * Given a string and a dictionary HashSet, write a function to determine the minimum number of
   * characters to delete to make a word.
   *
   * For example, if input string is 'abc' and the dictionary is ['aa','aaa','a'], then the
   * function returns 2.
   *
   * NOTE: there are 2 versions of this problem. In the 1st, the words in the dictionary are
   * not available. Only the hash of each word is available, so the only thing we can do is to
   * ask if a specific word is contained in the dictionary. In the 2nd, the words in the
   * dictionary are available.
   */
  public static void main(String[] args) {
    HashSet<String> dictionary = new HashSet<>();
    dictionary.add("aaa");
    dictionary.add("aa");
    dictionary.add("a");

    System.out.println(minCharsRemovedToMakeWord_1st_BFS("abc", dictionary));
    System.out.println(minCharsRemovedToMakeWord_2nd("abc", dictionary));

    System.out.println("------------");

    dictionary.clear();
    dictionary.add("fine");
    dictionary.add("fame");
    dictionary.add("fin");

    System.out.println(minCharsRemovedToMakeWord_1st_BFS("famine", dictionary));
    System.out.println(minCharsRemovedToMakeWord_2nd("famine", dictionary));

    System.out.println("------------");

    dictionary.clear();
    dictionary.add("petal");
    dictionary.add("pet");
    dictionary.add("roof");

    System.out.println(minCharsRemovedToMakeWord_1st_BFS("petals", dictionary));
    System.out.println(minCharsRemovedToMakeWord_2nd("petals", dictionary));

    System.out.println("------------");

    dictionary.clear();
    dictionary.add("more");
    dictionary.add("angry");
    dictionary.add("by");
    dictionary.add("the");
    dictionary.add("minute");

    System.out.println(minCharsRemovedToMakeWord_1st_BFS("argh", dictionary));
    System.out.println(minCharsRemovedToMakeWord_2nd("argh", dictionary));

  }

  // words in dictionary are not available
  // brute force: this has exponential time complexity
  private static int minCharsRemovedToMakeWord_1st(String original, String partial,
                                                   HashSet<String> dictionary,
                                                   int index) {
    if (index == original.length()) {
      // we are at the end of a branch, do a lookup in dictionary
      if (dictionary.contains(partial)) {
        return original.length() - partial.length();
      } else {
        return -1;
      }
    }
    // look at next character, and consider both options (present or absent)
    char character = original.charAt(index);
    int charsRemovedIfAbsent =
        minCharsRemovedToMakeWord_1st(original, partial, dictionary, index + 1);
    int charsRemovedIfPresent =
        minCharsRemovedToMakeWord_1st(original, partial + character, dictionary, index + 1);

    if (charsRemovedIfAbsent != -1 && charsRemovedIfPresent != -1) {
      return Math.min(charsRemovedIfAbsent, charsRemovedIfPresent);
    } else {
      return Math.max(charsRemovedIfAbsent, charsRemovedIfPresent);
    }
  }

  // creates a tree like structure and uses BFS with some caching to avoid duplicate work
  private static int minCharsRemovedToMakeWord_1st_BFS(String original,
                                                       HashSet<String> dictionary) {
    Queue<String> possibleWords = new LinkedList<>();
    Set<String> wordsAlreadyEnqueued = new HashSet<>();

    possibleWords.add(original);
    wordsAlreadyEnqueued.add(original);

    while (!possibleWords.isEmpty()) {
      String word = possibleWords.remove();
      wordsAlreadyEnqueued.remove(word);

      // first, lookup the word in the dictionary
      if (dictionary.contains(word)) {
        return original.length() - word.length();
      }

      // otherwise, compute next words and add them to queue
      for (int i = 0; i < word.length(); i++) {
        String newWord = word.substring(0, i) + word.substring(i + 1);
        if (!wordsAlreadyEnqueued.contains(newWord) && !newWord.isEmpty()) {
          possibleWords.add(newWord);
          wordsAlreadyEnqueued.add(newWord);
        }
      }
    }

    return -1;
  }

  // words in dictionary are available
  private static int minCharsRemovedToMakeWord_2nd(String original,
                                                   HashSet<String> dictionary) {
    int minDiffInLength = Integer.MAX_VALUE;
    for (String word : dictionary) {
      if (isSubstringOf(word, original)) {
        minDiffInLength = Math.min(minDiffInLength, original.length() - word.length());
      }
    }

    if (minDiffInLength != Integer.MAX_VALUE) {
      return minDiffInLength;
    }

    return -1;
  }

  /**
   * Returns true if word is a substring of original
   */
  private static boolean isSubstringOf(final String word, final String original) {
    if ((word == null || word.isEmpty()) || (original == null || original.isEmpty())) {
      throw new IllegalArgumentException("one or both string(s) are null/empty");
    }

    if (word.length() > original.length()) {
      return false;
    }

    int index_1 = 0;
    int index_2 = 0;
    while (index_1 < word.length() && index_2 < original.length()) {
      if (word.charAt(index_1) == original.charAt(index_2)) {
        if (index_1 == word.length() - 1) { // are we at the end of word?
          return true;
        } else {
          index_1++;
          index_2++;
        }
      } else {
        index_2++;
      }
    }

    return false;
  }

}
