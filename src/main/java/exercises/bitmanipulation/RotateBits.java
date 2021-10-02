package exercises.bitmanipulation;

/**
 * Given a number, write a function to rotate the bits (i.e. circular shift).
 */
public class RotateBits {

    public static void main(String[] args) {
        int result = rotateBitsClockwise(0xFFFF0000, 8);
        System.out.println(leftPad(Integer.toHexString(result), 8));

        result = rotateBitsClockwise(0x13579BDF, 12);
        System.out.println(leftPad(Integer.toHexString(result), 8));

        result = rotateBitsAntiClockwise(0b10110011100011110000111110000000, 17);
        System.out.println(leftPad(Integer.toBinaryString(result), 32));
    }

    static int rotateBitsClockwise(int number, int noOfBits) {
        if (noOfBits > Integer.SIZE) {
            throw new IllegalArgumentException("cannot rotate by more than 32 bits!");
        }
        return (number >>> noOfBits | number << (Integer.SIZE - noOfBits));
    }

    static int rotateBitsAntiClockwise(int number, int noOfBits) {
        if (noOfBits > Integer.SIZE) {
            throw new IllegalArgumentException("cannot rotate by more than 32 bits!");
        }
        return (number << noOfBits | number >>> (Integer.SIZE - noOfBits));
    }

    static String leftPad(String input, int length) {
        String format = "%1$" + length + "s";
        return String.format(format, input).replace(' ', '0');
    }
}
