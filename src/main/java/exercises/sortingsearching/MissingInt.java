package exercises.sortingsearching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Given an input file with 4 billion non-negative integers, provide an algorithm to generate an
 * integer that is not contained in the file. Assume you have 1 GB of memory available for the task;
 */
public class MissingInt {

  public static void main(String[] args) {
    String file = "src/main/java/exercises/sortingsearching/numbers.txt";
    int result = missingInt(file);
    System.out.println(result);
  }

  static int missingInt(String file) {
    // create bit vector that will be used to keep track of numbers in input file
    byte[] bitVector = setupBitVector();

    // setup to read numbers from file and flip bits in bit vector
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(file));
      scanner.useDelimiter("\n");
      while (scanner.hasNext()) {
        int currNumber = scanner.nextInt();
        // flip bit corresponding to current number we read from file
        setBit(currNumber, bitVector);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      scanner.close();
    }

    // now find the first bit that hasn't been flipped
    for (int i = 0; i < bitVector.length; i++) {
      byte currentByte = bitVector[i];
      for (int j = 0; j < 8; j++) {
        byte mask = (byte) (1 << j);
        if ((currentByte & mask) == 0) {
          return 8 * i + j;
        }
      }
    }

    throw new RuntimeException("shouldn't reach this point!");
  }

  private static void setBit(int currNumber, byte[] bitVector) {
    int[] coordinates = mapNumberToBitVectorCoordinates(currNumber);
    int indexOfByte = coordinates[0];
    int indexOfBit = coordinates[1];
    byte mask = (byte) (1 << indexOfBit);
    bitVector[indexOfByte] = (byte) (bitVector[indexOfByte] | mask);
  }

  private static int[] mapNumberToBitVectorCoordinates(int number) {
    // find the index to access the correct byte in the bit vector
    int indexOfByte = number / 8;
    // find the index to access the correct bit in the bit vector
    int indexOfBit = number % 8;
    return new int[]{ indexOfByte, indexOfBit };
  }

  private static byte[] setupBitVector() {
    long distinctNumbers = ((long)Integer.MAX_VALUE) + 1;
    byte[] bitVector = new byte[(int) (distinctNumbers / 8)];
    return bitVector;
  }

}
