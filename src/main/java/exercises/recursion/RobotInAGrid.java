package exercises.recursion;

import java.util.*;


/**
 * Imagine a robot sitting on the upper left corner of a grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells
 * are "off limits" such that the robot cannot step on them. Design an algorithm to find a
 * path for the robot from the top left to the bottom right.
 */
public class RobotInAGrid {

    public static void main(String[] args) {

        Grid grid = new Grid(4, 4);
        grid.markCellAsOffLimits(2, 2);
        grid.markCellAsOffLimits(4, 1);
        grid.markCellAsOffLimits(1, 2);
        grid.markCellAsOffLimits(1, 4);
        grid.markCellAsOffLimits(3, 3);

        // there is a path in this case!
        System.out.println(grid.findPathFromTopLeftCornerToBottomRightCorner());

        // now we change the grid so that there is no path
        grid.markCellAsOffLimits(3, 2);
        System.out.println(grid.findPathFromTopLeftCornerToBottomRightCorner());
    }


    private static class Cell {

        int row;
        int column;
        boolean offLimits;

        Cell(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        void markAsOffLimits() {
            this.offLimits = true;
        }

        boolean isOffLimits() {
            return this.offLimits;
        }

        @Override
        public String toString() {
            return "[" +
                    "r" + row +
                    "c" + column +
                    offLimits +
                    ']';
        }
    }

    private static class Grid {

        private final Cell[][] grid;
        private int rows;
        private int columns;

        // rows and columns are 1 based
        Grid(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            this.grid = new Cell[rows][columns];
            // populate the grid with cells
            for (int i = 0; i < rows; i++) {
                Cell[] row = grid[i];
                for (int j = 0; j < columns; j++) {
                    Cell cell = new Cell(i, j);
                    grid[i][j] = cell;
                }
            }
        }

        void markCellAsOffLimits(int row, int column) {
            if (row < 0 || row > this.rows) {
                throw new IllegalArgumentException("invalid row!");
            }

            if (column < 0 || column > this.columns) {
                throw new IllegalArgumentException("invalid column!");
            }

            Cell cell = this.grid[row - 1][column - 1];
            cell.markAsOffLimits();
        }

        List<Cell> findPathFromTopLeftCornerToBottomRightCorner() {
            List<Cell> path = new ArrayList<>();
            boolean foundPath = findPathRecursive(0, 0, path, new HashSet<>());
            if (foundPath) {
                return path;
            } else {
                return Collections.emptyList();
            }
        }

        boolean findPathRecursive(int row, int col, List<Cell> pathSoFar, Set<Cell> alreadyExplored) {
            // check if we have gone out of bounds
            if (row == this.rows || col == this.columns) {
                return false;
            }

            Cell currCell = grid[row][col];

            // check if we are on a cell that is offlimits
            if (currCell.isOffLimits()) {
                return false;
            }

            // check if we have been here before
            if (alreadyExplored.contains(currCell)) {
                return false;
            }

            // check if we are on the bottom right corner
            if (row == this.rows - 1 && col == this.columns - 1) {
                pathSoFar.add(currCell);
                return true;
            }

            // at this point we are in bounds and we haven't made it to
            // the destination yet, so keep looking!

            // is there a cell to my right or below me that I can visit?
            pathSoFar.add(currCell);
            if (findPathRecursive(row, col + 1, pathSoFar, alreadyExplored) || findPathRecursive(row + 1,
                    col, pathSoFar, alreadyExplored)) {
                return true;
            }

            // dead end!
            alreadyExplored.add(currCell);
            return false;
        }

        @Override
        public String toString() {
            // print row by row
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                Cell[] row = grid[i];
                for (int j = 0; j < columns; j++) {
                    Cell cell = grid[i][j];
                    sb.append(cell.toString() + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
