package exercises.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to compute all permutations of a string of unique characters
 *
 * @author emanno
 * @version 1.0 May 7, 2017
 */
public class PermutationsWithoutDups {

    public static void main(String[] args) {
        String input = "abc";
        System.out.println(permutations_impl1(input, 0));
        System.out.println(permutations_impl2(input));
    }


    /*
     * This implementation computes all permutations of first (n-1) chars in input, and then adds first char of input in
     * all positions to those permutations
     */
    public static List<String> permutations_impl1(String input, int index) {
        if (input == null)
            return null;

        List<String> perms = new ArrayList<>();
        if ((input.length() - 1) == index) {
            perms.add(Character.toString(input.charAt(index)));
            return perms;
        }

        char charToAdd = input.charAt(index);
        List<String> words = permutations_impl1(input, index + 1);

        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i <= sb.length(); i++) {
                StringBuilder clonedSb = new StringBuilder(sb);
                perms.add(clonedSb.insert(i, charToAdd).toString());
            }
        }
        return perms;
    }


    /*
     * This implementation selects one char from input, then computes all permutations for the remaining chars in input,
     * and finally forms permutations by prepending the selected char to those permutations
     */
    public static List<String> permutations_impl2(String input) {
        int len = input.length();
        List<String> res = new ArrayList<>();
        if (len == 0) {
            res.add("");
            return res;
        }

        for (int i = 0; i < len; i++) {
            String before = input.substring(0, i);
            String after = input.substring(i + 1);

            List<String> partialPerms = permutations_impl2(before + after);

            for (String perm : partialPerms) {
                res.add(input.charAt(i) + perm);
            }
        }

        return res;
    }

}
