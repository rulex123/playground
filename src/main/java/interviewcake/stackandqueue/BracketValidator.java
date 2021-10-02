package interviewcake.stackandqueue;

import java.util.Stack;

/**
 * You're working with an intern that keeps coming to you with JavaScript code that won't run
 * because the braces, brackets, and parentheses are off. To save you both some time, you decide to
 * write a braces/brackets/parentheses validator.
 * <p>
 * Let's say:
 * <p>
 * '(', '{', '[' are called "openers."
 * ')', '}', ']' are called "closers."
 * Write an efficient method that tells us whether or not an input string's openers and closers are
 * properly nested.
 * <p>
 * Examples:
 * <p>
 * "{ [ ] ( ) }" should return true
 * "{ [ ( ] ) }" should return false
 * "{ [ }" should return false
 */
public class BracketValidator {

    public static void main(String[] args) {
        System.out.println(bracketValidator("{ [ ] ( ) }"));
        System.out.println(bracketValidator("{ [ ( ] ) }"));
        System.out.println(bracketValidator("{ [ }"));
    }

    static boolean bracketValidator(String code) {
        if (code == null || code.isEmpty()) {
            return true;
        }

        Stack<Character> openersAndClosers = new Stack<>();
        char[] characters = code.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            char currChar = characters[i];
            if (isOpener(currChar)) {
                openersAndClosers.push(currChar);
            }
            if (isCloser(currChar)) {
                if (openersAndClosers.isEmpty() || !closerAndOpenerMatch(openersAndClosers.peek(),
                        currChar)) {
                    return false;
                }
                openersAndClosers.pop();
            }
        }
        return openersAndClosers.isEmpty();
    }

    static boolean isOpener(char character) {
        return character == '(' || character == '{' || character == '[';
    }

    static boolean isCloser(char character) {
        return character == ')' || character == '}' || character == ']';
    }

    static boolean closerAndOpenerMatch(char opener, char closer) {
        if (opener == '(') {
            return closer == ')';
        } else if (opener == '{') {
            return closer == '}';
        } else {
            return closer == ']';
        }
    }

}
