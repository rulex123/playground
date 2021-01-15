
package exercises.bitmanipulation;

/**
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double, print the binary
 * representation. If
 * the number cannot be represented accurately in binary with at most 32 characters, print "ERROR".
 *
 * @author emanno
 * @version 1.0 Jul 11, 2017
 */
public class BinaryToString {

  public static void main(String[] args) {
    System.out.println(binaryToStringForDoubleBetween0And1(0.25));
    System.out.println(binaryToStringForDoubleBetween0And1(0.025));
    System.out.println(binaryToStringForDoubleBetween0And1(0.75));
    System.out.println(binaryToStringForDoubleBetween0And1(0.750001));
  }

  public static String binaryToStringForDoubleBetween0And1(double num) {
    if (num >= 1 || num <= 0) {
      throw new IllegalArgumentException("invalid number!");
    }

    StringBuilder sb = new StringBuilder();
    sb.append(".");
    int exponent = 1;
    while (num > 0) {
      if (sb.length() > 32) {
        return "ERROR";
      }

      double fract = Math.pow(2.0, -exponent);
      if (num >= fract) {
        sb.append("1");
        num = num - fract;
      } else {
        sb.append("0");
      }
      exponent++;
    }

    return sb.toString();
  }

}
