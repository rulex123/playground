package exercises.sortingsearching;

import java.util.Optional;

/**
 * Given an MxN matrix in which each row and each column is sorted in ascending order, write a
 * method to find an element.
 * NOTE: interesting blog post on this problem at http://sq8alg.blogspot.com/2012/12/searching-2d-sorted-matrix-part.html
 */
public class SortedMatrixSearch {

    public static void main(String[] args) {
        int[][] m = new int[3][5];
        m[0][0] = 9;
        m[0][1] = 18;
        m[0][2] = 24;
        m[0][3] = 36;
        m[0][4] = 45;

        m[1][0] = 10;
        m[1][1] = 21;
        m[1][2] = 28;
        m[1][3] = 47;
        m[1][4] = 54;

        m[2][0] = 11;
        m[2][1] = 27;
        m[2][2] = 50;
        m[2][3] = 58;
        m[2][4] = 61;

        // first row of matrix
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 9, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 18, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 24, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 36, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 45, 0, 4, 0, 2));

        System.out.println();

        // second row
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 10, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 21, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 28, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 47, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 54, 0, 4, 0, 2));

        System.out.println();

        // third row
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 11, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 27, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 50, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 58, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 61, 0, 4, 0, 2));

        System.out.println();

        // values that are not in the matrix
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 71, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 3, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 26, 0, 4, 0, 2));
        System.out.println(sortedMatrixSearchUsingBinarySearch(m, 49, 0, 4, 0, 2));
    }

    // Here we use a binary search approach to divide the matrix in 4 quadrants, and then recursively
    // search on 2 of them until we either find the target or fall off the matrix.
    // This algo is the most efficient in terms of runtime, but its biggest con is that it's a bit
    // hairy to implement.
    static Optional<Coordinate> sortedMatrixSearchUsingBinarySearch(int[][] matrix, int target,
                                                                    int startAtCol, int endAtCol,
                                                                    int startAtRow, int endAtRow) {
        if (matrix == null) {
            return Optional.empty();
        }

        // base case: we fell off the matrix, which means target could not be found!
        if ((endAtCol < startAtCol || startAtCol > endAtCol) || (endAtRow < startAtRow
                || startAtRow > endAtRow)) {
            return Optional.empty();
        }

        int midCol = (startAtCol + endAtCol) / 2;

        if (target < matrix[startAtRow][midCol] || target > matrix[endAtRow][midCol]) {
            // special case: matrix can be divided in 2 halves
            if (target < matrix[startAtRow][midCol]) {
                // search in half of matrix to the left of mid column
                return sortedMatrixSearchUsingBinarySearch(matrix, target, startAtCol, midCol - 1,
                        startAtRow
                        , endAtRow);
            } else {
                // search in half of matrix to the right of mid column
                return sortedMatrixSearchUsingBinarySearch(matrix, target, midCol + 1, endAtCol, startAtRow
                        , endAtRow);
            }
        } else {
            // find the partition point around which we divide matrix in 4 quadrants
            Coordinate partition = findPartitionPoint(matrix, midCol, startAtRow, endAtRow, target);
            int partitionValue = matrix[partition.row][partition.column];

            // check if the partition point is the target
            if (partitionValue == target) {
                return Optional.of(partition);
            } else if (partitionValue > target) {

                // search 2 partitions
                Optional<Coordinate> targetCoordinate =
                        sortedMatrixSearchUsingBinarySearch(matrix, target, startAtCol,
                                partition.column - 1, partition.row, endAtRow);

                if (targetCoordinate.isPresent()) {
                    return targetCoordinate;
                } else {
                    targetCoordinate =
                            sortedMatrixSearchUsingBinarySearch(matrix, target, partition.column + 1
                                    , endAtCol, startAtRow, partition.row - 1);
                    return targetCoordinate;
                }

            } else {

                // search 2 partitions
                Optional<Coordinate> targetCoordinate =
                        sortedMatrixSearchUsingBinarySearch(matrix, target, startAtCol,
                                partition.column - 1, partition.row + 1, endAtRow);

                if (targetCoordinate.isPresent()) {
                    return targetCoordinate;
                } else {
                    targetCoordinate =
                            sortedMatrixSearchUsingBinarySearch(matrix, target, partition.column + 1
                                    , endAtCol, startAtRow, partition.row);
                    return targetCoordinate;
                }
            }
        }
    }

    private static Coordinate findPartitionPoint(int[][] matrix, int col, int startPos,
                                                 int endPos, int target) {
        // base case to interrupt search
        if (endPos <= startPos || startPos >= endPos) {
            return new Coordinate(startPos, col);
        }

        int midRow = (startPos + endPos) / 2;
        int elem = matrix[midRow][col];

        if (target == elem) {
            return new Coordinate(midRow, col);
        } else if (target > elem) {
            return findPartitionPoint(matrix, col, midRow + 1, endPos, target);
        } else {
            return findPartitionPoint(matrix, col, startPos, midRow - 1, target);
        }
    }

    // Here we use a "linear search that moves in steps", which takes advantage of the fact that the
    // matrix is sorted. We start at the right top corner and move either left or down until we
    // either find the target, or we fall off the matrix :)
    // This algo is quite fast (in terms of runtime) - though not the best - and most of all it's
    // trivial to implement
    static Optional<Coordinate> sortedMatrixSearchUsingStepLinearSearch(int[][] matrix, int target) {
        if (matrix == null) {
            return Optional.empty();
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        // start at top right corner
        int currRow = 0;
        int currCol = columns - 1;

        while (true) {
            // first, check if we are within boundaries
            if (currRow >= rows || currCol < 0) {
                // we fell off the matrix, which means that target wasn't found
                return Optional.empty();
            } else {
                // we are within boundaries, so look at current element and compare it to target
                int currElement = matrix[currRow][currCol];
                if (target == currElement) {
                    return Optional
                            .of(new Coordinate(currRow, currCol));        // found it! return the coordinate
                } else if (target < currElement) {
                    currCol--; // move left
                } else {
                    currRow++; // move down
                }
            }
        }
    }

    static class Coordinate {

        final int row;
        final int column;

        private Coordinate(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }
}
