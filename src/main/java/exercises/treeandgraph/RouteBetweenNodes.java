
package exercises.treeandgraph;

import java.util.LinkedList;
import java.util.Queue;

import exercises.treeandgraph.Graph.Node;
import exercises.treeandgraph.Graph.Node.Status;

/**
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 *
 * @author emanno
 * @version 1.0 Aug 20, 2017
 */
public class RouteBetweenNodes {

	public static void main ( String[] args ) {
		Graph<Integer> graph = new Graph<>();
		graph.addEdge( 0, 1 );
		graph.addEdge( 0, 4 );
		graph.addEdge( 0, 5 );
		graph.addEdge( 1, 4 );
		graph.addEdge( 1, 3 );
		graph.addEdge( 3, 2 );
		graph.addEdge( 3, 4 );
		graph.addEdge( 2, 1 );

		System.out.println( routeBetweenNodes( graph.getNode( 0 ), graph.getNode( 2 ) ) );
		System.out.println( routeBetweenNodes( graph.getNode( 2 ), graph.getNode( 5 ) ) );

	}


	public static boolean routeBetweenNodes ( Node<Integer> sourceNode, Node<Integer> destinationNode ) {
		if ( sourceNode == destinationNode )
			return true;

		// use breadth-first search to find path (if any)
		Queue<Node<Integer>> nodes = new LinkedList<>();
		sourceNode.setStatus( Status.QUEUED_FOR_VISITING );
		nodes.offer( sourceNode );

		while ( !nodes.isEmpty() ) {
			Node<Integer> currNode = nodes.poll();
			if ( currNode == destinationNode )
				return true;
			else
				currNode.setStatus( Status.VISITED );

			for ( Node<Integer> child : currNode.getChildren() ) {
				if ( child.getStatus() == Status.NOT_VISITED ) {
					child.setStatus( Status.QUEUED_FOR_VISITING );
					nodes.offer( child );
				}
			}
		}

		return false;
	}

}
