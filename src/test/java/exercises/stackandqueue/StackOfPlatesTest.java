
package exercises.stackandqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackOfPlatesTest {

	private StackOfPlates<Integer> unit;


	@Before
	public void setUp () {
		this.unit = new StackOfPlates<>( 2 );
		this.unit.push( 13 );
		this.unit.push( -2 );

		this.unit.push( 4 );
		this.unit.push( 3 );

		this.unit.push( 25 );
		this.unit.push( 11 );
	}


	@Test
	public void test_pop () {
		// pop first 3 elements
		Assert.assertEquals( new Integer( 11 ), this.unit.pop() );
		Assert.assertEquals( new Integer( 25 ), this.unit.pop() );
		Assert.assertEquals( new Integer( 3 ), this.unit.pop() );

		// pop all other elements
		for ( int i = 0; i < 3; i++ ) {
			this.unit.pop();
		}

		try {
			this.unit.pop();
			Assert.fail();
		}
		catch ( NullPointerException npe ) {
			// correct behavior
		}
	}


	@Test
	public void test_popAt () {
		Assert.assertEquals( new Integer( 3 ), this.unit.popAt( 1 ) );
		Assert.assertEquals( new Integer( 4 ), this.unit.popAt( 1 ) );

		Assert.assertEquals( new Integer( 25 ), this.unit.pop() );
		Assert.assertEquals( new Integer( 11 ), this.unit.pop() );

	}

}
