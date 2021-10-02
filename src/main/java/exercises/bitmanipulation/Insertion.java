package exercises.bitmanipulation;

/**
 * You are given two 32-bits numbers, N and M, and two bit positions, i and j. Write a method to insert M into N such
 * that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M.
 * That is, if M=10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have
 * j=3 and i=2, because M could not fully fit between bit 3 and bit 2.
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class Insertion {
    private static final int BITS_IN_INT = 32;

    public static void main(String[] args) {
        int newNumber = insertion(0b10000000000, 0b10011, 2, 6);
        System.out.println(Integer.toBinaryString(newNumber)); // expected 10001001100
    }


    public static int insertion(int originalNumber, int numberToInsert, int fromBit, int toBit) {
        // we need to clear the bits in originalNumber from i (inclusive) to j (inclusive), so
        // we build a mask where all bits between i and j are zeros, and the rest of the bits are set
        int leftPart = ~0 << (toBit + 1);
        int rightPart = ~0 >>> (BITS_IN_INT - fromBit); // alternatively, rightPart = (1 << fromBit)- 1;
        int mask = leftPart | rightPart;
        int originalNumberWithClearedBits = originalNumber & mask; // clear bits between i and j in originalNumber

        return originalNumberWithClearedBits | (numberToInsert << fromBit); // inserting into original number
    }

}
