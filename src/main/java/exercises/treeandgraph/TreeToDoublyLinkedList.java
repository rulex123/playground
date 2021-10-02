package exercises.treeandgraph;

/**
 * Given a tree, write a function to convert it into a circular doubly linked list from left to
 * right by only modifying the existing pointers.
 * <p>
 * e.g.
 * 8
 * /   \
 * 4     9
 * / \     \
 * 2   6     11
 * <p>
 * yields <-> 2 <-> 4 <-> 6 <-> 8 <-> 9 <-> 11 <->
 */
public class TreeToDoublyLinkedList {

    public static void main(String[] args) {
        TreeNode node_4 = new TreeNode(4, new TreeNode(2), new TreeNode(6));
        TreeNode node_9 = new TreeNode(9, new TreeNode(11), TreeNode.NodeType.RIGHT);
        TreeNode root = new TreeNode(8, node_4, node_9);
        treeToDoublyLinkedList(root);

        TreeNode startedAt = root;
        while (true) {
            System.out.println(root.leftChild.data + "<-" + root.data + "->" + root.rightChild.data);
            if (root.rightChild == startedAt) {
                break;
            } else {
                root = root.rightChild;
            }
        }
    }

    static void treeToDoublyLinkedList(TreeNode node) {
        if (node == null) {
            return; // nothing to do
        }
        TreeNode[] nodes = treeToDoublyLinkedListRec(node);
        // join the 2 outermost nodes
        nodes[0].leftChild = nodes[1];
        nodes[1].rightChild = nodes[0];
    }

    static TreeNode[] treeToDoublyLinkedListRec(TreeNode node) {
        if (node == null) {
            // base case, end recursion
            return new TreeNode[]{null, null};
        }

        // left subtree
        TreeNode[] leftSubtree = treeToDoublyLinkedListRec(node.leftChild);
        // right subtree
        TreeNode[] rightSubtree = treeToDoublyLinkedListRec(node.rightChild);

        // join subtrees with current node
        if (leftSubtree[1] != null) {
            leftSubtree[1].rightChild = node;
            node.leftChild = leftSubtree[1];
        }
        if (rightSubtree[0] != null) {
            rightSubtree[0].leftChild = node;
            node.rightChild = rightSubtree[0];
        }

        // pick outermost nodes to return
        TreeNode leftMostNode = leftSubtree[0] == null ? node : leftSubtree[0];
        TreeNode rightMostNode = rightSubtree[1] == null ? node : rightSubtree[1];

        return new TreeNode[]{leftMostNode, rightMostNode};
    }
}
