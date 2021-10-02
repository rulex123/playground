package exercises.treeandgraph;

import java.util.Stack;

/**
 * Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST (without
 * changing the nodes that are already in the correct position).
 */
public class RecoverBST {

    /*
     *           12
     *         /    \
     *        15      8
     *       / \    /  \
     *      1   10 13   18
     */
    public static void main(String[] args) {
        TreeNode node_15 = new TreeNode(15, new TreeNode(1), new TreeNode(10));
        TreeNode node_8 = new TreeNode(8, new TreeNode(13), new TreeNode(18));
        TreeNode root = new TreeNode(12, node_15, node_8);

        recoverBST(root);
    }

    private static void recoverBST(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(node);

        TreeNode firstOutOfOrder = null;
        TreeNode lastOutOfOrder = null;
        TreeNode prevVisitedNode = null;

        while (!nodes.isEmpty()) {
            TreeNode currNode = nodes.peek();

            if (currNode.left != null && !currNode.left.visited) {
                nodes.push(currNode.left);
                continue;
            }

            currNode.visited();
            nodes.pop();

            if (prevVisitedNode != null && prevVisitedNode.value > currNode.value) {
                if (firstOutOfOrder == null) {
                    firstOutOfOrder = prevVisitedNode;
                }
                lastOutOfOrder = currNode;
            }

            prevVisitedNode = currNode;

            if (currNode.right != null) {
                nodes.push(currNode.right);
            }
        }

        System.out.println(
                "Found nodes out of order: " + firstOutOfOrder.value + " and " + lastOutOfOrder.value);
        System.out.println("Swapping them");

        // swapping out of order nodes
        int temp = firstOutOfOrder.value;
        firstOutOfOrder.value = lastOutOfOrder.value;
        lastOutOfOrder.value = temp;
    }

    static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int value;
        public boolean visited;

        private TreeNode(final int value, final TreeNode left, final TreeNode right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        private TreeNode(final int value) {
            this.value = value;
        }

        public void visited() {
            this.visited = true;
        }
    }
}
