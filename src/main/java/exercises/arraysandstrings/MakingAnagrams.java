package exercises.arraysandstrings;

/**
 * Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams
 * of each other if the first string's letters can be rearranged to form the second string. In other words, both strings
 * must contain the same exact letters in the same exact frequency. For example, bacdc and dcbac are anagrams, but bacdc
 * and dcbad are not. Alice decides on an encryption scheme involving two large strings where encryption is dependent on
 * the minimum number of character deletions required to make the two strings anagrams. Can you help her find this
 * number? Given two strings, a and b, that may or may not be of the same length, determine the minimum number of
 * character deletions required to make a and b anagrams. Any characters can be deleted from either of the strings. <br>
 *
 * @author emanno
 * @version 1.0 Oct 8, 2017
 */
public class MakingAnagrams {

    public static void main(String[] args) {
        System.out.println(makingAnagrams("bacdc", "dcbac"));
        System.out.println(makingAnagrams("cde", "abc"));
        System.out.println(makingAnagrams("bananas", "banano"));
        System.out.println(makingAnagrams("xeno", "eonx"));
        System.out.println(makingAnagrams("abc", "deccbb"));
    }


    public static int makingAnagrams(String a, String b) {
        if ((a == null || a.isEmpty()) || (b == null || b.isEmpty()))
            return 0;

        int[] lettersCount = new int[26]; // 26 letters from 'a' to 'z'
        for (char c : a.toCharArray()) {
            lettersCount[c - 'a']++;
        }

        int charsToSubtractToMakeAnagrams = a.length();
        for (char c : b.toCharArray()) {
            int index = c - 'a';
            if (lettersCount[index] > 0)
                charsToSubtractToMakeAnagrams--;
            else
                charsToSubtractToMakeAnagrams++;

            lettersCount[index]--;
        }

        return charsToSubtractToMakeAnagrams;
    }

}
