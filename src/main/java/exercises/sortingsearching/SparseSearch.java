package exercises.sortingsearching;

/**
 * Given a sorted array of strings that is interspersed with empty strings, write a method to
 * find the location of a given string.
 * EXAMPLE
 * Input: ball {"at","","","","ball","","","car","","","dad","",""}
 * Output: 4
 */
public class SparseSearch {

    public static void main(String[] args) {
        String[] array = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""
                , "ever", "grant", "", "", "green", "", "", "",};
        System.out.println(sparseSearch(array, "ball"));
        System.out.println(sparseSearch(array, "dad"));
        System.out.println(sparseSearch(array, "blegh"));
        System.out.println(sparseSearch(array, "grant"));
        System.out.println(sparseSearch(array, "green"));
    }

    static int sparseSearch(String[] array, String elem) {
        if (array == null || array.length == 0 || elem.equals("")) {
            return -1;
        }

        return binarySearch(array, elem, 0, array.length - 1);
    }

    static int binarySearch(String[] array, String elem, int start, int end) {
        if (start > end) {
            return -1; // element not found!
        }

        int midIndex = (start + end) / 2;
        String midElem = array[midIndex];

        if (midElem.equals("")) {
            // search until the closest non-empty string
            int leftPtr = midIndex - 1;
            int rightPtr = midIndex + 1;
            while (true) {
                if (leftPtr < start && rightPtr > end) {
                    return -1;
                } else if (leftPtr >= start && !array[leftPtr].equals("")) {
                    midIndex = leftPtr;
                    break;
                } else if (rightPtr <= end && !array[rightPtr].equals("")) {
                    midIndex = rightPtr;
                    break;
                }
                leftPtr--;
                rightPtr++;
            }
        }

        midElem = array[midIndex];
        if (elem.equals(midElem)) {
            return midIndex; // found it, return index
        } else if (elem.compareTo(midElem) < 0) {
            // search left half
            return binarySearch(array, elem, start, midIndex - 1);
        } else {
            // search right half
            return binarySearch(array, elem, midIndex + 1, end);
        }
    }
}
