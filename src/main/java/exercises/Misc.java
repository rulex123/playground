package exercises;

import java.util.ArrayList;
import java.util.List;

public class Misc {

    public static void main(String[] args) {

        List<Object> nums = toList(1, "hello", 3);
        System.out.println(nums);

        // System.out.println( isPalindrome_rec( "abeba" ) );
        // System.out.println( isPalindrome_rec( "abba" ) );
        // System.out.println( isPalindrome_rec( "hello" ) );
        //
        // System.out.println( isPalindrome_it( "abeba" ) );
        // System.out.println( isPalindrome_it( "abba" ) );
        // System.out.println( isPalindrome_it( "hello" ) );

        // System.out.println( factorial_rec( 20 ) );
        System.out.println(reverseWordsInString_rec("cat is running"));
        System.out.println(reverseWordsInString_it2("cat is running"));

    }


    public static <T> List<T> toList(T... elements) {
        List<T> res = new ArrayList<>();
        for (T e : elements) {
            res.add(e);
        }
        return res;
    }


    // reverse words in a string -----------------
    public static String reverseWordsInString_rec(String input) {
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1) {
            return input;
        }

        return reverseWordsInString_rec(input.substring(indexOfSpace + 1))
                + " "
                + input.substring(0, indexOfSpace);
    }


    public static String reverseWordsInString_it1(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }


    /*
     * Time complexity: O(n) where n is the length of the input string
     * Space complexity: O(1)
     */
    public static String reverseWordsInString_it2(String input) {
        char[] chars = input.toCharArray();
        // first, reverse full input string in place
        reverseStringInPlace(chars, 0, input.length() - 1);

        // then, reverse each word in input string in place
        for (int i = 0, j = 0; j < chars.length; j++) {
            if (chars[j] == ' ') { // found a word, reverse
                reverseStringInPlace(chars, i, j - 1);
                // reposition i
                i = j + 1;
            } else if (j == chars.length - 1) {
                reverseStringInPlace(chars, i, j); // reverse last word
            }
        }

        return new String(chars);
    }


    public static void reverseStringInPlace(char[] input, int startAt, int endAt) {
        int stopAt = (startAt + endAt) / 2;
        if ((startAt + endAt) % 2 != 0) {
            stopAt++;
        }
        for (int i = startAt, j = endAt; i < stopAt; i++, j--) {
            // swap char
            char temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
    }


    // recursive multiply ------------------

    public static long recursiveMultiply_rec(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        if (a == 1) {
            return b;
        }

        if (b == 1) {
            return a;
        }

        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;

        return bigger + recursiveMultiply_rec(bigger, smaller - 1);
    }


    // factorial of a number --------------------
    public static long factorial_rec(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("I only deal with natural numbers");
        } else if (n == 0) {
            return 1; // base case
        }

        return n * factorial_rec(n - 1);
    }

    // sum of natural numbers


    // palindrome --------------------------------------
    public static boolean isPalindrome_rec(String input) {
        if (input.length() <= 1) {
            return true; // base case: 1-char or empty string is a palindrome
        }

        char firstChar = input.charAt(0);
        char lastChar = input.charAt(input.length() - 1);
        return (firstChar == lastChar) && isPalindrome_rec(input.substring(1, input.length() - 1));
    }


    public static boolean isPalindrome_it(String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
