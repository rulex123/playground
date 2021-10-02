package interviewcake.treeandgraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a method to see if a binary tree is "superbalanced" (a new tree property we just made
 * up).
 * <p>
 * A tree is "superbalanced" if the difference between the depths of any two leaf nodes is no
 * greater than one.
 */
public class CheckSuperbalanced {

    public static void main(String[] args) {

        /*
         *           3
         *         /    \
         *        4      2
         *       /
         *      1
         */

        BinaryTreeNode root = new BinaryTreeNode(3);
        root.insertLeft(4).insertLeft(1);
        root.insertRight(2);
        System.out.println(checkSuperbalanced(root));

        /*
         *            3
         *         /    \
         *        4      2
         *       / \
         *      1   9
         *           \
         *           11
         */

        root = new BinaryTreeNode(3);
        BinaryTreeNode node4 = root.insertLeft(4);
        node4.insertLeft(1);
        node4.insertRight(9).insertRight(11);
        root.insertRight(2);
        System.out.println(checkSuperbalanced(root));
    }

    static boolean checkSuperbalanced(BinaryTreeNode node) {
        if (node == null) {
            // a tree with no nodes is superbalanced, since there are no leaves
            return true;
        }
        return checkSuperbalanced(node, 0, new HashSet<>());
    }

    private static boolean checkSuperbalanced(BinaryTreeNode node, int depth, Set<Integer> depths) {
        if (node.left == null && node.right == null) { // is it a leaf node?
            depths.add(depth); // add current depth, then check
            if (depths.size() > 2) {
                return false; // after adding the current depth, we have now seen more than 2 distinct
                // depths, meaning that the tree cannot be superbalanced
            } else {
                if (depths.size() == 2) {
                    Iterator<Integer> iter = depths.iterator();
                    int depth_1 = iter.next();
                    int depth_2 = iter.next();
                    if (Math.abs(depth_1 - depth_2) > 1) {
                        return false; // bail out if, after adding the current depth, we have now seen 2 depths
                        // that differ by more than 1
                    }
                }
            }
        } else {
            boolean isSuperbalanced;
            if (node.left != null) {
                isSuperbalanced = checkSuperbalanced(node.left, depth + 1, depths);
                if (!isSuperbalanced) {
                    return false;
                }
            }
            if (node.right != null) {
                isSuperbalanced = checkSuperbalanced(node.right, depth + 1, depths);
                if (!isSuperbalanced) {
                    return false;
                }
            }
        }
        return true;
    }

    static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

}
