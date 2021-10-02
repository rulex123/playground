package exercises.recursion;

/**
 * Given two integers, write a function to sum the numbers without using any arithmetic operators.
 */
public class Sum {

    public static void main(String[] args) {
        System.out.println(sum(3, 25));
        System.out.println(sum(12, 28));
        System.out.println(sum(13, 13));
        System.out.println(sum(-4, -5));
        System.out.println(sum(-20, 10));
    }

    static int sum(int firstNumber, int secondNumber) {
        if (secondNumber == 0) {
            return firstNumber;
        }
        int partialSum = firstNumber ^ secondNumber;
        int carry = (firstNumber & secondNumber) << 1;
        return sum(partialSum, carry);
    }
}
