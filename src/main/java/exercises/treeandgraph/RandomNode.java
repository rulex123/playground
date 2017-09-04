
package exercises.treeandgraph;

import java.util.Random;

/**
 * You are implementing a binary search tree class from scratch, which, in addition to insert, find, and delete, has a
 * method getRandomNode() which returns a random tree from the tree. All nodes should be equally likely to be chosen.
 * Design and implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods.
 *
 * @author emanno
 * @version 1.0 Aug 24, 2017
 */
public class RandomNode {

	private static class Node {
		int data;
		Node left;
		Node right;
		int childrenCount;


		Node ( int value ) {
			this.data = value;
		}


		private int leftChildren () {
			if ( this.left == null )
				return 0;
			return this.left.childrenCount + 1;
		}


		private void resetValue ( int value ) {
			this.data = value;
		}


		private boolean isLeftChildOf ( Node parentNode ) {
			return parentNode.left != null && parentNode.left == this;
		}


		private boolean isRightChildOf ( Node parentNode ) {
			return parentNode.right != null && parentNode.right == this;
		}


		private void resetChild ( Node oldChild, Node newChild ) {
			if ( oldChild.isLeftChildOf( this ) ) {
				this.left = newChild;
			}
			else if ( oldChild.isRightChildOf( this ) ) {
				this.right = newChild;
			}
		}

	}

	public static class BinarySearchTree {
		Node root;


		public void insertNode ( int value ) {
			if ( this.root == null ) {
				this.root = new Node( value );
			}
			else {
				this.insertNode( this.root, value );
			}
		}


		public void deleteNode ( int value ) {
			if ( this.root == null )
				throw new NullPointerException();

			if ( this.root.data == value ) {
				if ( this.root.childrenCount == 0 ) { // special case: root is being deleted, and there are no other nodes
					this.root = null;
				}
				else if ( this.root.leftChildren() == 1 ) { // special case: root is being deleted, and tree has only a left
																										// subtree
					this.root = this.root.left;
				}
			}

			this.deleteNode( null, this.root, value );
		}


		public Node findNode ( int value ) {
			return this.findNode( this.root, value );
		}


		public Node getRandomNode () {
			if ( this.root == null )
				throw new NullPointerException();

			int randomIndex = new Random().nextInt( this.root.childrenCount + 1 );

			System.out.println( "the magic index is " + randomIndex );

			return this.getRandomNode( this.root, randomIndex );
		}


		public void print () {
			this.print( this.root );
		}


		// --------------- overloaded private methods ------------

		private void insertNode ( Node currNode, int value ) {
			if ( currNode.data == value )
				return; // node already exists

			if ( value > currNode.data ) {
				if ( currNode.right == null ) {
					currNode.right = new Node( value );
					currNode.childrenCount += 1;
				}
				else {
					if ( currNode.right.data != value ) // defend against duplicate value at next level
						currNode.childrenCount += 1;
					this.insertNode( currNode.right, value );
				}
			}
			else {
				if ( currNode.left == null ) {
					currNode.left = new Node( value );
					currNode.childrenCount += 1;
				}
				else {
					if ( currNode.left.data != value ) // defend against duplicate value at next level
						currNode.childrenCount += 1;
					this.insertNode( currNode.left, value );
				}
			}
		}


		private void deleteNode ( Node parentNode, Node currNode, int value ) {
			if ( currNode == null )
				throw new NullPointerException(); // we exhausted options, node was not found

			if ( value == currNode.data ) { // found the node to delete
				if ( currNode.left == null && currNode.right == null ) { // no children
					parentNode.resetChild( currNode, null );
				}
				else if ( currNode.right == null ) { // only one child
					parentNode.resetChild( currNode, currNode.left );
				}
				else if ( currNode.left == null ) { // only one child
					parentNode.resetChild( currNode, currNode.right );
				}
				else { // two children
					int newValue = this.findNextSmallestValue( currNode.right ); // find new value for node
					this.deleteNode( newValue ); // delete node from which new value is obtained
					currNode.resetValue( newValue ); // reset value of currNode
				}
			}
			else if ( value < currNode.data ) {
				this.deleteNode( currNode, currNode.left, value );
			}
			else {
				this.deleteNode( currNode, currNode.right, value );
			}
		}


		private int findNextSmallestValue ( Node currNode ) {
			if ( currNode.left == null )
				return currNode.data;
			return this.findNextSmallestValue( currNode.left );
		}


		private Node findNode ( Node currNode, int value ) {
			if ( currNode == null )
				throw new NullPointerException();

			if ( value == currNode.data )
				return currNode;

			if ( value < currNode.data ) {
				return this.findNode( currNode.left, value );
			}
			else {
				return this.findNode( currNode.right, value );
			}
		}


		private Node getRandomNode ( Node currNode, int randomIndex ) {
			if ( randomIndex == currNode.leftChildren() )
				return currNode;

			if ( randomIndex < currNode.leftChildren() ) {
				// return random node from left subtree
				return this.getRandomNode( currNode.left, randomIndex );
			}
			else {
				// return random node from right subtree
				return this.getRandomNode( currNode.right, randomIndex - currNode.leftChildren() - 1 );
			}
		}


		private void print ( Node currNode ) {
			if ( currNode != null ) {
				this.print( currNode.left );
				System.out.println( currNode.data );
				this.print( currNode.right );
			}
		}

	}


	public static void main ( String[] args ) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insertNode( 5 );
		bst.insertNode( 3 );
		bst.insertNode( 7 );
		bst.insertNode( 2 );
		bst.insertNode( 1 );
		bst.insertNode( 4 );
		bst.insertNode( 6 );
		bst.insertNode( 8 );
		bst.insertNode( 9 );

		for ( int i = 0; i < 10; i++ ) {
			System.out.println( bst.getRandomNode().data );
		}
	}

}
