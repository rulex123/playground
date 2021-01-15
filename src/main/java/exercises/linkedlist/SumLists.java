
package exercises.linkedlist;

import java.util.Stack;

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit.The digits are stored in reverse order, such that the 1 's digit is at the head of the
 * list. Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input:(7-> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295.
 * Output:2 -> 1 -> 9.That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input:(6-> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
 * Output:9 -> 1 -> 2. That is, 912.
 *
 * @author emanno
 * @version 1.0 Apr 24, 2017
 */
public class SumLists {

  public static void main(String[] args) {

    Node l1 = new Node(7);
    l1.appendToTail(1);
    l1.appendToTail(6);

    Node l2 = new Node(5);
    l2.appendToTail(9);
    l2.appendToTail(2);

    l1.print();
    l2.print();

    Node result = forwardOrder(l1, l2);
    result.print();

  }

  private static Node reverseOrder(Node l1, Node l2) {
    int carry = 0;
    Node result = null;
    while (l1 != null || l2 != null) { // until either linked list has a node
      int data1 = l1 != null ? l1.data : 0;
      int data2 = l2 != null ? l2.data : 0;
      int value = (data1 + data2 + carry) % 10; // calculate value for node to add to result
      carry = (data1 + data2 + carry) >= 10 ? 1 : 0; // set carry for next iteration
      if (result == null) {
        result = new Node(value); // create head of result linked list
      } else {
        result.appendToTail(value); // append node to result linked list
      }
      l1 = l1 != null ? l1.next : null;
      l2 = l2 != null ? l2.next : null;
    }

    // we broke out of the while look, so we exhausted both input linked lists
    if (carry == 1) {
      result.appendToTail(carry);
    }

    return result;
  }

  // caveat: this will only work if the 2 linked lists have the same
  // number of nodes: an easy way to work around this problem would be
  // to change the algo to store digits of the 2 numbers in separate stacks,
  // or alternatively keep the recursive algo and pad the shorter linked list with zeroes.
  private static Node forwardOrder(Node l1, Node l2) {
    Stack<Integer> result = new Stack<>();
    int carry = forwardOrderRec(l1, l2, result);
    if (carry == 1) {
      result.push(1);
    }

    // create linked list for result
    Node head = null;
    Node currNode = null;
    while (!result.isEmpty()) {
      int digit = result.pop();
      if (head == null) {
        head = new Node(digit);
        currNode = head;
        continue;
      }
      Node tmpNode = new Node(digit);
      currNode.next = tmpNode;
      currNode = tmpNode;
    }

    return head;
  }

  private static int forwardOrderRec(Node l1, Node l2, Stack<Integer> result) {
    if (l1 == null && l2 == null) {
      // base case: we reached the end of both linked lists
      return 0;
    }

    int carry = forwardOrderRec(l1.next, l2.next, result);
    int data1 = l1 != null ? l1.data : 0;
    int data2 = l2 != null ? l2.data : 0;
    int value = (data1 + data2 + carry) % 10; // calculate value for node to add to result
    carry = (data1 + data2 + carry) >= 10 ? 1 : 0; // set carry for next iteration
    result.push(value);
    return carry;
  }

}