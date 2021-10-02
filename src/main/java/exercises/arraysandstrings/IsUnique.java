package exercises.arraysandstrings;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use any additional data
 * structures?
 *
 * @author emanno
 * @version 1.0 Aug 4, 2017
 */
public class IsUnique {

    public static void main(String[] args) {
        System.out.println(isUnique("spoon"));
        System.out.println(isUnique("fork"));
        System.out.println(isUnique("table"));
        System.out.println(isUnique("bottle"));

        System.out.println();

        System.out.println(isUnique_noAdditionalDataStructures("spoom"));
        System.out.println(isUnique_noAdditionalDataStructures("fork"));
        System.out.println(isUnique_noAdditionalDataStructures("table"));
        System.out.println(isUnique_noAdditionalDataStructures("bottle"));
    }


    public static boolean isUnique(String string) {
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException("input string is null or empty");

        // assuming input string is an ASCII string
        if (string.length() > 128)
            return false;

        boolean[] letters = new boolean[128];
        for (int i = 0; i < string.length(); i++) {
            if (letters[string.charAt(i)]) {
                return false;
            } else {
                letters[string.charAt(i)] = true;
            }
        }

        return true;
    }


    public static boolean isUnique_noAdditionalDataStructures(String string) {
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException("input string is null or empty");

        // assuming input string is an ASCII string
        if (string.length() > 128)
            return false;

        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            for (int j = i + 1; j < string.length(); j++) {
                if (letter == string.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

}
