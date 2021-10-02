package interviewcake.treeandgraph;

/**
 * Write a method to find the 2nd largest element in a binary search tree.
 */
public class SecondLargestItemInBST {

    public static void main(String[] args) {
        /*
         *           12
         *         /    \
         *        8      15
         *       / \    /  \
         *      1   10 13   18
         */

        Node node_8 = new Node(8, new Node(1), new Node(10));
        Node node_15 = new Node(15, new Node(13), new Node(18));
        Node root = new Node(12, node_8, node_15);

        System.out.println(secondLargestItemInBST(root));
    }

    static int secondLargestItemInBST(Node node) {
        // protect against cases where there is no tree, or only a root node
        if (node == null || node.isLeafNode()) {
            throw new IllegalArgumentException("must have at least two nodes!");
        }

        return secondLargestItemInBSTRec(node, null);
    }

    private static int secondLargestItemInBSTRec(final Node node, final Node parentNode) {
        if (node.isLeafNode()) {
            // if we have reached a leaf node, then we have found the largest item: return
            // the parent node, which will contain the 2nd largest item
            return parentNode.data;
        }
        if (node.rightChild == null) {
            // if there is no right subtree, then current node is the largest item, so the
            // 2nd largest item is the largest item in the left subtree
            return largestItemInBST(node.leftChild);
        } else {
            // if there is a right subtree, then keep searching there
            return secondLargestItemInBSTRec(node.rightChild, node);
        }
    }

    private static int largestItemInBST(final Node node) {
        if (node.rightChild != null) {
            return largestItemInBST(node.rightChild);
        }
        return node.data;
    }

    static class Node {

        int data;
        Node rightChild;
        Node leftChild;

        private Node(final int data, final Node leftChild,
                     final Node rightChild) {
            this.data = data;
            this.rightChild = rightChild;
            this.leftChild = leftChild;
        }

        private Node(final int data) {
            this.data = data;
        }

        boolean isLeafNode() {
            return rightChild == null && leftChild == null;
        }
    }
}
