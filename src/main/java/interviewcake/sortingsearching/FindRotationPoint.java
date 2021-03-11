package interviewcake.sortingsearching;

/**
 * I opened up a dictionary to a page in the middle and started flipping through, looking for words
 * I didn't know. I put each word I didn't know at increasing indices in a huge array I created in
 * memory. When I reached the end of the dictionary, I started from the beginning and did the same
 * thing until I reached the page I started at.
 *
 * Now I have an array of words that are mostly alphabetical, except they start somewhere in the
 * middle of the alphabet, reach the end, and then start from the beginning of the alphabet.
 * Write a method for finding the index of the "rotation point," which is where I started working
 * from the beginning of the dictionary. This array is huge (there are lots of words I don't know)
 * so we want to be efficient here (to keep things simple, you can assume all words are lowercase).
 */
public class FindRotationPoint {

  public static void main(String[] args) {
    String[] words = new String[]{
        "ptolemaic",
        "retrograde",
        "supplant",
        "undulate",
        "xenoepist",
        "asymptote",  // <-- rotates here!
        "babka",
        "banoffee",
        "engender",
        "karpatka",
        "othellolagkage",
        };
    System.out.println(findRotationPoint(words, 0, words.length - 1));

    // not rotated
    words = new String[]{
        "asymptote",
        "babka",
        "banoffee",
        "engender",
        "karpatka",
        "othellolagkage",
        "ptolemaic",
        "retrograde",
        "supplant",
        "undulate",
        "xenoepist"
    };
    System.out.println(findRotationPoint(words, 0, words.length - 1));
  }

  static int findRotationPoint(String[] words, int start, int end) {
    if (words == null || words.length == 0) {
      return -1;
    }

    if (start == end) {
      // we have zoomed in on the rotation point!
      return start;
    }

    int midPoint = (start + end) / 2;
    String wordAtMidpoint = words[midPoint];
    String wordAtStart = words[start];
    String wordAtEnd = words[end];

    if (wordAtMidpoint.compareTo(wordAtStart) < 0) { // is word at midpoint out of order relative
      // to word at start? then recurse on top half
      return findRotationPoint(words, start, midPoint);
    } else if (wordAtMidpoint.compareTo(wordAtEnd) > 0) { // is word at midpoint out of order
      // relative to word at end? then recurse on bottom half
      return findRotationPoint(words, midPoint + 1, end);
    } else {
      // array isn't rotated
      return 0;
    }
  }
}
