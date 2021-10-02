package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

/**
 * Gien a tree, write a function to find the length of the longest branch of nodes in increasing consecutive order.
 *
 * @author emanno
 * @version 1.0 Jul 6, 2017
 */
public class LongestConsecutiveBranch {

    public static void main(String[] args) {

        /*
         * @formatter:off
         *
         * 				6
         * 			/		\
         * 		 7		 2
         * 		/ \			\
         *   3   2		 4
         * 			/	\
         * 		 3	 4
         * 		/
         * 		4
         *  	 \
         *      5
         *
         */

        TreeNode node_3 = new TreeNode(3, new TreeNode(4, new TreeNode(5), NodeType.RIGHT), NodeType.LEFT);
        TreeNode node_2 = new TreeNode(2, node_3, new TreeNode(4));
        TreeNode node_7 = new TreeNode(7, new TreeNode(3), node_2);
        TreeNode root = new TreeNode(6, node_7, new TreeNode(2, new TreeNode(4), NodeType.RIGHT));

        System.out.println(longestConsecBranch(root));
    }

    public static int longestConsecBranch(TreeNode node) {
        return max(longestConsecBranch(node.leftChild, node.data, 1), longestConsecBranch(node.rightChild, node.data, 1));
    }


    public static int longestConsecBranch(TreeNode node, int prevData, int length) {
        if (node == null)
            return length;

        if (node.data == prevData + 1) {
            int leftLength = longestConsecBranch(node.leftChild, node.data, length + 1);
            int rightLength = longestConsecBranch(node.rightChild, node.data, length + 1);
            return max(leftLength, rightLength);
        } else {
            int leftLength = longestConsecBranch(node.leftChild, node.data, 1);
            int rightLength = longestConsecBranch(node.rightChild, node.data, 1);
            return max(leftLength, rightLength, length);
        }
    }


    private static int max(int... values) {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max)
                max = value;
        }

        return max;
    }

}
