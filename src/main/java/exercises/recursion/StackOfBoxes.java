
package exercises.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackOfBoxes {

  public static void main(String[] args) {

    List<Box> boxes = new ArrayList<>();
    boxes.add(new Box(100, 3, 54));
    boxes.add(new Box(200, 198, 55));
    boxes.add(new Box(12, 200, 102));
//    boxes.add(new Box(1, 400, 1));

    int result = stackOfBoxes(boxes, new Stack<>(), 0);
    System.out.println(result);
  }

  private static int stackOfBoxes(List<Box> lefOverBoxes, Stack<Box> stackedBoxes,
                                  int heightSoFar) {
    // base case
    if (lefOverBoxes.isEmpty()) {
      return heightSoFar;
    }

    int currMax = Integer.MIN_VALUE;
    // part of the algo that recurses
    for (int i = 0; i < lefOverBoxes.size(); i++) {
      Box currBox = lefOverBoxes.get(i);

      // check if we have a valid stack
      if (stackedBoxes.isEmpty() || stackedBoxes.peek().isLargerThan(currBox)) {
        stackedBoxes.push(currBox);
        heightSoFar += currBox.height;
      }

      List<Box> newLeftOverBoxes = new ArrayList<>();
      newLeftOverBoxes.addAll(lefOverBoxes.subList(0, i));
      newLeftOverBoxes.addAll(lefOverBoxes.subList(i + 1, lefOverBoxes.size()));

      int height = stackOfBoxes(newLeftOverBoxes, stackedBoxes, heightSoFar);
      if (height > currMax) {
        currMax = height;
      }

      // undo change to the stack and continue
      if (!stackedBoxes.isEmpty() && stackedBoxes.peek() == currBox) {
        stackedBoxes.pop();
        heightSoFar -= currBox.height;
      }
    }

    // need to return the max value stored in list of heights
    return currMax;
  }

  private static class Box {

    final int width;
    final int height;
    final int depth;

    Box(final int width, final int height, final int depth) {
      this.width = width;
      this.height = height;
      this.depth = depth;
    }

    @Override
    public String toString() {
      return "{w=" + width +
             ",h=" + height +
             ",d=" + depth + "}";
    }

    boolean isLargerThan(Box box) {
      if (this.width > box.width && this.height > box.height && this.depth > box.depth) {
        return true;
      }
      return false;
    }
  }
}
