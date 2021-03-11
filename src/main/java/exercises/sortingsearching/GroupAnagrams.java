package exercises.sortingsearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 */
public class GroupAnagrams {

  public static void main(String[] args) {
    String[] array = new String[]{ "part", "fried", "spar", "fired", "trap", "listen", "rasp",
                                   "silent", "blegh" };
    groupAnagrams(array);
    System.out.println(Arrays.toString(array));
  }

  static void groupAnagrams(String[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    // map from sorted string to all its anagrams found in original array
    Map<String, List<String>> sortedStringToAnagrams = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      char[] stringChars = array[i].toCharArray();
      Arrays.sort(stringChars);
      String sortedString = new String(stringChars);

      if (sortedStringToAnagrams.containsKey(sortedString)) {
        sortedStringToAnagrams.get(sortedString).add(array[i]);
      } else {
        List<String> anagrams = new ArrayList<>();
        anagrams.add(array[i]);
        sortedStringToAnagrams.put(sortedString, anagrams);
      }
    }

    // reorganize array so that anagrams are grouped together
    int currIndex = 0;
    for (List<String> anagrams : sortedStringToAnagrams.values()) {
      for (int i = 0; i < anagrams.size(); i++, currIndex++) {
        array[currIndex] = anagrams.get(i);
      }
    }
  }
}
