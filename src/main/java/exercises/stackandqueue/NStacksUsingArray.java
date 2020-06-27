package exercises.stackandqueue;

import java.util.Arrays;
import java.util.EmptyStackException;

public class NStacksUsingArray {

  public static void main(String[] args) {
    NStacks nStacks = new NStacks(2, 5);

    nStacks.push(0, 1);
    nStacks.push(0, 2);
    nStacks.push(1, 3);
    nStacks.push(1, 4);
    nStacks.push(0, 5);

    try {
      nStacks.push(0, 6); // we are at capacity
    } catch (IllegalStateException ex) {
      System.out.println("Reached capacity!");
    }

    System.out.println(nStacks.pop(1)); // expected 4
    System.out.println(nStacks.pop(0)); // expected 5
    System.out.println(nStacks.pop(0)); // expected 2
    System.out.println(nStacks.pop(1)); // expected 3

    nStacks.push(0, 6);

    System.out.println(nStacks.pop(0)); // expected 1
    System.out.println(nStacks.pop(0)); // expected 6

    try {
      nStacks.pop(0); // no items
    } catch (EmptyStackException ex) {
      System.out.println("No items!");
    }
  }

  private static class NStacks {

    private final int[] items;
    private final int[] topOfStacks;
    private final int[] nextPointers;
    private final int[] prevPointers;
    private int insertAt;

    private NStacks(int noOfStacks, int capacity) {
      items = new int[capacity];
      topOfStacks = new int[noOfStacks];
      nextPointers = new int[capacity];
      prevPointers = new int[capacity];
      Arrays.fill(topOfStacks, -1);
      Arrays.fill(prevPointers, -1);
      initializeNextPointers();
    }

    private void initializeNextPointers() {
      nextPointers[nextPointers.length - 1] = -1;
      for (int i = 0; i < nextPointers.length - 1; i++) {
        nextPointers[i] = i + 1;
      }
    }

    private int pop(int stackIndex) {
      validateStackIndex(stackIndex);
      if (topOfStacks[stackIndex] == -1) {
        throw new EmptyStackException();
      }

      int result = items[topOfStacks[stackIndex]];

      nextPointers[topOfStacks[stackIndex]] = insertAt;

      insertAt = topOfStacks[stackIndex];

      if (prevPointers[topOfStacks[stackIndex]] != -1) {
        topOfStacks[stackIndex] = prevPointers[topOfStacks[stackIndex]];
      } else {
        topOfStacks[stackIndex] = -1;
      }

      return result;
    }

    private void push(int stackIndex, int value) {
      validateStackIndex(stackIndex);
      if (insertAt == -1) {
        throw new IllegalStateException("We are at capacity, go away please!");
      }

      items[insertAt] = value;

      if (topOfStacks[stackIndex] != -1) {
        prevPointers[insertAt] = topOfStacks[stackIndex];
      }

      topOfStacks[stackIndex] = insertAt;

      insertAt = nextPointers[insertAt];
    }

    private void validateStackIndex(int stackIndex) {
      if (stackIndex < 0 || stackIndex > this.topOfStacks.length - 1) {
        throw new IllegalArgumentException("Invalid stack index");
      }
    }

  }

}
