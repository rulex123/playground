package exercises.matrix;

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method
 * to rotate the image by 90 degrees. Can you do this in place?
 *
 * @author emanno
 * @version 1.0 Apr 20, 2017
 */
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] m = new int[4][4];
        m[0][0] = 1;
        m[0][1] = 2;
        m[0][2] = 3;
        m[0][3] = 0;

        m[1][0] = 4;
        m[1][1] = 5;
        m[1][2] = 6;
        m[1][3] = 0;

        m[2][0] = 7;
        m[2][1] = 8;
        m[2][2] = 9;
        m[2][3] = 0;

        m[3][0] = 1;
        m[3][1] = 2;
        m[3][2] = 3;
        m[3][3] = 4;


        RotateMatrix unit = new RotateMatrix();
        printMatrix(m);

        unit.rotateMatrixBy90Degrees(m);

        System.out.println();

        printMatrix(m);

    }

    public static void printMatrix(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%5d ", m[i][j]);
            }
            System.out.println();
        }
    }

    private void rotateMatrixBy90Degrees(int[][] m) {
        // if empty matrix, then return
        if (m.length == 0)
            return;

        // if not a nxn matrix, then return
        if (m.length != m[0].length)
            return;

        for (int layer = 0; layer < m.length / 2; layer++) {
            int first = layer;
            int last = m.length - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = m[first][i];
                m[first][i] = m[last - offset][first];
                m[last - offset][first] = m[last][last - offset];
                m[last][last - offset] = m[i][last];
                m[i][last] = top;
            }
        }
    }

}
