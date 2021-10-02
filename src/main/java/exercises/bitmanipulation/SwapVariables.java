package exercises.bitmanipulation;

/**
 * Given two integers, write a function that swaps them without using any temporary variables.
 */
public class SwapVariables {

    public static void main(String[] args) {
        swapVariables(13, 34);
        swapVariables(-2, 86);
        swapVariables(4, 123);
    }

    static void swapVariables(int firstNumber, int secondNumber) {
        System.out.println("before swap <" + firstNumber + "," + secondNumber + ">");

        // swap
        firstNumber ^= secondNumber;
        secondNumber ^= firstNumber;
        firstNumber ^= secondNumber;

        System.out.println("after swap swap <" + firstNumber + "," + secondNumber + ">");
    }
}
