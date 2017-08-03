
package exercises.treeandgraph;

import java.util.Stack;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing
 * additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.
 *
 * @author emanno
 * @version 1.0 Jul 3, 2017
 */
public class FirstCommonAncestor {

	public static void main ( String[] args ) {
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
		TreeNode node_9 = new TreeNode( 9, node_21, NodeType.RIGHT );
		TreeNode node_7 = new TreeNode( 7, new TreeNode( 1 ), new TreeNode( 15 ) );
		TreeNode root = new TreeNode( 6, node_7, node_9 );

		System.out.println( firstCommonAncestor( root, node_7, node_3 ).data ); // result: 6
		System.out.println( firstCommonAncestor( root, node_21, node_9 ).data ); // result: 9
		System.out.println( firstCommonAncestor( root, node_7, new TreeNode( 32 ) ) ); // result: null

	}


	public static TreeNode firstCommonAncestor ( TreeNode root, TreeNode nodeA, TreeNode nodeB ) {
		if ( nodeA.equals( nodeB ) )
			return nodeA;

		Stack<TreeNode> pathToNodeA = pathToNode( root, nodeA );
		Stack<TreeNode> pathToNodeB = pathToNode( root, nodeB );

		if ( pathToNodeA == null || pathToNodeB == null )
			// either one or both given nodes are not in the tree, so they
			// don't have a common ancestor
			return null;

		TreeNode prev = null;
		while ( !pathToNodeA.isEmpty() && !pathToNodeB.isEmpty() ) {
			// both nodes are in the tree
			TreeNode nA = pathToNodeA.pop();
			TreeNode nB = pathToNodeB.pop();

			if ( nA.equals( nB ) )
				prev = nA;
			else
				break;
		}

		return prev;
	}


	private static Stack<TreeNode> pathToNode ( TreeNode root, TreeNode node ) {
		if ( root == null )
			return null;

		if ( root.equals( node ) ) {
			Stack<TreeNode> path = new Stack<>();
			path.push( node );
			return path;
		}

		Stack<TreeNode> pathLeft = pathToNode( root.leftChild, node );
		Stack<TreeNode> pathRight = pathToNode( root.rightChild, node );

		if ( pathLeft != null ) {
			pathLeft.push( root );
			return pathLeft;
		}

		if ( pathRight != null ) {
			pathRight.push( root );
			return pathRight;
		}

		// node not found
		return null;
	}

}
