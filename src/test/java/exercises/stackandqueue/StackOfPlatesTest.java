
package exercises.stackandqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackOfPlatesTest {

    private StackOfPlates<Integer> unit;

    @Before
    public void setUp() {
        this.unit = new StackOfPlates<>(2);
        this.unit.push(13);
        this.unit.push(-2);

        this.unit.push(4);
        this.unit.push(3);

        this.unit.push(25);
        this.unit.push(11);
    }


    @Test
    public void test_pop() {
        // pop first 3 elements
        assertThat(this.unit.pop(), is(11));
        assertThat(this.unit.pop(), is(25));
        assertThat(this.unit.pop(), is(3));

        // pop all other elements
        for (int i = 0; i < 3; i++) {
            this.unit.pop();
        }

        try {
            this.unit.pop();
            Assert.fail();
        } catch (NullPointerException npe) {
            // correct behavior
        }
    }


    @Test
    public void test_popAt() {
        assertThat(this.unit.popAt(1), is(3));
        assertThat(this.unit.popAt(1), is(4));
        assertThat(this.unit.pop(), is(25));
    }

}
