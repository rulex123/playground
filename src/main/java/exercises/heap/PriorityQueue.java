package exercises.heap;

import exercises.sortingsearching.Utils;

/**
 * Implement a priority queue
 */
public class PriorityQueue {

    private int[] items; // using a min heap
    private int size; // keeps track of how many items are currently in the heap

    PriorityQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("invalid capacity");
        }
        items = new int[capacity];
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(6);
        priorityQueue.addItem(27);
        priorityQueue.addItem(35);
        priorityQueue.addItem(18);
        priorityQueue.addItem(41);
        priorityQueue.addItem(52);
        priorityQueue.addItem(24);

        System.out.println(priorityQueue.getSmallestItem());
        System.out.println(priorityQueue.getSmallestItem());
        System.out.println(priorityQueue.getSmallestItem());
        System.out.println(priorityQueue.getSmallestItem());
        System.out.println(priorityQueue.getSmallestItem());
        System.out.println(priorityQueue.getSmallestItem());
    }

    int getSmallestItem() {
        if (size == 0) {
            throw new IllegalStateException("no items!");
        }

        // grab hold of current min item (at the top of heap)
        int result = items[0];
        // swap top of heap with bottom
        int indexOfBottom = size - 1;
        items[0] = items[indexOfBottom];
        size--;

        // now start pushing down the new top of heap until appropriate
        int currIndex = 0;
        while (true) {
            int indexOfLeftChild = (currIndex * 2) + 1;
            int indexOfRightChild = (currIndex * 2) + 2;
            if (indexOfLeftChild > size - 1 && indexOfRightChild > size - 1) {
                break; // done!
            }

            int swapIndex;
            if (indexOfRightChild > size - 1) {
                swapIndex = indexOfLeftChild;
            } else {
                if (items[indexOfLeftChild] < items[indexOfRightChild]) {
                    swapIndex = indexOfLeftChild;
                } else {
                    swapIndex = indexOfRightChild;
                }
            }

            if (items[currIndex] > items[swapIndex]) {
                Utils.swap(items, currIndex, swapIndex);
                currIndex = swapIndex;
            } else {
                break; // done!
            }
        }

        return result;
    }

    void addItem(int item) {
        if (size == items.length) {
            throw new IllegalStateException("full!");
        }
        size++;
        int indexOfNewItem = size - 1;
        // insert new item at bottom of heap
        items[indexOfNewItem] = item;
        // then start bubbling it up to the appropriate plae
        while (true) {
            int indexOfParent = (indexOfNewItem - 1) / 2;
            int parentItem = items[indexOfParent];
            if (item < parentItem) {
                Utils.swap(items, indexOfNewItem, indexOfParent);
                indexOfNewItem = indexOfParent;
            } else {
                break; // done!
            }
        }
    }

}
