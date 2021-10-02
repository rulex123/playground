package exercises.treeandgraph;

import exercises.treeandgraph.TreeNode.NodeType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A binary search tree was created by traversing through an array
 * from left to right and inserting each element. Given a binary search tree
 * with distinct elements, print all possible arrays that could have led to
 * this tree.
 */
public class BSTSequences {

    public static void main(String[] args) {

        /*
         *           8
         *         /   \
         *        4     9
         *       / \     \
         *      2   6     11
         *
         */
        TreeNode node_4 = new TreeNode(4, new TreeNode(2), new TreeNode(6));
        TreeNode node_9 = new TreeNode(9, new TreeNode(11), NodeType.RIGHT);
        TreeNode root = new TreeNode(8, node_4, node_9);

        final List<LinkedList<Integer>> result = BSTSequences(root);
        for (List<Integer> sequence : result) {
            System.out.println(sequence);
        }
    }

    private static List<LinkedList<Integer>> BSTSequences(TreeNode node) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }

        int currNodeValue = node.data;
        List<LinkedList<Integer>> seqsFromLHS = BSTSequences(node.leftChild);
        List<LinkedList<Integer>> seqsFromRHS = BSTSequences(node.rightChild);

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(currNodeValue);
        for (LinkedList<Integer> seqsFromRH : seqsFromRHS) {
            for (LinkedList<Integer> seqsFromLH : seqsFromLHS) {
                List<LinkedList<Integer>> weavedSeqs = new ArrayList<>();
                composeSeqs(seqsFromRH, seqsFromLH, prefix, weavedSeqs);
                result.addAll(weavedSeqs);
            }
        }

        return result;
    }

    private static void composeSeqs(LinkedList<Integer> first, LinkedList<Integer> second,
                                    LinkedList<Integer> prefix, List<LinkedList<Integer>> result) {
        // base case
        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> seq = (LinkedList<Integer>) prefix.clone();
            seq.addAll(first);
            seq.addAll(second);
            result.add(seq);
            return;
        }

        Integer headFirst = first.removeFirst();
        prefix.add(headFirst);
        composeSeqs(first, second, prefix, result);
        // put things back to how they were
        prefix.removeLast();
        first.addFirst(headFirst);

        Integer headSecond = second.removeFirst();
        prefix.add(headSecond);
        composeSeqs(first, second, prefix, result);
        // put things back to how they were
        prefix.removeLast();
        second.addFirst(headSecond);
    }

}
