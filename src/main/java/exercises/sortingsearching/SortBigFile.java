package exercises.sortingsearching;

/**
 * Imagine you have a 20GB file with one string per line. Explain how you would sort the file.
 */
public class SortBigFile {

    /**
     * Since the file is 20GB, we don't want to bring oll of the data into memory. Instead, we can
     * bring the data into memory in chunks that are as big as the memory available to our program.
     *
     * Each chunk of data that is brought into memory is sorted using an efficient sorting
     * algorithm such as mergesort or quicksort (maybe quicksort might be a better choice since it
     * requires less extra space than mergesort), and then the sorted chunk is saved to the file
     * system into a temporary file.
     *
     * Once all of the chunks have been sorted and stored back to the file system, they can be
     * merged together using an algorithm similar to the one we use to merge k sorted arrays
     * (reading one string at a time from each file and writing them in sorted order to the
     * original file).
     */

}
