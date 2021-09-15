
package exercises.misc;

/**
 * You're an engineer at a disruptive drone delivery startup and your CTO asks you to come up with an efficient
 * algorithm that calculates the minimum amount of energy required for the company's drone to complete its flight. You
 * know that the drone burns 1 kWh (kilowatt-hour is an energy unit) for every mile it ascends, and it gains 1 kWh for
 * every mile it descends. Flying sideways neither burns nor adds any energy. Given an array route of 3D points,
 * implement a function calcDroneMinEnergy that computes and returns the minimal amount of energy the drone would need
 * to complete its route. Assume that the drone starts its flight at the first point in route. That is, no energy was
 * expended to place the drone at the starting point. For simplicity, every 3D point will be represented as an integer
 * array whose length is 3. Also, the values at indexes 0, 1, and 2 represent the x, y and z coordinates in a 3D point,
 * respectively.
 *
 * @author emanno
 * @version 1.0 Aug 15, 2017
 */
public class DroneFlightPlanner {

	public static void main ( String[] args ) {
		int[][] route = new int[5][3];

		route[ 0 ][ 0 ] = 0;
		route[ 0 ][ 1 ] = 2;
		route[ 0 ][ 2 ] = 10;

		route[ 1 ][ 0 ] = 3;
		route[ 1 ][ 1 ] = 5;
		route[ 1 ][ 2 ] = 0;

		route[ 2 ][ 0 ] = 9;
		route[ 2 ][ 1 ] = 20;
		route[ 2 ][ 2 ] = 6;

		route[ 3 ][ 0 ] = 10;
		route[ 3 ][ 1 ] = 12;
		route[ 3 ][ 2 ] = 15;

		route[ 4 ][ 0 ] = 10;
		route[ 4 ][ 1 ] = 10;
		route[ 4 ][ 2 ] = 8;

		System.out.println( calculateDroneMinEnergy( route ) );

	}


	public static int calculateDroneMinEnergy ( int[][] route ) {
		int largestEnergyDeficit = 0;
		int energyBudget = 0;
		for ( int i = 1; i < route.length; i++ ) {
			energyBudget += route[ i - 1 ][ 2 ] - route[ i ][ 2 ];
			if ( energyBudget < largestEnergyDeficit )
				largestEnergyDeficit = energyBudget;
		}

		return Math.abs( largestEnergyDeficit );
	}

}
