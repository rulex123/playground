package exercises.arraysandstrings;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 *
 * @author emanno
 * @version 1.0 Aug 4, 2017
 */
public class CheckPermutation {

    public static void main(String[] args) {
        System.out.println(checkPermutation("god", "dog"));
        System.out.println(checkPermutation("clock", "table"));
    }


    public static boolean checkPermutation(String a, String b) {
        if (a.length() != b.length())
            return false; // two strings cannot be permutations of each other if they have different lengths

        int[] lettersCount_1 = buildLettersCount(a);

        for (int i = 0; i < b.length(); i++) {
            lettersCount_1[b.charAt(i)]--;
            if (lettersCount_1[b.charAt(i)] < 0)
                return false;
        }

        return true;
    }


    private static int[] buildLettersCount(String string) {
        int[] lettersCount = new int[128]; // assuming input string is an ASCII string
        for (int i = 0; i < string.length(); i++) {
            lettersCount[string.charAt(i)]++;
        }
        return lettersCount;
    }
}
