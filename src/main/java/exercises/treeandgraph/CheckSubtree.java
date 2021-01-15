
package exercises.treeandgraph;

/**
 * T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of T1.
 * A tree T2 is a subtree of T1 if there exists a node in T1 such that the subtree of n
 * is identical to T2. That is, if you cut off the tree at node n, the ywo trees would
 * be identical.
 */
public class CheckSubtree {

  public static void main(String[] args) {
    /*
     *           12
     *         /    \
     *        15     8
     *       / \    / \
     *      1   10 13  19
     */

    TreeNode node_15 = new TreeNode(15, new TreeNode(1), new TreeNode(10));
    TreeNode node_8 = new TreeNode(8, new TreeNode(13), new TreeNode(19));
    TreeNode t1 = new TreeNode(12, node_15, node_8);

    /*
     *        8
     *       / \
     *     13  19
     */

    TreeNode t2 = new TreeNode(8, new TreeNode(13), new TreeNode(19));

    System.out.println(execute(t1, t2));
  }

  private static boolean execute(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return false; // bad input
    }

    if (t2 == null) {
      return true; // t1 is not empty, but t2 is
    }

    return checkSubtree(t1, t2);
  }

  private static boolean checkSubtree(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return false; // we reached the end of t1 without finding a match for t2
    }

    if (t1.data == t2.data && areIdenticalTrees(t1, t2)) {
      return true;
    }
    return checkSubtree(t1.leftChild, t2) || checkSubtree(t1.rightChild, t2);
  }

  private static boolean areIdenticalTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return true;
    }

    if ((t1 == null || t2 == null) || (t1.data != t2.data)) {
      return false;
    }

    return areIdenticalTrees(t1.leftChild, t2.leftChild) && areIdenticalTrees(t1.rightChild,
        t2.rightChild);
  }
}
