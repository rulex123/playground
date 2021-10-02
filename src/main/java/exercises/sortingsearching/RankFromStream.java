package exercises.sortingsearching;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up
 * the rank of a number x (the number of values less than or equal to x). Implement the data
 * structures and algorithms to support these operations. That is, implement the method track(int
 * x), which is called when each number is generated, and the method getRankOfNumber(int x),
 * which returns the number of values less than or equal to x (not including x itself).
 * <p>
 * EXAMPLE
 * Stream (in order of appearance): 5,1,4,4,5,9,7,13,3
 * getRankOfNumber(1)=0
 * getRankOfNumber(3)=1
 * getRankOfNumber(4)=2
 */
public class RankFromStream {

    Node root;

    /* This method would be called when a new integer is generated */
    void track(int number) {
        if (root == null) {
            root = new Node(number);
            return;
        }
        root.insertNode(number);
    }

    int getRankOfNumber(int number) {
        if (root == null) {
            return -1;
        }
        return root.getRank(number);
    }

    static class Node {

        int number;
        int leftSize; // size of the left subtree
        Node leftChild;
        Node rightChild;

        private Node(int number) {
            this.number = number;
        }

        void insertNode(int number) {
            if (this.number == number) {
                // number is already present, nothing to do
                return;
            } else if (number < this.number) {
                // increment left size
                leftSize++;
                if (this.leftChild == null) {
                    leftChild = new Node(number);
                } else {
                    leftChild.insertNode(number);
                }
            } else {
                if (rightChild == null) {
                    rightChild = new Node(number);
                } else {
                    rightChild.insertNode(number);
                }
            }
        }

        int getRank(int number) {
            if (this.number == number) {
                return leftSize;
            } else if (number < this.number) {
                if (leftChild == null) {
                    return -1;
                }
                return leftChild.getRank(number);
            } else {
                int rightRank = rightChild.getRank(number);
                return rightRank == -1 ? -1 : leftSize + 1 + rightRank;
            }
        }
    }

}
