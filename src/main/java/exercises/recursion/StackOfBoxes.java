package exercises.recursion;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a stack of n boxes, with widths w1, w2, w3, etc. , heights h1, h2, h3, etc., depths d1,
 * d2, d3, etc. The boxes cannot be rotated and can only be stacked on top of one another if each
 * box in the stack is strictly larger than the box above it in width, height and depth.
 * Implement a method to compute the height of the tallest possible stack. The height of a stack
 * is the sum of the heights of each box.
 */
public class StackOfBoxes {


    static Comparator<Box> ascending_width = (b1, b2) -> {
        if (b1.width > b2.width) {
            return 1; // first box is greater (i.e. appears after)
        } else if (b1.width < b2.width) {
            return -1; // first box is smaller (i.e. appears before)
        } else {
            return 0;
        }
    };

    public static void main(String[] args) {
        Box[] boxes = new Box[3];
        boxes[0] = new Box(100, 3, 54);
        boxes[1] = new Box(200, 198, 55);
        boxes[2] = new Box(12, 200, 102);

        System.out.println(stackOfBoxes(boxes));
    }

    static int stackOfBoxes(Box[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        Arrays.sort(boxes, ascending_width.reversed());
        return stackOfBoxes(boxes, null, 0, new int[boxes.length]);
    }

    private static int stackOfBoxes(Box[] boxes, Box currentBottom, int offset, int[] cache) {
        // base case
        if (offset == boxes.length) {
            return 0; // we are out of boxes!
        }

        // use offset to grab a new bottom for the stack
        Box newBottom = boxes[offset];
        int heightWithBottom = 0;
        if (currentBottom == null || newBottom.canBeStackedAbove(currentBottom)) {
            if (cache[offset] == 0) { // check if we have computed this combination already
                // if not, compute the height of stack that uses the new bottom and insert into cache
                heightWithBottom = stackOfBoxes(boxes, newBottom, offset + 1, cache);
                heightWithBottom += newBottom.height;
                cache[offset] = heightWithBottom;
            } else {
                // read from cache
                heightWithBottom = cache[offset];
            }
        }

        // compute the height of stack that doesn't use the new bottom (i.e. maintain current bottom
        // and increase offset)
        int heightWithoutBottom = stackOfBoxes(boxes, currentBottom, offset + 1, cache);

        // return bigger of two heights
        return Math.max(heightWithoutBottom, heightWithBottom);
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

        // returns true if this box can be stacked above the given box, false otherwise
        boolean canBeStackedAbove(Box box) {
            if (this.height < box.height && this.depth < box.depth) {
                return true;
            }
            return false;
        }
    }
}
