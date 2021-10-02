package exercises.bitmanipulation;

/**
 * Given a positive integer, print the next smallest and the next largest number that have the same
 * number of 1 bits in
 * their binary representation.
 *
 * @author emanno
 * @version 1.0 Jul 20, 2017
 */
public class NextNumber {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            nextNumber(i);
        }
    }


    static void nextNumber(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("input number must be a positive integer!");
        }
        System.out.println(nextSmallestNumber(num) + "<-" + num + "->" + nextLargestNumber(num));
    }

    static int nextSmallestNumber(int num) {
        int onesCounter = 0;
        while ((num & 1) != 0) {
            onesCounter++;
            num = num >>> 1;
        }

        if (num == 0) {
            // original number was something like 00..0011..11, so there is no way to get a smaller
            // number with the same number of bits set
            return -1;
        }

        int zerosCounter = 0;
        while ((num & 1) != 1) {
            zerosCounter++;
            num = num >>> 1;
        }

        // clear all bits up to (zerosCounter + onesCounter)
        num = num << (zerosCounter + onesCounter);

        // clear the bit right after (zerosCounter + onesCounter)
        int mask = ~(1 << (zerosCounter + onesCounter));
        num = num & mask;

        // set the bit at the end of (zerosCounter + onesCounter)
        mask = 1 << (onesCounter + zerosCounter - 1);
        num = num | mask;

        // move any remaining bits that were set by shifting them to the left as much as possible
        if (onesCounter > 0) {
            for (int i = 1; i <= onesCounter; i++) {
                mask = 1 << (zerosCounter + onesCounter - 1 - i);
                num = num | mask;
            }
        }

        return num;
    }

    static int nextLargestNumber(int num) {
        // count how many bits are not set (starting from LSB)
        int zerosCounter = 0;
        while ((num & 1) != 1) {
            zerosCounter++;
            num = num >>> 1;
        }

        // count how many bits are set (starting from LSB)
        int onesCounter = 0;
        while ((num & 1) != 0) {
            onesCounter++;
            num = num >>> 1;
        }

        if (zerosCounter + onesCounter == 31) {
            return -1;
        }

        // now all the bits that were set up to (zerosCounter + onesCounter) are cleared
        num = num << (zerosCounter + onesCounter);
        // now set a bit after (zerosCounter + onesCounter)
        int mask = 1 << (zerosCounter + onesCounter);
        num = num | mask;

        // if there were any other bits set, set them
        if (onesCounter > 1) {
            mask = 1 << (onesCounter - 1);
            mask -= 1; // to flip from e.g. 1000 to 0111
            num = num | mask;
        }
        return num;
    }

}
