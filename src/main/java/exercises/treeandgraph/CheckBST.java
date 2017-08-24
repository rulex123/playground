
package exercises.treeandgraph;

/**
 * Implement a function to check if a binary tree is a binary search tree.
 * 
 * @author emanno
 * @version 1.0 Aug 21, 2017
 */
public class CheckBST {

	public static void main ( String[] args ) {
		/*
		 *           12
		 *         /    \
		 *        8      15
		 *       / \    /  \
		 *      1   10 13   18
		 */

		TreeNode node_8 = new TreeNode( 8, new TreeNode( 1 ), new TreeNode( 10 ) );
		TreeNode node_15 = new TreeNode( 15, new TreeNode( 13 ), new TreeNode( 18 ) );
		TreeNode root = new TreeNode( 12, node_8, node_15 );

		System.out.println( isBST( root, null, null ) );

		/*
		 *           12
		 *         /    \
		 *        8      15
		 *       / \    /  \
		 *      1   10 11   18
		 */

		node_8 = new TreeNode( 8, new TreeNode( 1 ), new TreeNode( 10 ) );
		node_15 = new TreeNode( 15, new TreeNode( 11 ), new TreeNode( 18 ) );
		root = new TreeNode( 12, node_8, node_15 );

		System.out.println( isBST( root, null, null ) );

		/*
		 *           12
		 *         /    \
		 *        8      15
		 *       / \    /  \
		 *      1   8 13   18
		 */

		node_8 = new TreeNode( 8, new TreeNode( 1 ), new TreeNode( 8 ) );
		node_15 = new TreeNode( 15, new TreeNode( 13 ), new TreeNode( 18 ) );
		root = new TreeNode( 12, node_8, node_15 );

		System.out.println( isBST( root, null, null ) );

		/*
		 *           12
		 *         /    \
		 *        8      15
		 *       / \    /  \
		 *      8   10 13   18
		 */

		node_8 = new TreeNode( 8, new TreeNode( 8 ), new TreeNode( 10 ) );
		node_15 = new TreeNode( 15, new TreeNode( 13 ), new TreeNode( 18 ) );
		root = new TreeNode( 12, node_8, node_15 );

		System.out.println( isBST( root, null, null ) );
	}


	public static boolean isBST ( TreeNode node, Integer min, Integer max ) {
		if ( node == null )
			return true;

		if ( min != null && node.data <= min )
			return false;

		if ( max != null && node.data > max )
			return false;

		return isBST( node.leftChild, min, node.data ) && isBST( node.rightChild, node.data, max );
	}

}
