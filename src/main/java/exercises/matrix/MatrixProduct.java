package exercises.matrix;

/**
 * Given a matrix, find the path from top left to bottom right with the greatest product by moving only down and right.
 * You should return the product of the path.
 *
 * @author emanno
 * @version 1.0 Aug 8, 2017
 */
public class MatrixProduct {

    public static void main(String[] args) {
        int[][] m = new int[3][3];
        m[0][0] = 1;
        m[0][1] = 2;
        m[0][2] = 3;

        m[1][0] = 4;
        m[1][1] = 5;
        m[1][2] = 6;

        m[2][0] = 7;
        m[2][1] = 8;
        m[2][2] = 9;

        System.out.println(matrixProduct(m));

        m = new int[3][3];
        m[0][0] = -1;
        m[0][1] = 2;
        m[0][2] = 3;

        m[1][0] = 4;
        m[1][1] = 5;
        m[1][2] = -6;

        m[2][0] = 7;
        m[2][1] = 8;
        m[2][2] = 9;

        System.out.println(matrixProduct(m));

    }


    public static int matrixProduct(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] minProducts = new int[matrix.length][matrix[0].length];
        int[][] maxProducts = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) { // top left corner
                    minProducts[i][j] = matrix[i][j];
                    maxProducts[i][j] = matrix[i][j];
                    continue;
                }
                if (i == 0) { // first row
                    minProducts[i][j] = minProducts[i][j - 1] * matrix[i][j];
                    maxProducts[i][j] = maxProducts[i][j - 1] * matrix[i][j];
                    continue;
                }
                if (j == 0) { // first column
                    minProducts[i][j] = minProducts[i - 1][j] * matrix[i][j];
                    maxProducts[i][j] = maxProducts[i - 1][j] * matrix[i][j];
                    continue;
                }

                int p1 = matrix[i][j] * maxProducts[i - 1][j];
                int p2 = matrix[i][j] * maxProducts[i][j - 1];
                int p3 = matrix[i][j] * minProducts[i - 1][j];
                int p4 = matrix[i][j] * minProducts[i][j - 1];

                maxProducts[i][j] = max(p1, p2, p3, p4);
                minProducts[i][j] = min(p1, p2, p3, p4);

            }
        }

        return maxProducts[matrix.length - 1][matrix[0].length - 1];
    }


    private static int max(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max)
                max = num;
        }
        return max;
    }

    private static int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min)
                min = num;
        }
        return min;
    }

}
