package exercises.sortingsearching;

public class Utils {
    public static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }


    public static void insert(int[] array, int element, int startAtIndex) {
        for (int i = startAtIndex; i >= 0; i--) {
            if (array[i] > element) {
                array[i + 1] = array[i];
                // we are at the beginning of the array: insert element
                if (i == 0) {
                    array[0] = element;
                }
            } else {
                // we found spot where element should be inserted: insert and break
                array[i + 1] = element;
                break;
            }
        }
    }

}
