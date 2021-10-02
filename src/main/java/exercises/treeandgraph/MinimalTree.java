package exercises.treeandgraph;

/**
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a BST with minimal
 * height.
 *
 * @author emanno
 * @version 1.0 Jul 1, 2017
 */
public class MinimalTree {

    public static void main(String[] args) {
        int[] array = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        TreeNode root = createMinimalBST(array);
        TreeTraversal.depthFirstInOrder_recursive(root);
    }


    public static TreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1);
    }


    private static TreeNode createMinimalBST(int[] array, int from, int to) {
        if (to < from)
            return null;

        int midPoint = (from + to) / 2;
        TreeNode node = new TreeNode(array[midPoint]);
        node.leftChild = createMinimalBST(array, from, midPoint - 1);
        node.rightChild = createMinimalBST(array, midPoint + 1, to);

        return node;
    }

}
