package exercises.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an input stream of integers, you must perform the following task for each integer: Add the ith integer to a
 * running list of integers. Find the median of the updated list (i.e., for the first element through the ith element).
 * Print the list's updated median on a new line. The printed value must be a double-precision number scaled to decimal
 * place (i.e. 12.3 format).
 *
 * @author emanno
 * @version 1.0 Jan 3, 2018
 */
public class RunningMedian {

    public static void main(String[] args) {
        int[] array = new int[]{
                12, 4, 5, 3, 8, 7
        };
        double[] medians = getMedians(array);
        System.out.println(Arrays.toString(medians));
    }


    private static double[] getMedians(int[] array) {
        if (array == null || array.length == 0)
            return null;

        double[] medians = new double[array.length];
        PriorityQueue<Integer> topHalf = new PriorityQueue<>(); // min heap
        PriorityQueue<Integer> bottomHalf = new PriorityQueue<>(new Comparator<Integer>() // max heap
        {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        for (int i = 0; i < array.length; i++) {
            addNumberToHeap(array[i], topHalf, bottomHalf);
            rebalanceHeaps(topHalf, bottomHalf);
            medians[i] = calculateMedian(topHalf, bottomHalf);
        }

        return medians;
    }


    private static double calculateMedian(PriorityQueue<Integer> topHalf, PriorityQueue<Integer> bottomHalf) {
        if (topHalf.size() == bottomHalf.size()) {
            return ((double) topHalf.peek() + bottomHalf.peek()) / 2;
        } else {
            if (topHalf.size() > bottomHalf.size())
                return topHalf.peek();
            else
                return bottomHalf.peek();
        }
    }


    private static void rebalanceHeaps(PriorityQueue<Integer> topHalf, PriorityQueue<Integer> bottomHalf) {
        PriorityQueue<Integer> smallerHeap = topHalf.size() < bottomHalf.size() ? topHalf : bottomHalf;
        PriorityQueue<Integer> biggerHeap = topHalf.size() > bottomHalf.size() ? topHalf : bottomHalf;

        if (biggerHeap.size() - smallerHeap.size() > 1) {
            smallerHeap.add(biggerHeap.poll());
        }
    }


    private static void addNumberToHeap(int number, PriorityQueue<Integer> topHalf,
                                        PriorityQueue<Integer> bottomHalf) {
        if (bottomHalf.isEmpty() || number < bottomHalf.peek().intValue()) {
            bottomHalf.add(number);
        } else {
            topHalf.add(number);
        }
    }

}
