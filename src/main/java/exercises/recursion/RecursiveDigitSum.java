package exercises.recursion;

/**
 * Given an integer, we need to find the super digit of the integer. We define super digit of an integer using the
 * following rules: If has only digit, then its super digit is . Otherwise, the super digit of is equal to the super
 * digit of the digit-sum of . Here, digit-sum of a number is defined as the sum of its digits. You are given two
 * numbers n and k. You have to calculate the super digit of p. p is created when number n is concatenated k times. That
 * is, if n = 123 and k = 3, then p = 123123123.
 *
 * @author emanno
 * @version 1.0 Apr 18, 2018
 */
public class RecursiveDigitSum {

    public static void main(String[] args) {
        System.out.println(recursiveDigitSum("123", 3));
    }


    public static int recursiveDigitSum(String n, int k) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            sb.append(n);
        }

        return recursiveDigitSum(sb.toString());
    }


    private static int recursiveDigitSum(String n) {
        if (n.length() == 1)
            return Integer.parseInt(n);

        int sumOfDigits = 0;
        for (int i = 0; i < n.length(); i++) {
            sumOfDigits += n.charAt(i) - '0';
        }

        return recursiveDigitSum(Integer.toString(sumOfDigits));
    }

}
