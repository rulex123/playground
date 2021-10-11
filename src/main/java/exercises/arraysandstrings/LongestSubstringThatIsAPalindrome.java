package exercises.arraysandstrings;

/**
 * Given a string s, find the longest substring in s which is a palindrome.
 *
 * @author emanno
 * @version 1.0 Sep 22, 2017
 */
public class LongestSubstringThatIsAPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubstring("bananas"));
        System.out.println(longestPalindromeSubstring("baddad"));
        System.out.println(longestPalindromeSubstring("abcdef"));
        System.out.println(longestPalindromeSubstring("abcdeefghi"));
        System.out.println(longestPalindromeSubstring("stannats"));
    }


    static String longestPalindromeSubstring(String s) {
        if (s == null || s.isEmpty())
            return "";

        int palStartAt = 0;
        int palEndAt = 0;

        // look for palindrome substrings of odd size (they expand out from one character)
        for (int i = 1; i < s.length(); i++) {
            int from = i - 1;
            int to = i + 1;
            int[] palIndices = expandOut(s, from, to);

            if (palIndices != null) {
                // check if we found a LONGER palindrome substring
                if (palIndices[1] - palIndices[0] > palEndAt - palStartAt) {
                    palStartAt = palIndices[0];
                    palEndAt = palIndices[1];
                }
            }
        }

        // look for palindrome substrings of even size (expand out from a pair of identical characters)
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) { // check if we have a pair of contiguous identical characters
                if (palEndAt - palStartAt < 1) {
                    palStartAt = i;
                    palEndAt = i + 1;
                }

                int from = i - 1;
                int to = i + 2;
                int[] palIndices = expandOut(s, from, to);

                if (palIndices != null) {
                    // check if we found a LONGER palindrome substring
                    if (palIndices[1] - palIndices[0] > palEndAt - palStartAt) {
                        palStartAt = palIndices[0];
                        palEndAt = palIndices[1];
                    }
                }
            }
        }

        return s.substring(palStartAt, palEndAt + 1);
    }


    public static String longestSubstringThatIsAPalindrome_bruteForce(String string) {
        if (string == null || string.isEmpty())
            return string;

        String longestPalindrome = null;
        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j < string.length() + 1; j++) {
                String subString = string.substring(i, j);

                if (isPalindrome(subString)) {
                    if (longestPalindrome == null) {
                        longestPalindrome = subString;
                    } else {
                        longestPalindrome = subString.length() > longestPalindrome.length() ? subString : longestPalindrome;
                    }
                }
            }
        }

        return longestPalindrome;
    }


    private static boolean isPalindrome(String string) {
        if (string.length() == 1)
            return true;

        for (int i = 0, j = string.length() - 1; i < j; i++, j--) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    private static int[] expandOut(String string, int startIndex, int endIndex) {
        int[] palindromeIndices = null;

        while (startIndex >= 0 && endIndex < string.length()) {
            char c1 = string.charAt(startIndex);
            char c2 = string.charAt(endIndex);

            if (c1 == c2) {
                palindromeIndices = new int[]{startIndex, endIndex};
                startIndex--;
                endIndex++;
            } else {
                break;
            }
        }
        return palindromeIndices;
    }

}
