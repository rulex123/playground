
package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is
 * defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 *
 * @author emanno
 * @version 1.0 Jul 2, 2017
 */
public class CheckBalanced {

	public static void main ( String[] args ) {

		/*
		 *           8
		 *         /   \
		 *        4     9
		 *       / \     \
		 *      2   6     11
		 *
		 */
		TreeNode node_4 = new TreeNode( 4, new TreeNode( 2 ), new TreeNode( 6 ) );
		TreeNode node_9 = new TreeNode( 9, new TreeNode( 11 ), NodeType.RIGHT );
		TreeNode root = new TreeNode( 8, node_4, node_9 );

		System.out.println( checkBalanced( root ) );


		/*
		 *           6
		 *         /   \
		 *        7     9
		 *       / \     \
		 *      1   15   21
		 *      		 			 \
		 *					        3
		 *									 \
		 *										4
		 */

		TreeNode node_3 = new TreeNode( 3, new TreeNode( 4 ), NodeType.RIGHT );
		TreeNode node_21 = new TreeNode( 21, node_3, NodeType.RIGHT );
		node_9 = new TreeNode( 9, node_21, NodeType.RIGHT );
		TreeNode node_7 = new TreeNode( 7, new TreeNode( 1 ), new TreeNode( 15 ) );
		root = new TreeNode( 6, node_7, node_9 );

		System.out.println( checkBalanced( root ) );
	}


	public static boolean checkBalanced ( TreeNode node ) {
		return computeHeight( node ) > -1;
	}


	private static int computeHeight ( TreeNode node ) {
		if ( node == null )
			return 0; // base case: leaf node

		int heightLeftSubtree = computeHeight( node.leftChild );
		int heightRightSubtree = computeHeight( node.rightChild );

		// if either one of the subtrees is unbalanced,
		// bubble this up to parent tree
		if ( heightLeftSubtree == -1 || heightRightSubtree == -1 )
			return -1;

		// calculate diff in heights (if more than 1, tree is unbalanced
		if ( Math.abs( heightLeftSubtree - heightRightSubtree ) > 1 )
			return -1;

		return (heightLeftSubtree > heightRightSubtree) ? heightLeftSubtree + 1 : heightRightSubtree + 1;
	}

}
