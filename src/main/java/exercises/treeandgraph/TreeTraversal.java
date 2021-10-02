package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class illustrates the techniques to traverse a tree, namely depth-first (in-order, pre-order and post-order) and
 * breadth-first.
 *
 * @author emanno
 * @version 1.0 Jun 29, 2017
 */
public class TreeTraversal {

    public static void main(String[] args) {
        /*
         *           8
         *         /   \
         *        4     9
         *       / \     \
         *      2   6     11
         */
        TreeNode leafNode1 = new TreeNode(2);
        TreeNode leafNode2 = new TreeNode(6);
        TreeNode leafNode3 = new TreeNode(11);

        TreeNode midNode1 = new TreeNode(4, leafNode1, leafNode2);
        TreeNode midNode2 = new TreeNode(9, leafNode3, NodeType.RIGHT);

        TreeNode root = new TreeNode(8, midNode1, midNode2);

        System.out.println("IN ORDER (recursive implementation)");
        depthFirstInOrder_recursive(root);

        System.out.println();
        System.out.println("IN ORDER (iterative implementation)");
        depthFirstInOrder_iterative(root);

        System.out.println();
        System.out.println("PRE ORDER (recursive implementation)");
        depthFirstPreOrder_recursive(root);

        System.out.println();
        System.out.println("PRE ORDER (iterative implementation)");
        depthFirstPreOrder_iterative(root);

        System.out.println();
        System.out.println("POST ORDER (recursive implementation)");
        depthFirstPostOrder_recursive(root);

        System.out.println();
        System.out.println("POST ORDER (iterative implementation)");
        depthFirstPostOrder_iterative(root);

        System.out.println();
        System.out.println("BREADTH FIRST");
        breadthFirst(root);

    }


    public static void depthFirstInOrder_iterative(TreeNode node) {
        Stack<TreeNode> visitedNodes = new Stack<>();
        leftNodes(node, visitedNodes, false);

        while (!visitedNodes.isEmpty()) {
            TreeNode currentNode = visitedNodes.pop();
            System.out.println(currentNode.data);

            if (currentNode.rightChild != null) {
                leftNodes(currentNode.rightChild, visitedNodes, false);
            }
        }
    }


    public static void depthFirstPreOrder_iterative(TreeNode node) {
        Stack<TreeNode> visitedNodes = new Stack<>();
        leftNodes(node, visitedNodes, true);

        while (!visitedNodes.isEmpty()) {
            TreeNode currentNode = visitedNodes.pop();

            if (currentNode.rightChild != null) {
                leftNodes(currentNode.rightChild, visitedNodes, true);
            }
        }
    }


    public static void depthFirstPostOrder_iterative(TreeNode node) {
        Stack<TreeNode> visitedNodes = new Stack<>();
        TreeNode currentNode = node;
        TreeNode lastVisitedNode = null;

        while (currentNode != null || !visitedNodes.isEmpty()) {
            if (currentNode != null) {
                visitedNodes.push(currentNode);
                currentNode = currentNode.leftChild;
            } else {
                if (visitedNodes.peek().rightChild != null && lastVisitedNode != visitedNodes.peek().rightChild) {
                    currentNode = visitedNodes.peek().rightChild;
                } else {
                    lastVisitedNode = visitedNodes.pop();
                    System.out.println(lastVisitedNode.data);
                }
            }
        }
    }


    private static void leftNodes(TreeNode node, Stack<TreeNode> visitedNodes, boolean visitNode) {
        while (node != null) {
            if (visitNode)
                System.out.println(node.data);
            visitedNodes.push(node);
            node = node.leftChild;
        }
    }


    public static void breadthFirst(TreeNode node) {
        Queue<TreeNode> visitedNodes = new LinkedList<>();
        visitedNodes.offer(node);

        while (!visitedNodes.isEmpty()) {
            node = visitedNodes.poll();
            System.out.println(node.data);
            if (node.leftChild != null)
                visitedNodes.offer(node.leftChild);
            if (node.rightChild != null)
                visitedNodes.offer(node.rightChild);
        }
    }


    public static void depthFirstInOrder_recursive(TreeNode node) {
        if (node != null) {
            depthFirstInOrder_recursive(node.leftChild);
            System.out.println(node.data);
            depthFirstInOrder_recursive(node.rightChild);
        }
    }


    public static void depthFirstPreOrder_recursive(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            depthFirstPreOrder_recursive(node.leftChild);
            depthFirstPreOrder_recursive(node.rightChild);
        }
    }


    public static void depthFirstPostOrder_recursive(TreeNode node) {
        if (node != null) {
            depthFirstPostOrder_recursive(node.leftChild);
            depthFirstPostOrder_recursive(node.rightChild);
            System.out.println(node.data);
        }
    }

}
