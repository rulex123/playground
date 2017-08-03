
package exercises;

/**
 * Given two integers, an hour and a minute, write a function to calcuate the angle between the two hands on a clock
 * representing that time
 *
 * @author emanno
 * @version 1.0 Jun 25, 2017
 */
public class AngleClock {

	public static void main ( String[] args ) {
		int minutes = 13;
		int hour = 11;

		final int MINUTES_IN_HOUR = 60;
		final int DEGREES_PER_MINUTE = 360 / MINUTES_IN_HOUR;
		final int MINUTE_SLOTS_PER_HOUR = 5;
		final int DEGREES_PER_HOUR = MINUTE_SLOTS_PER_HOUR * DEGREES_PER_MINUTE;

		double minutesAngle = DEGREES_PER_MINUTE * minutes;
		double hourAngle = DEGREES_PER_HOUR * hour
				+ (double) minutes / (double) MINUTES_IN_HOUR * MINUTE_SLOTS_PER_HOUR * DEGREES_PER_MINUTE;

		double angle = Math.abs( minutesAngle - hourAngle );
		angle = angle > 180 ? (360 - angle) : angle;

		System.out.print( "The angle between the clock hands is " + angle + " degrees" );

	}

}
