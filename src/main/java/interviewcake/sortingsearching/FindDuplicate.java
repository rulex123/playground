package interviewcake.sortingsearching;

/**
 * We have an array of integers, where:
 * <p>
 * The integers are in the range 1..n1..n
 * The array has a length of n+1n+1
 * It follows that our array has at least one integer which appears at least twice. But it may have
 * several duplicates, and each duplicate may appear more than twice.
 * <p>
 * Write a method which finds an integer that appears more than once in our array. (If there are
 * multiple duplicates, you only need to find one of them.)
 * <p>
 * We're going to run this method on our new, super - hip MacBook Pro With Retina Display TM. Thing is,
 * the damn thing came with the RAM soldered right to the motherboard, so we can't upgrade our RAM.
 * So we need to optimize for space!
 */
public class FindDuplicate {

    public static void main(String[] args) {
        // we know that floor is 1
        // we know that ceiling is length of array - 1
        int[] array = {7, 4, 2, 2, 5, 3, 9, 10, 1, 8, 6};
        System.out.println(findDuplicate(array, 1, array.length - 1));

        array = new int[]{7, 4, 2, 2, 5, 3, 8, 8, 8, 8, 10};
        System.out.println(findDuplicate(array, 1, array.length - 1));
    }

    static int findDuplicate(int[] array, int floor, int ceiling) {
        if (array == null || array.length == 0) {
            return -1;
        }

        if (floor == ceiling) {
            return array[floor]; // found duplicate!
        }

        int mid = (floor + ceiling) / 2; // midvalue for the range floor...ceiling
        int range = mid - floor + 1;
        int size = 0;
        for (int i = 0; i < array.length; i++) { // count how many items in the array are within
            // floor...midvalue
            int currItem = array[i];
            if (currItem >= floor && currItem <= mid) {
                size++;
            }
        }

        // the general idea is that we compare how many distinct elements could be in one half of the
        // range 1...n with how many elements in the input array are actually in that range (size):
        // if the size is bigger than the range, then we know for sure that there must be a duplicate in
        // that half of the 1...n range
        if (size > range) {
            return findDuplicate(array, floor, mid);
        } else {
            return findDuplicate(array, mid + 1, ceiling);
        }
    }
}
