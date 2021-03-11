package exercises.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a boolean expression consisting of the symbols 0 (false), 1 (true), & (AND), | (OR), and ^
 * (XOR), and a desired boolean result value <code>result</code>, implement a function to count
 * the number of ways of parenthesizing the expression such that it evaluates to
 * <code>result</code>.
 *
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 */
public class BooleanEvaluation {

  public static void main(String[] args) {
    String expression = "1^0|0|1";
    System.out.println(booleanEvaluation(expression, false));

    expression = "0&0&0&1^1|0";
    System.out.println(booleanEvaluation(expression, true));
  }

  static int booleanEvaluation(String expression, boolean result) {
    if (expression == null || expression.isEmpty()) {
      return 0;
    }

    return booleanEvaluation(expression.toCharArray(), 0, expression.length() - 1, result,
        new HashMap<>());
  }

  static int booleanEvaluation(char[] expression, int startAt, int endAt, boolean result,
                               Map<String, Integer> cache) {
    // base case: we are down to one character (either a 1 or a 0)
    if (startAt == endAt) {
      char currChar = expression[startAt];
      if (result) {
        return currChar == '1' ? 1 : 0;
      } else {
        return currChar == '0' ? 1 : 0;
      }
    }

    int count = 0;
    String cacheKey = "" + startAt + endAt + result;

    // start looping at (start + 1) index (that will be the position of the first operator)
    // then go through all operators (i.e. increment i by 2)
    for (int i = startAt + 1; i < endAt; i = i + 2) {
      char operator = expression[i];
      // index at which expression to the left of operator ends
      int leftExpressionEnd = i - 1;
      // index at which expression to the right of operator starts
      int rightExpressionStart = i + 1;

      // possible operators are &, | and ^
      switch (operator) {
        case '^':
          if (result) {
            // left is true, right is false
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            int evalRight =
                booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;

            // left is false, right is true
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;
          } else {
            // left and right are both true
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            int evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;

            // left and right are both false
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;
          }
          break;
        case '&':
          if (result) {
            // left and right are both true
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            int evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;
          } else {
            // left is true, right is false
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            int evalRight =
                booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;

            // left and right are both false
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;

            // left is false, right is true
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;
          }
          break;
        case '|':
          if (result) {
            // left is true, right is false
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            int evalRight =
                booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;

            // left and right are both true
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, true, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;

            // left is false, right is true
            evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            evalRight = booleanEvaluation(expression, rightExpressionStart, endAt, true, cache);
            count += evalLeft * evalRight;
          } else {
            // left and right are both false
            int evalLeft = booleanEvaluation(expression, startAt, leftExpressionEnd, false, cache);
            int evalRight =
                booleanEvaluation(expression, rightExpressionStart, endAt, false, cache);
            count += evalLeft * evalRight;
          }
          break;
      }
    }
    cache.put(cacheKey, count);
    return count;
  }
}
