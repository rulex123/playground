package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Find the distance between two keys in a binary tree, no parent pointers are given. Distance between two nodes is the
 * minimum number of edges to be traversed to reach one node from other.
 *
 * @author emanno
 * @version 1.0 Jan 31, 2018
 */
public class ShortestDistanceBetweenNodesInBinaryTree {

    public static void main(String[] args) {
        /*
         * @formatter:off
         *
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

        TreeNode node_3 = new TreeNode(3, new TreeNode(4), NodeType.RIGHT);
        TreeNode node_21 = new TreeNode(21, node_3, NodeType.RIGHT);
        TreeNode node_9 = new TreeNode(9, node_21, NodeType.RIGHT);
        TreeNode node_7 = new TreeNode(7, new TreeNode(1), new TreeNode(15));
        TreeNode root = new TreeNode(6, node_7, node_9);

        System.out.println(shortestDistanceBetweenNodes(root, node_9, node_7));
        System.out.println(shortestDistanceBetweenNodes(root, node_7, node_21));
        System.out.println(shortestDistanceBetweenNodes(root, node_3, node_7));
        System.out.println(shortestDistanceBetweenNodes(root, node_9, node_21));

    }

    private static int shortestDistanceBetweenNodes(TreeNode startNode, TreeNode node1, TreeNode node2) {
        TreeNode firstCommonAncestor = FirstCommonAncestor.firstCommonAncestor(startNode, node1, node2);

        if (firstCommonAncestor == null) {
            return -1; // either one or both node1 and node2 are not in the tree
        }

        return findDistance(firstCommonAncestor, node1, 0) + findDistance(firstCommonAncestor, node2, 0);
    }

    public static int findDistance(TreeNode startNode, TreeNode to, int distance) {
        if (startNode == null) {
            return -1; // we hit a leaf node and didn't find the node we are looking for
        }

        if (startNode == to) {
            return distance; // we found the node we are looking for
        }

        int distanceInLeftSubtree = findDistance(startNode.leftChild, to, distance + 1);
        int distanceInRightSubtree = findDistance(startNode.rightChild, to, distance + 1);

        if (distanceInLeftSubtree > -1) {
            return distanceInLeftSubtree;
        } else {
            return distanceInRightSubtree;
        }
    }

}
