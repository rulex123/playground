package exercises.matrix;

/**
 * Given a 2D array of 1s and 0s, find the largest square subarray of all 1s.
 */
public class SquareSubmatrix {

    public static void main(String[] args) {
        int[][] m = new int[3][4];
        m[0][0] = 0;
        m[0][1] = 1;
        m[0][2] = 0;
        m[0][3] = 0;

        m[1][0] = 1;
        m[1][1] = 1;
        m[1][2] = 0;
        m[1][3] = 0;

        m[2][0] = 1;
        m[2][1] = 1;
        m[2][2] = 0;
        m[2][3] = 1;

        System.out.println(squareSubmatrix(m)); // max expected size is 2

        m = new int[3][4];
        m[0][0] = 0;
        m[0][1] = 1;
        m[0][2] = 1;
        m[0][3] = 1;

        m[1][0] = 0;
        m[1][1] = 1;
        m[1][2] = 1;
        m[1][3] = 1;

        m[2][0] = 1;
        m[2][1] = 1;
        m[2][2] = 1;
        m[2][3] = 1;

        System.out.println(squareSubmatrix(m)); // max expected size is 3

        m = new int[3][4];
        m[0][0] = 0;
        m[0][1] = 1;
        m[0][2] = 0;
        m[0][3] = 0;

        m[1][0] = 1;
        m[1][1] = 0;
        m[1][2] = 1;
        m[1][3] = 0;

        m[2][0] = 0;
        m[2][1] = 1;
        m[2][2] = 0;
        m[2][3] = 1;

        System.out.println(squareSubmatrix(m)); // max expected size is 1
    }

    static int squareSubmatrix(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dpMatrix = new int[rows][cols];
        int maxSubmatrixSize = 0;

        // move through the input array from left to right and top to bottom
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    // if we are at the top or left edge, just copy original
                    // cell and move on
                    dpMatrix[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] > 0) {
                        // look at surrounding 3 cells forming a square (where current cell is the bottom
                        // right corner of the square): we are guaranteed to have 3 surrounding cells because we
                        // are not at the top or left edge of the matrix.
                        int minSize = Math.min(dpMatrix[i - 1][j - 1], dpMatrix[i - 1][j]);
                        minSize = Math.min(minSize, dpMatrix[i][j - 1]);
                        dpMatrix[i][j] = minSize + matrix[i][j];
                    }
                }
                // check if we have found a new max submatrix
                if (dpMatrix[i][j] > maxSubmatrixSize) {
                    maxSubmatrixSize = dpMatrix[i][j];
                }
            }
        }
        return maxSubmatrixSize;
    }
}
