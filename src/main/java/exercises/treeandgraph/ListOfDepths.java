
package exercises.treeandgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (i.e. if you have
 * a tree with depth D, you'll have D linked lists)
 *
 * @author emanno
 * @version 1.0 Jan 31, 2018
 */
public class ListOfDepths {

	public static void main ( String[] args ) {

		/*
		*           8
		*         /   \
		*        4     9
		*       / \     \
		*      2   6     11
		*/
		TreeNode node_4 = new TreeNode( 4, new TreeNode( 2 ), new TreeNode( 6 ) );
		TreeNode node_9 = new TreeNode( 9, new TreeNode( 11 ), NodeType.RIGHT );
		TreeNode root = new TreeNode( 8, node_4, node_9 );

		List<LinkedList<TreeNode>> levels = listOfDepths( root );

		for ( LinkedList<TreeNode> level : levels ) {
			System.out.println( Arrays.toString( level.toArray() ) );
		}

	}


	public static List<LinkedList<TreeNode>> listOfDepths ( TreeNode root ) {

		List<LinkedList<TreeNode>> allLevels = new ArrayList<>();

		LinkedList<TreeNode> currentLevel = new LinkedList<>();
		if ( root != null )
			currentLevel.add( root ); // root level

		while ( !currentLevel.isEmpty() ) {
			allLevels.add( currentLevel ); // add current level to list of levels

			LinkedList<TreeNode> parents = currentLevel;
			currentLevel = new LinkedList<>(); // re-initialize for a new level

			// visit all nodes from previous level and add children to current level
			for ( TreeNode parentNode : parents ) {
				if ( parentNode.leftChild != null )
					currentLevel.add( parentNode.leftChild );
				if ( parentNode.rightChild != null )
					currentLevel.add( parentNode.rightChild );
			}
		}

		return allLevels;
	}

}
