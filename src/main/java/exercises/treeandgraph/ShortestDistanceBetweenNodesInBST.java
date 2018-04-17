
package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Given a Binary Search Tree and two nodes in it, find the distance between the two nodes. It may be assumed that both
 * keys exist in BST.
 *
 * @author emanno
 * @version 1.0 Jan 31, 2018
 */
public class ShortestDistanceBetweenNodesInBST {

	public static void main ( String[] args ) {
		/*
		 * @formatter:off
		 *
		 *           6
		 *         /   \
		 *        3     9
		 *       / \   / \
		 *      1   5 7   10
		 *      		 			 \
		 *					        11
		 *
		 */

		TreeNode node_3 = new TreeNode( 3, new TreeNode( 1 ), new TreeNode(5) );
		TreeNode node_10 = new TreeNode( 10, new TreeNode( 11 ), NodeType.RIGHT );
		TreeNode node_9 = new TreeNode( 9, new TreeNode(7), node_10 );
		TreeNode root = new TreeNode( 6, node_3, node_9 );

		System.out.println( shortestDistanceBetweenNodes( root, node_3, node_10 ) ); // expected 3
		System.out.println( shortestDistanceBetweenNodes( root, root, node_10 ) ); // expected 2

	}

	private static int shortestDistanceBetweenNodes(TreeNode startNode, TreeNode node1, TreeNode node2) {
		if (node1.data < startNode.data && node2.data < startNode.data ) {
			return shortestDistanceBetweenNodes( startNode.leftChild, node1, node2 ); // both nodes are in the left subtree
		} else if (node1.data > startNode.data && node2.data > startNode.data) {
			return shortestDistanceBetweenNodes( startNode.rightChild, node1, node2 ); // both nodes are in the right subtree
		} else {
			// nodes are in different subtrees, so current node is first common ancestor
			return ShortestDistanceBetweenNodesInBinaryTree.findDistance( startNode, node1, 0 ) + ShortestDistanceBetweenNodesInBinaryTree.findDistance( startNode, node2, 0 );
		}
	}

}
