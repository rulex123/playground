package exercises.matrix;

/**
 * Given a 2D matrix of 0s and 1s, implement a function that returns the number of islands of 1s in the matrix. An
 * island is defined as a group of adjacent values that are all 1s. A cell in the given matrix is considered adjacent to
 * another cell if they are next to each other either on the same row or column. Note that 2 values of 1 are NOT part of
 * the same island if they're sharing only a mutual corner (i.e. they are diagonally neighbors).<br>
 *
 * @author emanno
 * @version 1.0 Aug 11, 2017
 */
public class IslandCount {

    private static final int[] ROWS_OFFSET_FOR_NEIGHBOURING_CELLS = new int[]{
            -1, 0, 0, 1
    };
    private static final int[] COLS_OFFSET_FOR_NEIGHBOURING_CELLS = new int[]{
            0, -1, 1, 0
    };


    public static void main(String[] args) {
        int[][] matrix = new int[5][5];

        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 0;
        matrix[0][3] = 1;
        matrix[0][4] = 0;

        matrix[1][0] = 0;
        matrix[1][1] = 0;
        matrix[1][2] = 1;
        matrix[1][3] = 1;
        matrix[1][4] = 1;

        matrix[2][0] = 1;
        matrix[2][1] = 0;
        matrix[2][2] = 0;
        matrix[2][3] = 1;
        matrix[2][4] = 0;

        matrix[3][0] = 0;
        matrix[3][1] = 1;
        matrix[3][2] = 1;
        matrix[3][3] = 0;
        matrix[3][4] = 0;

        matrix[4][0] = 1;
        matrix[4][1] = 0;
        matrix[4][2] = 1;
        matrix[4][3] = 0;
        matrix[4][4] = 1;

        System.out.println(countIslands(matrix));
    }


    public static int countIslands(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int noOfIslands = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) { // found a piece of land (represented by cell with number
                    // 1) that belongs to an island.. going to explore the
                    // island
                    exploreIsland(matrix, visited, i, j);
                    noOfIslands++;
                }
            }
        }

        return noOfIslands;
    }


    private static void exploreIsland(int[][] matrix, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= matrix[0].length)
            return;

        if (y < 0 || y >= matrix.length)
            return;

        if (visited[x][y])
            return;

        visited[x][y] = true;

        if (matrix[x][y] == 0)
            return;

        for (int i = 0; i < ROWS_OFFSET_FOR_NEIGHBOURING_CELLS.length; i++) {
            exploreIsland(matrix, visited, x + ROWS_OFFSET_FOR_NEIGHBOURING_CELLS[i],
                    y + COLS_OFFSET_FOR_NEIGHBOURING_CELLS[i]);
        }
    }

}
