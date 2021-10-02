package exercises.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement an algorithm to print all valid (e.g. properly opened and closed) combinations of n pairs of parenthesis
 *
 * @author emanno
 * @version 1.0 May 10, 2017
 */
public class Parens {

    public static void main(String[] args) {
        int noOfPairs = 3;
        List<String> res = new ArrayList<>();
        char[] parensStore = new char[noOfPairs * 2];
        generateParens(res, parensStore, noOfPairs, noOfPairs, 0);

        System.out.println(res);
    }


    public static void generateParens(List<String> result, char[] parensStore, int noOfRemainingOpenParens,
                                      int noOfRemainingClosedParens, int index) {
        if (noOfRemainingOpenParens < 0 || noOfRemainingClosedParens < noOfRemainingOpenParens)
            return;

        if (noOfRemainingOpenParens == 0 && noOfRemainingClosedParens == 0) {
            result.add(String.copyValueOf(parensStore));
            return;
        } else {
            parensStore[index] = '(';
            generateParens(result, parensStore, noOfRemainingOpenParens - 1, noOfRemainingClosedParens, index + 1);

            parensStore[index] = ')';
            generateParens(result, parensStore, noOfRemainingOpenParens, noOfRemainingClosedParens - 1, index + 1);
        }
    }

}
