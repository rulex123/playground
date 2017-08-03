
package exercises.linkedlist;

public class Node {
	public int data;
	public Node next;


	public Node ( int data ) {
		this.data = data;
	}


	public void appendToTail ( int data ) {
		Node end = new Node( data );
		Node n = this;
		while ( n.next != null ) {
			n = n.next; // get to last node
		}
		n.next = end;
	}


	@Override
	public String toString () {
		return "Node [data=" + this.data + ", next=" + this.next.data + "]";
	}


	public void appendToTail ( Node node ) {
		Node n = this;
		while ( n.next != null ) {
			n = n.next; // get to last node
		}
		n.next = node;
	}


	public void print () {
		StringBuilder sb = new StringBuilder();
		Node n = this;
		while ( n != null ) {
			sb.append( n.data );
			sb.append( " -> " );
			n = n.next; // get to last node
		}
		sb.append( "null" );
		System.out.println( sb.toString() );
	}
}