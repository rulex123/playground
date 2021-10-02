package exercises.bitmanipulation;

/**
 * You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest
 * sequence of 1s you could create. <br>
 * EXAMPLE <br>
 * input: 1775 (in binary 11011101111) <br>
 * output: 8
 */
public class FlipBitToWin {

    public static void main(String[] args) {
        System.out.println(flipBitToWin(-1)); // expected 32
        System.out.println(flipBitToWin(0)); // expected 1
        System.out.println(flipBitToWin(0b11011101111)); // expected 8
        System.out.println(flipBitToWin(0b111101110111)); // expected 8
        System.out.println(flipBitToWin(0b1110011101111)); // expected 8
    }

    public static int flipBitToWin(int num) {
        // edge cases
        if (num == -1) {
            return 32;
        }
        if (num == 0) {
            return 1;
        }

        boolean terminate = false;
        int currBit = 0;
        int flipCount = 1;
        int lastFlippedBit = -1;
        int carryOver = 0;
        int currLength = 0;
        int maxLength = Integer.MIN_VALUE;
        while (num > 0 || !terminate) {
            // keep track of which bit we are looking at from the original number
            currBit++;
            // check if the LSB is set
            if ((num & 1) == 1) {
                // bit is set
                // increment length of current sequence of 1s
                currLength++;
            } else {
                // bit is not set
                // can we flip the bit?
                if (flipCount > 0) {
                    flipCount--;
                    currLength++;
                } else {
                    // check if current sequence (including any carry over) is the longest seen so far
                    if (currLength + carryOver > maxLength) {
                        maxLength = currLength + carryOver;
                    }
                    // flip current bit and reset length of current sequence of 1s
                    flipCount = 0;
                    currLength = 1;
                    // set carry over
                    carryOver = currBit - lastFlippedBit - 1;
                }
                lastFlippedBit = currBit;
            }

            if (num == 0) {
                // we terminate once we have shifted all 1s in the original number to the right
                // and taken into account a trailing zero
                terminate = true;
            } else {
                num = num >>> 1;
            }
        }

        return maxLength;
    }

}
