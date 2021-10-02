package exercises.bitmanipulation;

/**
 * Given an integer, write a function to compute the number of ones in the binary representation
 * of the number
 */
public class OnesInBinaryNumber {

    public static void main(String[] args) {
        System.out.println(onesInBinaryNumber(0));
        System.out.println(onesInBinaryNumber(8));
        System.out.println(onesInBinaryNumber(15));
        System.out.println(onesInBinaryNumber(5));
    }

    static int onesInBinaryNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("input number must be greater or equal to zero!");
        }
        int ones = 0;
        while (number > 0) {
            ones = ones + (number & 1);
            number = number >> 1;
        }

        return ones;
    }
}
