package exercises.sortingsearching;

/**
 * You are given an array-like data structure Listy which lacks a size method. It does, however,
 * have an elementAt(i) method that returns the element at index i in O(1) time. If i is beyond
 * the bounds of the data structure, it returns -1. (For this reason, the data structure only
 * supports positive integers). Given a Listy which contains sorted, positive integers, find the
 * index at which an element x occurs. If x occurs multiple times, you may return any index.
 */
public class SortedSearchNoSize {

    public static void main(String[] args) {
        Listy listy = new Listy(7, 15, 27, 34, 36, 42, 51, 66, 72, 90);
        System.out.println(sortedSearchNoSize(listy, 51));
        System.out.println(sortedSearchNoSize(listy, 27));
        System.out.println(sortedSearchNoSize(listy, 90));
        System.out.println(sortedSearchNoSize(listy, 7));
        System.out.println(sortedSearchNoSize(listy, 111));
        System.out.println(sortedSearchNoSize(listy, 1));
    }

    static int sortedSearchNoSize(Listy listy, int elem) {
        if (listy == null || listy.elementAt(0) == -1) {
            throw new IllegalArgumentException("invalid listy");
        }

        int index = 1;
        while (listy.elementAt(index) != -1 && listy.elementAt(index) < elem) {
            // keep pushing the boundary until we either go too far or come across
            // an element in listy that is bigger than what we are looking for
            index = index * 2;
        }

        return binarySearch(listy, elem, 0, index);
    }

    private static int binarySearch(Listy listy, int elem, int start, int end) {
        if (start > end) {
            return -1; // elem is not in listy!
        }

        int mid = (start + end) / 2;
        int midElem = listy.elementAt(mid);

        if (midElem == -1) {
            // no choice but to search left half
            return binarySearch(listy, elem, start, mid - 1);
        } else {
            if (midElem == elem) {
                // found it!
                return mid;
            } else if (midElem > elem) {
                // search left half
                return binarySearch(listy, elem, start, mid - 1);
            } else {
                // search right half
                return binarySearch(listy, elem, mid + 1, end);
            }
        }
    }

    static class Listy {

        private final int[] array;

        Listy(int... items) {
            array = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                if (items[i] < 0) {
                    throw new IllegalArgumentException("negative numbers are not allowed!");
                }
                array[i] = items[i];
            }
        }

        int elementAt(int index) {
            if (index < 0 || index > array.length - 1) {
                return -1;
            }
            return array[index];
        }
    }
}
