package exercises.linkedlist;

public class CloneLinkedList {

    /**
     * Given a linked list where each node has two pointers, one to the next node and one to a
     * random node in the list, clone the linked list.
     */
    public static void main(String[] args) {

        // 1(4) -> 2(3) -> 3(1) -> 4(4) -> null
        Node node_4 = new Node(4);
        node_4.random = node_4;

        Node node_3 = new Node(3);
        node_3.next = node_4;

        Node node_2 = new Node(2);
        node_2.next = node_3;
        node_2.random = node_3;

        Node root = new Node(1);
        root.next = node_2;
        root.random = node_4;

        node_3.random = root;

        System.out.println("Original linked list:");
        root.print();

        Node clonedLinkedList = cloneLinkedList(root);
        System.out.println("Cloned linked list:");
        clonedLinkedList.print();
    }

    private static Node cloneLinkedList(Node root) {
        if (root == null) {
            return null;
        }

        Node currNode = root;
        Node clonedRoot = new Node(currNode.value);
        Node clonedCurrNode = clonedRoot;
        // in first pass, we want to partially clone the linked list and set up the pointers
        while (currNode != null) {
            if (currNode.next != null) {
                clonedCurrNode.next = new Node(currNode.next.value);
            }

            // point cloned node to original node
            clonedCurrNode.random = currNode;

            // point original node to cloned node
            Node nextNode = currNode.next;
            currNode.next = clonedCurrNode;

            // move along the linked lists (original and cloned)
            currNode = nextNode;
            clonedCurrNode = clonedCurrNode.next;
        }

        // now we reset the "random" pointer of each cloned node during a second pass
        clonedCurrNode = clonedRoot;
        while (clonedCurrNode != null) {
            Node randomNode = clonedCurrNode.random.random.next;
            clonedCurrNode.random = randomNode;
            clonedCurrNode = clonedCurrNode.next;
        }

        return clonedRoot;
    }

    private static class Node {

        private int value;
        private Node next;
        private Node random;

        private Node(final int value) {
            this.value = value;
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            Node n = this;
            while (n != null) {
                sb.append(n.value);
                sb.append("(");
                sb.append(n.random.value);
                sb.append(")");
                sb.append(" -> ");
                n = n.next; // get to last node
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

}
