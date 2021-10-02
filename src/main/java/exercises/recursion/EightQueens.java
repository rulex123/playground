package exercises.recursion;

import java.util.*;

public class EightQueens {

    public static void main(String[] args) {
        String[][] grid = new String[8][8];
        for (final String[] row : grid) {
            Arrays.fill(row, "-");
        }
        execute(grid, 7, new HashMap<>(), new ArrayList<>());
    }


    private static void execute(String[][] grid, int rowIndex, Map<Integer, Integer> map,
                                List<Cell> queens) {
        // check if current grid is invalid
        if (!isValidGrid(map, queens)) {
            return;
        }

        // grid is valid: check if we have placed all queens
        if (rowIndex == -1) {
            printGrid(grid);
            return;
        }

        int cols = grid[0].length;
        for (int j = 0; j < cols; j++) {

            // let's place a queen at the current cell
            grid[rowIndex][j] = "q";
            incrementQueensFreq(map, j);
            queens.add(new Cell(rowIndex, j));

            // recurse on next row of grid, after placing the queen
            execute(grid, rowIndex - 1, map, queens);

            // undo the queen placement before continuing
            grid[rowIndex][j] = "-";
            decrementQueensFreq(map, j);
            queens.remove(queens.size() - 1);
        }
    }

    private static boolean isValidGrid(final Map<Integer, Integer> map,
                                       final List<Cell> queens) {
        final Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        // check if there are any queens on the same column
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() > 1) {
                return false;
            }
        }

        // check if there are any queens on the same diagonal
        for (int i = 0; i < queens.size(); i++) {
            Cell queen = queens.get(i);
            for (int j = i + 1; j < queens.size(); j++) {
                Cell otherQueen = queens.get(j);
                if (queen.isOnDiagonal(otherQueen)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void incrementQueensFreq(final Map<Integer, Integer> map, int col) {
        if (map.containsKey(col)) {
            map.put(col, map.get(col) + 1);
        } else {
            map.put(col, 1);
        }
    }

    private static void decrementQueensFreq(final Map<Integer, Integer> map, int col) {
        if (map.containsKey(col)) {
            map.put(col, map.get(col) - 1);
            if (map.get(col) == 0) {
                map.remove(col);
            }
        }
    }

    private static void printGrid(String[][] grid) {
        // print row by row
        StringBuilder sb = new StringBuilder();
        for (String[] row : grid) {
            for (String color : row) {
                sb.append("[").append(color).append("]");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static class Cell {

        int row;
        int col;

        Cell(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        boolean isOnDiagonal(Cell cell) {
            int side_1 = Math.abs(cell.row - this.row);
            int side_2 = Math.abs(cell.col - this.col);

            return side_1 == side_2 ? true : false;
        }
    }

}
