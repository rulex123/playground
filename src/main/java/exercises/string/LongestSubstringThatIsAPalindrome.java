package exercises.string;

/**
 * Given a string s, find the longest substring in s which is a palindrome.
 *
 * @author emanno
 * @version 1.0 Sep 22, 2017
 */
public class LongestSubstringThatIsAPalindrome {

    public static void main(String[] args) {
        System.out.println(longestSubstringThatIsAPalindrome_bruteForce("bananas"));

        System.out.println(longestSubstringThatIsAPalindrome_dynamicProgramming("bananas"));

    }


    public static String longestSubstringThatIsAPalindrome_dynamicProgramming(String string) {
        if (string == null || string.isEmpty())
            return string;

        int stringLength = string.length();
        int startIndex = -1;
        int longestPalindromeLength = -1;
        boolean[][] palindromes = new boolean[stringLength][stringLength];

        // populate matrix for substrings of length 1
        for (int i = 0; i < palindromes.length; i++) {
            palindromes[i][i] = true;
            startIndex = i;
            longestPalindromeLength = 1;
        }

        // populate matrix for substrings of length 2
        for (int i = 0; i < palindromes.length - 1; i++) {
            if (string.charAt(i) == string.charAt(i + 1)) {
                palindromes[i][i + 1] = true;
                startIndex = i;
                longestPalindromeLength = 2;
            }
        }

        // populate matrix for substrings of length > 2
        for (int i = 2; i < palindromes.length; i++) {
            for (int j = 0; j < palindromes.length - i; j++) {
                if (string.charAt(j) == string.charAt(i) && palindromes[j + 1][i - 1] == true) {
                    palindromes[j][i] = true;
                    startIndex = j;
                    longestPalindromeLength = i - j + 1;
                }
            }
        }

        return string.substring(startIndex, startIndex + longestPalindromeLength);
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

}
