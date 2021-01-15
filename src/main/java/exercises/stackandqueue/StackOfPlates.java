
package exercises.stackandqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would
 * likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that
 * mimics this. SetOfStacks is composed of several stacks and should create a new stack once the previous one exceeds
 * capacity. SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack (that is, pop() should
 * return the same values as it would if there were just a single stack).
 *
 * @author emanno
 * @version 1.0 Apr 26, 2017
 */
public class StackOfPlates<T> {

	private final int threshold;

	List<MyStack<T>> listOfStacks = new ArrayList<>();


	public StackOfPlates(int threshold ) {
		super();
		this.threshold = threshold;
	}


	public void push ( T item ) {
		if ( this.listOfStacks.isEmpty() ) {
			// create new stack
			this.listOfStacks.add( new MyStack<>() );
		}

		if ( this.getLastStack().size() == this.threshold ) {
			// roll-over current stack if full
			this.listOfStacks.add( new MyStack<>() );
		}

		// push element onto stack
		this.getLastStack().push( item );
	}


	public T pop () {
		MyStack<T> lastStack = this.getLastStack();
		if ( lastStack == null ) {
			throw new NullPointerException( "there are no stacks!" );
		}

		T element = lastStack.pop();

		// check if we popped the last element of the last stack
		if ( lastStack.isEmpty() ) {
			this.removeLastStack();
		}

		return element;
	}


	public T popAt ( int index ) {
		if ( index < 0 || index > this.listOfStacks.size() - 1 )
			throw new IllegalArgumentException( "invalid index value: " + index );

		T element = this.listOfStacks.get( index ).pop();

		// check if we popped last element of stack with given index
		if ( this.listOfStacks.get( index ).isEmpty() ) {
			this.removeStackAt( index );
		}
		else {
			this.leftShift( index );
		}

		return element;
	}


	private void leftShift ( int index ) {
		if ( index < this.listOfStacks.size() - 1 ) {
			// take bottom of next stack and insert it at bottom of this stack
			T bottomOfNextStack = this.removeBottomValueFromStack( index + 1 );
			this.insertAtBottomOfStack( index, bottomOfNextStack );
			// remove next stack if empty
			if ( this.listOfStacks.get( index + 1 ).isEmpty() )
				this.removeStackAt( index + 1 );
			this.leftShift( index + 1 );
		}
	}


	private void insertAtBottomOfStack ( int index, T element ) {
		MyStack<T> stack = this.listOfStacks.get( index );
		if ( stack.isEmpty() ) {
			stack.push( element );
		}
		else {
			T poppedValue = stack.pop();
			this.insertAtBottomOfStack( index, element );
			stack.push( poppedValue );
		}
	}


	private T removeBottomValueFromStack ( int index ) {
		T valueRemoved = null;
		MyStack<T> stack = this.listOfStacks.get( index );
		if ( stack.size() == 1 ) {
			valueRemoved = stack.pop();
		}
		else {
			T poppedValue = stack.pop();
			valueRemoved = this.removeBottomValueFromStack( index );
			stack.push( poppedValue );
		}
		return valueRemoved;
	}


	private MyStack<T> getLastStack () {
		if ( this.listOfStacks.isEmpty() )
			return null;
		return this.listOfStacks.get( this.listOfStacks.size() - 1 );
	}


	private void removeLastStack () {
		if ( this.listOfStacks.isEmpty() )
			return;
		this.listOfStacks.remove( this.listOfStacks.size() - 1 );
	}


	private void removeStackAt ( int index ) {
		this.listOfStacks.remove( index );
	}
}
