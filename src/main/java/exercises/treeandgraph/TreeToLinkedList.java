
package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Given a tree, write a function to convert it into a circular doubly linked list from left to right by only modifying
 * the existing pointers.
 *
 * @author emanno
 * @version 1.0 Jul 5, 2017
 */
public class TreeToLinkedList {


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
		TreeNode node = treeToLinkedList( root );

		TreeNode startedAt = node;
		while ( true ) {
			System.out.println( node.data );
			if ( node.rightChild == startedAt )
				break;
			else
				node = node.rightChild;
		}
	}


	public static TreeNode treeToLinkedList ( TreeNode node ) {
		if ( node == null )
			return null;

		TreeNode leftList = treeToLinkedList( node.leftChild );
		TreeNode rightList = treeToLinkedList( node.rightChild );
		node.leftChild = node;
		node.rightChild = node;

		node = createCircularLinkedList( leftList, node );
		node = createCircularLinkedList( node, rightList );

		return node;
	}


	private static TreeNode createCircularLinkedList ( TreeNode nodeA, TreeNode nodeB ) {
		if ( nodeA == null )
			return nodeB;
		if ( nodeB == null )
			return nodeA;

		TreeNode aEnd = nodeA.leftChild;
		TreeNode bEnd = nodeB.leftChild;

		aEnd.rightChild = nodeB;
		nodeB.leftChild = aEnd;
		nodeA.leftChild = bEnd;
		bEnd.rightChild = nodeA;

		return nodeA;
	}

}
