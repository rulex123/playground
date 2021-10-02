package exercises.arraysandstrings;

/**
 * Given two strings, write a function that returns the longest common substrings.
 * e.g. longestSubstring("ABAB","BABA") = "ABA"
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABAB", "BABA"));
        System.out.println(longestCommonSubstring("BABC", "ABC"));
        System.out.println(longestCommonSubstring("ABBACO", "CABBAO"));
    }

    static String longestCommonSubstring(String stringA, String stringB) {
        String result = "";
        if ((stringA == null || stringA.isEmpty()) || (stringB == null || stringB
                .isEmpty())) {
            return result;
        }

        int prevLength = 0;
        int[] lengths = new int[stringA.length()];
        for (int j = 0; j < stringA.length(); j++) {
            char charA = stringA.charAt(j);
            for (int i = 0; i < stringB.length(); i++) {
                char charB = stringB.charAt(i);
                if (charA == charB) {
                    if (j == 0) { // check if we are at the edge
                        lengths[i] = 1;
                    } else {
                        int newLength = prevLength + 1;
                        prevLength = lengths[i];
                        lengths[i] = newLength;
                    }
                } else {
                    prevLength = lengths[i];
                    lengths[i] = 0;
                }
                if (lengths[i] > result.length()) { // check if we have found a new substring
                    result = stringB.substring(i - lengths[i] + 1, i + 1);
                }
            }
        }

        return result;
    }

}
