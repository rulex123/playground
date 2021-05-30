package exercises.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of string, write a function to get the kth most frequently occurring string.
 */
public class KthMostFrequentString {

  public static void main(String[] args) {
    List<String> strings = Arrays.asList("a", "b", "c", "a", "b", "a");
    System.out.println(kthMostFrequentString(strings, 0));
    System.out.println(kthMostFrequentString(strings, 1));
    System.out.println(kthMostFrequentString(strings, 2));
    System.out.println(kthMostFrequentString(strings, 3));
  }

  static String kthMostFrequentString(List<String> strings, int k) {
    if (strings == null || strings.isEmpty()) {
      throw new RuntimeException("null or empty strings!");
    }

    Map<String, Integer> freqMap = new HashMap<>();
    for (int i = 0; i < strings.size(); i++) {
      final String string = strings.get(i);
      if (freqMap.containsKey(string)) {
        freqMap.put(string, freqMap.get(string) + 1);
      } else {
        freqMap.put(string, 1);
      }
    }

    // now sort the entry set by frequency
    final ArrayList<Map.Entry<String, Integer>> tmp = new ArrayList<>(freqMap.entrySet());
    Collections.sort(tmp,
        Comparator.comparing(e -> ((Map.Entry<String, Integer>) e).getValue()).reversed());

    if (k > tmp.size() - 1) {
      return null;
    } else {
      return tmp.get(k).getKey();
    }
  }
}
