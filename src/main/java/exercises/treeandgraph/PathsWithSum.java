package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value (which might be positive or negative).
 * Design an algorithm to count the number of paths that sum to a given value. The path does not need to start or end at
 * the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 */
public class PathsWithSum {

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

        System.out.println(countPathsWithSum(root, 9));

        System.out.println(rootToLeafPath(root, 12)); // expected true (6->2->4)
        System.out.println(rootToLeafPath(root, 19)); // expected true (6->7->2->4)
        System.out.println(rootToLeafPath(root, 13)); // expected false
        System.out.println(rootToLeafPath(root, 6)); // expected false


    }


    public static int countPathsWithSum(TreeNode node, int targetSum) {
        return countPathsWithSum(node, targetSum, 0, new HashMap<>());
    }


    public static int countPathsWithSum(TreeNode node, int targetSum, int runningSum,
                                        Map<Integer, Integer> countOfPaths) {
        if (node == null)
            return 0;

        // calculate new running sum at current node
        runningSum = runningSum + node.data;

        // given running sum, calculate number missing to get to target sum
        int missing = runningSum - targetSum;

        // how many paths have we seen leading to the missing number?
        int totalPaths = countOfPaths.getOrDefault(missing, 0);

        // increment number of paths if current node equals target sum
        if (runningSum == targetSum)
            totalPaths++;

        updateCountOfPath(runningSum, countOfPaths, 1);
        totalPaths += countPathsWithSum(node.leftChild, targetSum, runningSum, countOfPaths);
        totalPaths += countPathsWithSum(node.rightChild, targetSum, runningSum, countOfPaths);
        updateCountOfPath(runningSum, countOfPaths, -1);

        return totalPaths;
    }


    private static void updateCountOfPath(int key, Map<Integer, Integer> countOfPaths, int delta) {
        int newCount = countOfPaths.getOrDefault(key, 0) + delta;
        if (newCount <= 0) {
            // eliminate from hash map in case count goes to 0 or less
            countOfPaths.remove(key);
        } else {
            countOfPaths.put(key, newCount);
        }
    }

    // --------------------------------------------------------------------------------


    /**
     * Given a binary tree, find if there is a path from root to leaf which sums to a given value.
     */
    public static boolean rootToLeafPath(TreeNode node, int targetSum) {
        // base case: leaf node
        if (node.leftChild == null && node.rightChild == null)
            return node.data == targetSum;

        // not a leaf node
        if (node.leftChild != null && node.rightChild != null)
            return rootToLeafPath(node.leftChild, targetSum - node.data) || rootToLeafPath(node.rightChild, targetSum - node.data);
        else if (node.leftChild != null) {
            return rootToLeafPath(node.leftChild, targetSum - node.data);
        } else {
            return rootToLeafPath(node.rightChild, targetSum - node.data);
        }
    }

}
