
package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

public class Successor {

	/**
	 * Write an algorithm to find the "next" node (i.e. in-order successor) of a given node in a binary search tree. You
	 * may assume that each node has a link to its parent.
	 *
	 * @param args
	 */
	public static void main ( String[] args ) {

		/*
		 * 				   16
		 * 				 /     \
		 * 			 11      22
		 *      / \      / \
		 *     8  13    19  25
		 *    / \           /
		 *   5  9          23
		 */

		TreeNode node_13;

		TreeNode node_8 = new TreeNode( 8, new TreeNode( 5 ), new TreeNode( 9 ) );
		TreeNode node_11 = new TreeNode( 11, node_8, node_13 = new TreeNode( 13 ) );
		TreeNode node_25 = new TreeNode( 25, new TreeNode( 23 ), NodeType.LEFT );
		TreeNode node_22 = new TreeNode( 22, new TreeNode( 19 ), node_25 );
		TreeNode root = new TreeNode( 16, node_11, node_22 );

		System.out.println( successor( node_22 ).data ); // result: 23
		System.out.println( successor( node_8 ).data ); // result: 9
		System.out.println( successor( root ).data ); // result: 19
		System.out.println( successor( node_13 ).data ); // result: 16
		System.out.println( successor( node_25 ) ); // result: null

	}


	public static TreeNode successor ( TreeNode node ) {
		if ( node == null )
			return null;

		if ( node.rightChild == null ) {
			// given node has no right child:
			// find the next bigger node traversing the tree
			// upwards
			int nodeValue = node.data;
			while ( node.parent != null && nodeValue > node.parent.data ) {
				node = node.parent;
			}
			if ( node.parent == null ) {
				// given node is rightmost, so has no successor
				return null;
			}
			else {
				return node.parent;
			}
		}
		else {
			// given node has right child: find smallest
			// node in subtree whose parent node is the given node's right child
			node = node.rightChild;
			while ( node.leftChild != null ) {
				node = node.leftChild;
			}
			return node;
		}
	}
}
