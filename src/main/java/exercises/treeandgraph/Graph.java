
package exercises.treeandgraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic implementation of the graph data structure (using an adjacency list).
 *
 * @author emanno
 * @version 1.0 Aug 20, 2017
 */
public class Graph<T> {

	private final Map<T, Node<T>> nodesLookup = new HashMap<>();


	public void addNode ( T value ) {
		if ( !this.nodesLookup.containsKey( value ) ) {
			Node<T> node = new Node<>( value );
			this.nodesLookup.put( value, node );
		}
	}


	public Node<T> getNode ( T value ) {
		if ( !this.nodesLookup.containsKey( value ) )
			throw new NullPointerException();
		return this.nodesLookup.get( value );
	}


	public void addEdge ( T source, T destination ) {
		Node<T> sourceNode = this.nodesLookup.get( source );
		if ( sourceNode == null ) {
			sourceNode = new Node<T>( source );
			this.nodesLookup.put( source, sourceNode );
		}

		Node<T> destNode = this.nodesLookup.get( destination );
		if ( destNode == null ) {
			destNode = new Node<T>( destination );
			this.nodesLookup.put( destination, destNode );
		}

		sourceNode.children.add( destNode );
	}


	public Collection<Node<T>> getNodes () {
		return this.nodesLookup.values();
	}

	public static class Node<T> {

		public static enum Status {
			VISITED, NOT_VISITED, QUEUED_FOR_VISITING, VISITING
		}

		private final T value;
		private final List<Node<T>> children;
		private Status status;


		public Node ( T value ) {
			this.value = value;
			this.children = new ArrayList<>();
			this.status = Status.NOT_VISITED;
		}


		public Status getStatus () {
			return this.status;
		}


		public void setStatus ( Status status ) {
			this.status = status;
		}


		public List<Node<T>> getChildren () {
			return this.children;
		}


		public T getValue () {
			return this.value;
		}


		@Override
		public String toString () {
			return "Node [value=" + this.value + ", children=" + this.children + "]";
		}

	}

}
