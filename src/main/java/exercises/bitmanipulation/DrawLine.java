
package exercises.bitmanipulation;

import java.io.IOException;

/**
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be
 * stored in one byte.
 * The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows).
 * The height of the
 * screen, of course, can be derived from the length of the array and the width. Implement a
 * function that draws a
 * horizontal line from (x1,y) to (x2,y). The method signature should look something like: <br>
 * <code>drawLine(byte[] screen, int width, int x1, int x2, int y)</code>
 *
 * @author emanno
 * @version 1.0 Jul 28, 2017
 */
public class DrawLine {

  public static void main(String[] args) {
    byte[] screen = new byte[12];
    drawLine(screen, 32, 5, 29, 2);
    System.out.println("--------");
    printScreen(screen);
  }

  static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
    // defend against bad screen or width
    if (screen == null || screen.length == 0 || width % 8 != 0) {
      return;
    }

    // derive the height of the screen
    int height = calculateHeightOfScreen(screen, width);

    // validate the y input param
    if (y < 1 || y > height) {
      throw new IllegalArgumentException("invalid value for y!");
    }

    // translate y to a range of indices in the screen array
    int[] indexes = calculateIndexes(y, height, screen);
    int minIndex = indexes[0];
    int maxIndex = indexes[1];

    boolean startedLine = false;
    boolean finishedLine = false;

    for (int i = minIndex, counter = 0; i <= maxIndex; i++, counter++) {
      // calculate the range of pixels we are dealing with
      int minPixel = 8 * counter + 1;
      int maxPixel = minPixel + 8 - 1;

      // check if we are done
      if (startedLine && finishedLine) {
        break;
      }

      // check if line starts and ends at current index
      if (isWithin(x1, minPixel, maxPixel) && isWithin(x2, minPixel, maxPixel)) {
        int maskA = (1 << maxPixel - x1 + 1) - 1;
        int maskB = 0xFF << (maxPixel - x2);
        screen[i] = (byte) (maskA & maskB);
        startedLine = true;
        finishedLine = true;
        continue;
      }

      // check if line starts at current index
      if (isWithin(x1, minPixel, maxPixel)) {
        startedLine = true;
        int mask = (1 << (maxPixel - x1 + 1)) - 1;
        screen[i] = (byte) (screen[i] | mask);
        continue;
      }

      // check if line ends at current index
      if (isWithin(x2, minPixel, maxPixel)) {
        finishedLine = true;
        screen[i] = (byte) 0xFF;
        screen[i] = (byte) (screen[i] << (maxPixel - x2));
        continue;
      }

      // otherwise flip all the bits for current index
      screen[i] = (byte) 0xFF;
    }
  }

  private static int[] calculateIndexes(final int y, final int height,
                                        final byte[] screen) {
    // first, we need to know how many bytes correspond to one line of the screen
    int bytesPerLIne = screen.length / height;

    // then, we need to identify all the indices
    int startIndex = (y - 1) * bytesPerLIne;
    int endIndex = startIndex + bytesPerLIne - 1;
    return new int[]{ startIndex, endIndex };
  }

  private static int calculateHeightOfScreen(final byte[] screen, final int width) {
    return screen.length / (width / 8);
  }

  static void printScreen(byte[] screen) {
    StringBuilder sb = new StringBuilder();
    for (byte byteInScreen : screen) {
      if (byteInScreen == 0) {
        sb.append("00000000");
        sb.append("\n");
      } else {
        int result = byteInScreen & 0xFF;
        String string = Integer.toBinaryString(result);
        for (int i = 0; i < (8 - string.length()); i++) {
          sb.append("0");
        }
        sb.append(string);
        sb.append("\n");
      }

    }
    System.out.print(sb);
  }

  static boolean isWithin(int startLineAtPixel, int firstPixel, int lastPixel) {
    if (startLineAtPixel >= firstPixel && startLineAtPixel <= lastPixel) {
      return true;
    }
    return false;
  }
}
