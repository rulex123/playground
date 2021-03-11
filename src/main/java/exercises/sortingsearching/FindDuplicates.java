package exercises.sortingsearching;

/**
 * You have an array with all the numbers from 1 to N, where N is at most 32000. The array may
 * have duplicate entries and you do not know what N is. With only 4 kilobytes of memory
 * available, how would you print all duplicate elements in the array?
 */
public class FindDuplicates {

  public static void main(String[] args) {
    int[] array = new int[]{ 3, 7, 15, 1, 9, 12, 1, 3, 3, 2, 4, 7, 9, 15, 14, 5, 8, 6, 10, 11, 13 };
    findDuplicates(array);
  }

  static void findDuplicates(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    // we have 4 kilobytes of memory available, meaning 32000 bits, so we can map each number in
    // the input array to a bit in a bit vector that we keep in memory
    byte[] bitVector = new byte[32000 / 8];
    for (int i = 0; i < array.length; i++) {
      int currNumber = array[i];
      if (isBitSet(currNumber, bitVector)) {
        // we found a duplicate number!
        System.out.println(currNumber);
      } else {
        // set the bit corresponding to current number
        setBit(currNumber, bitVector);
      }
    }
  }

  static boolean isBitSet(int number, byte[] bitVector) {
    int[] coordinates = mapNumberToBitVectorCoordinates(number);
    int indexOfByte = coordinates[0];
    int indexOfBit = coordinates[1];
    byte mask = (byte) (1 << indexOfBit);

    if ((bitVector[indexOfByte] & mask) == 0) {
      return false;
    }
    return true;
  }

  static void setBit(int number, byte[] bitVector) {
    int[] coordinates = mapNumberToBitVectorCoordinates(number);
    int indexOfByte = coordinates[0];
    int indexOfBit = coordinates[1];
    byte mask = (byte) (1 << indexOfBit);

    bitVector[indexOfByte] = (byte) (bitVector[indexOfByte] | mask);
  }

  static int[] mapNumberToBitVectorCoordinates(int number) {
    // find the index to access the correct byte in the bit vector
    int indexOfByte = number % 8 == 0 ? (number / 8) - 1 : number / 8;
    // find the index to access the correct bit in the bit vector
    int indexOfBit = number % 8 == 0 ? 7 : (number % 8) - 1;
    return new int[]{ indexOfByte, indexOfBit };
  }

}
