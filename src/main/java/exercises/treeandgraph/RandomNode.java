
package exercises.treeandgraph;

import java.util.Random;

/**
 * You are implementing a binary search tree class from scratch, which, in addition to insert, find,
 * and delete, has a method getRandomNode() which returns a random node from the tree. All nodes
 * should be equally likely to be chosen. Design and implement an algorithm for getRandomNode,
 * and explain how you would implement the rest of the methods.
 *
 * @author emanno
 * @version 1.0 Aug 24, 2017
 */
public class RandomNode {

  private static class Node {

    int data;
    Node left;
    Node right;
    int childrenCount;


    Node(int value) {
      this.data = value;
    }


    private int leftChildren() {
      if (this.left == null) {
        return 0;
      }
      return this.left.childrenCount + 1;
    }
  }

  public static class BinarySearchTree {

    Node root;


    public void insertNode(int value) {
      if (this.root == null) {
        this.root = new Node(value);
      } else {
        this.insertNode(this.root, null, value);
      }
    }


    public void deleteNode(int value) {
      if (this.root == null) {
        throw new NullPointerException();
      }

      if (this.root.data == value) {
        if (this.root.childrenCount
            == 0) { // special case: root is being deleted, and there are no other nodes
          this.root = null;
        } else if (this.root.leftChildren()
                   == 1) { // special case: root is being deleted, and tree has only a left
          // subtree
          this.root = this.root.left;
        }
      }

      this.deleteNode(this.root, null, value);
    }


    public Node findNode(int value) {
      return this.findNode(this.root, value);
    }


    public Node getRandomNode() {
      if (this.root == null) {
        throw new NullPointerException();
      }

      int randomIndex = new Random().nextInt(this.root.childrenCount + 1);

      System.out.println("the magic index is " + randomIndex);

      return this.getRandomNode(this.root, randomIndex);
    }


    public void print() {
      this.print(this.root);
    }

    // --------------- overloaded private methods ------------

    private boolean insertNode(Node currNode, Node parentNode, int value) {
      // base case: node already exists
      if (currNode != null && currNode.data == value) {
        return false;
      }

      // base case: we made it to the right spot for inserting the node
      if (currNode == null) {
        Node newNode = new Node(value);
        if (value < parentNode.data) {
          parentNode.left = newNode;
        } else {
          parentNode.right = newNode;
        }
        return true;
      }

      // otherwise recurse on either RHS or LHS
      boolean inserted;
      if (value > currNode.data) {
        inserted = insertNode(currNode.right, currNode, value);
      } else {
        inserted = insertNode(currNode.left, currNode, value);
      }

      // check if we need to increment the count of children
      // based on whether the node was inserted or not
      if (inserted) {
        currNode.childrenCount += 1;
      }
      return inserted;
    }


    private boolean deleteNode(Node currNode, Node parentNode, int value) {
      if (currNode == null) {
        // there is no node in the tree with the given value
        return false;
      }

      if (currNode.data == value) {
        // we found the node to delete!
        // now we have to deal with 2 cases: either it's a leaf node or not

        // case 1: leaf node
        if (currNode.left == null && currNode.right == null) {
          if (value > parentNode.data) {
            parentNode.right = null;
          } else {
            parentNode.left = null;
          }
          return true;
        }

        // case 2: not a leaf node
        if (currNode.right != null) {
          // find the next smallest value in the subtree
          int nextSmallestNode = findNextSmallestValue(currNode.right);
          // delete the node with that value
          deleteNode(currNode.right, currNode, nextSmallestNode);
          // swap value being deleted with next smallest value
          currNode.data = nextSmallestNode;
          // decrement children count
          currNode.childrenCount -= 1;
        } else {
          if (currNode.left.data > parentNode.data) {
            parentNode.right = currNode.left;
          } else {
            parentNode.left = currNode.left;
          }
        }
        return true;
      }

      boolean deleted;
      if (value > currNode.data) {
        deleted = deleteNode(currNode.right, currNode, value);
      } else {
        deleted = deleteNode(currNode.left, currNode, value);
      }

      if (deleted) {
        currNode.childrenCount -= 1;
      }

      return deleted;
    }

    private int findNextSmallestValue(Node currNode) {
      if (currNode.left == null) {
        return currNode.data;
      }
      return this.findNextSmallestValue(currNode.left);
    }


    private Node findNode(Node currNode, int value) {
      if (currNode == null) {
        throw new NullPointerException();
      }

      if (value == currNode.data) {
        return currNode;
      }

      if (value < currNode.data) {
        return this.findNode(currNode.left, value);
      } else {
        return this.findNode(currNode.right, value);
      }
    }


    private Node getRandomNode(Node currNode, int randomIndex) {
      if (randomIndex == currNode.leftChildren()) {
        return currNode;
      }

      if (randomIndex < currNode.leftChildren()) {
        // return random node from left subtree
        return this.getRandomNode(currNode.left, randomIndex);
      } else {
        // return random node from right subtree
        return this.getRandomNode(currNode.right, randomIndex - currNode.leftChildren() - 1);
      }
    }


    private void print(Node currNode) {
      if (currNode != null) {
        this.print(currNode.left);
        System.out.println(currNode.data);
        this.print(currNode.right);
      }
    }

  }


  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    bst.insertNode(5);
    bst.insertNode(3);
    bst.insertNode(7);
    bst.insertNode(2);
    bst.insertNode(1);
    bst.insertNode(4);
    bst.insertNode(6);
    bst.insertNode(8);
    bst.insertNode(9);

    bst.deleteNode(100);

    for (int i = 0; i < 10; i++) {
      System.out.println(bst.getRandomNode().data);
    }
  }

}
