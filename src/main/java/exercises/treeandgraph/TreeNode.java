
package exercises.treeandgraph;

public class TreeNode {

	enum NodeType {
		LEFT, RIGHT;
	}

	int data;
	TreeNode leftChild;
	TreeNode rightChild;
	// usually, you shouldn't assume a node has a link to its parent: this field is
	// included here because one exercise (Successor) uses it
	TreeNode parent;


	public TreeNode ( int data, TreeNode leftChild, TreeNode rightChild ) {
		this.data = data;
		leftChild.parent = this;
		rightChild.parent = this;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}


	public TreeNode ( int data ) {
		this.data = data;
	}


	public TreeNode ( int data, TreeNode child, NodeType childType ) {
		this.data = data;
		child.parent = this;
		if ( childType == NodeType.RIGHT ) {
			this.rightChild = child;
		}
		else { // assuming left for simplicity
			this.leftChild = child;
		}
	}


	@Override
	public String toString () {
		return "TreeNode [data=" + this.data + "]";
	}

}
