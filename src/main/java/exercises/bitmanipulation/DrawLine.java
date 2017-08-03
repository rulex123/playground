
package exercises.bitmanipulation;

import java.io.IOException;

/**
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.
 * The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows). The height of the
 * screen, of course, can be derived from the length of the array and the width. Implement a function that draws a
 * horizontal line from (x1,y) to (x2,y). The method signature should look something like: <br>
 * <code>drawLine(byte[] screen, int width, int x1, int x2, int y)</code>
 *
 * @author emanno
 * @version 1.0 Jul 28, 2017
 */
public class DrawLine {

	public static void main ( String[] args ) throws IOException {
		byte[] screen = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};

		// drawLine( screen, 24, 3, 19, 2 );
		// drawLine( screen, 24, 10, 15, 3 );
		drawLine( screen, 32, 5, 29, 2 );
		printScreen( screen );

	}


	/**
	 * @param screen
	 *        single array representing the screen
	 * @param width
	 *        width of the screen (in number of bits)
	 * @param x1
	 *        x-coordinate where the horizontal line should start (starts at 1)
	 * @param x2
	 *        x-coordinate where the horizontal line should end (starts at 1)
	 * @param y
	 *        y-coordinate where the horizontal line should be drawn (starts at 1)
	 */
	public static void drawLine ( byte[] screen, int width, int x1, int x2, int y ) {
		if ( x2 < x1 )
			return; // invalid input param, do nothing

		// calculate height of screen
		int bytesPerRow = width / Byte.SIZE;
		int height = screen.length / bytesPerRow;

		if ( y > height )
			return; // invalid input param, do nothing

		int firstByteIndex = (x1 / Byte.SIZE) + (bytesPerRow * (y - 1));
		int lastByteIndex = (x2 / Byte.SIZE) + (bytesPerRow * (y - 1));

		byte startLineMask = (byte) (0xFF >> (x1 % Byte.SIZE) - 1);
		byte endLineMask = (byte) ~(0xFF >> (x2 % Byte.SIZE));

		if ( firstByteIndex == lastByteIndex ) { // x1 and x2 are in the same byte
			screen[ firstByteIndex ] |= ((byte) (startLineMask & endLineMask));
		}
		else {
			if ( lastByteIndex - firstByteIndex > 1 ) { // x1 and x2 are far enough that entire bytes can be set in between
				for ( int i = firstByteIndex + 1; i < lastByteIndex; i++ ) {
					setByte( screen, i );
				}
			}

			// set first byte of horizontal line
			screen[ firstByteIndex ] |= startLineMask;

			// set last byte of horizontal line
			screen[ lastByteIndex ] |= endLineMask;

		}

	}


	private static void setByte ( byte[] screen, int index ) {
		screen[ index ] = (byte) 0xFF;
	}


	private static void printScreen ( byte[] screen ) {
		for ( byte b : screen ) {
			System.out.println( String.format( "%8s", Integer.toBinaryString( b & 0xFF ) ).replace( " ", "0" ) );
		}
	}

}
