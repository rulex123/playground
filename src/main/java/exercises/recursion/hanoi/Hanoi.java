
package exercises.recursion.hanoi;

import java.util.Arrays;
import java.util.Stack;

/**
 * Towers of Hanoi problem
 *
 * @author emanno
 * @version 1.0 Jun 23, 2017
 */
public class Hanoi {
	public static void main ( String[] args ) {

		// create tower A, contains disks to move
		Tower towerA = new Tower( "A" );
		for ( int i = 5; i >= 1; i-- ) {
			towerA.addDisk( i );
		}

		// create towers B and C, initially empty
		Tower towerB = new Tower( "B" );
		Tower towerC = new Tower( "C" );

		System.out.println( "Before" );
		System.out.println( towerA );
		System.out.println( towerB );
		System.out.println( towerC );

		// move all disks from tower A to C
		towerA.moveDisks( 5, towerC, towerB );

		System.out.println( "After" );
		System.out.println( towerA );
		System.out.println( towerB );
		System.out.println( towerC );

	}

	private static class Tower {
		private final String name;
		private final Stack<Integer> disks = new Stack<>();


		public Tower ( String name ) {
			super();
			this.name = name;
		}


		public void addDisk ( int n ) {
			if ( !this.disks.isEmpty() && this.disks.peek() <= n ) {
				throw new IllegalStateException( "Error adding disk to tower!" );
			}
			else {
				this.disks.push( n );
			}
		}


		@Override
		public String toString () {
			StringBuilder sb = new StringBuilder();
			sb.append( "name=" + this.name + "," );
			sb.append( "disks=" + Arrays.toString( this.disks.toArray() ) );
			return sb.toString();
		}


		public void moveDisks ( int numOfDisks, Tower destinationTower, Tower spareTower ) {
			if ( numOfDisks > 0 ) {
				// move all disks but the last to spare tower
				this.moveDisks( numOfDisks - 1, spareTower, destinationTower );

				// then move top disk to destination tower
				Integer n = this.disks.pop();
				destinationTower.addDisk( n );

				// then move all disks from spare tower to destination tower
				spareTower.moveDisks( numOfDisks - 1, destinationTower, this );
			}
			else {
				return;
			}
		}

	}
}
