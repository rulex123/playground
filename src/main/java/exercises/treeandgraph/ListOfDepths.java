package exercises.treeandgraph;

import exercises.linkedlist.DeleteMiddleNode;
import exercises.linkedlist.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each
 * depth (i.e. if you have
 * a tree with depth D, you'll have D linked lists)
 *
 * @author emanno
 * @version 1.0 Jan 31, 2018
 */
public class ListOfDepths {

    public static void main(String[] args) {

        /*
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

        TreeNode node_3 = new TreeNode(3, new TreeNode(4), TreeNode.NodeType.RIGHT);
        TreeNode node_21 = new TreeNode(21, node_3, TreeNode.NodeType.RIGHT);
        TreeNode node_9 = new TreeNode(9, node_21, TreeNode.NodeType.RIGHT);
        TreeNode node_7 = new TreeNode(7, new TreeNode(1), new TreeNode(15));
        TreeNode root = new TreeNode(6, node_7, node_9);

        final List<Node> allLinkedLists = listOfDepths(root);
        for (Node ll : allLinkedLists) {
            DeleteMiddleNode.printLinkedList(ll);
            System.out.println("");
        }
    }


    public static List<Node> listOfDepths(TreeNode node) {
        if (node == null) {
            return null;
        }

        List<Node> allLinkedLists = new ArrayList<>();

        Queue<TreeNodeWrapper> toVisit = new LinkedList<>();
        // add root of tree
        toVisit.add(new TreeNodeWrapper(node, 0));

        int currLevel = -1;
        while (!toVisit.isEmpty()) {
            TreeNodeWrapper currentNodeWrapper = toVisit.poll();

            if (currentNodeWrapper.nodeLevel > currLevel) {
                // we are beginning to traverse the nodes of a new level
                currLevel = currentNodeWrapper.nodeLevel;
                allLinkedLists.add(new Node(currentNodeWrapper.node.data));
            } else {
                // continuation of a previous level
                Node existingLLNode = allLinkedLists.get(currLevel);
                existingLLNode.appendToTail(new Node(currentNodeWrapper.node.data));
            }

            // add left child to queue
            if (currentNodeWrapper.node.leftChild != null) {
                toVisit.offer(new TreeNodeWrapper(currentNodeWrapper.node.leftChild,
                        currentNodeWrapper.nodeLevel + 1));
            }

            // add right child to queue
            if (currentNodeWrapper.node.rightChild != null) {
                toVisit.offer(new TreeNodeWrapper(currentNodeWrapper.node.rightChild,
                        currentNodeWrapper.nodeLevel + 1));
            }
        }

        return allLinkedLists;
    }

    private static class TreeNodeWrapper {

        final TreeNode node;
        final int nodeLevel;

        private TreeNodeWrapper(final TreeNode node, final int nodeLevel) {
            this.node = node;
            this.nodeLevel = nodeLevel;
        }
    }
}
