
package exercises.recursion;

import java.util.Arrays;

/**
 * Implement the "paint fill" function othat one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors), a point, and a new
 * color, fill the surrounding area until the color changes from the original color.
 */
public class PaintFill {

  public static void main(String[] args) {
    int rows = 4;
    int cols = 5;
    String[][] grid = new String[rows][cols];
    for (final String[] strings : grid) {
      Arrays.fill(strings, "yellow");
    }
    // original grid
    printGrid(grid);
    // paint the new color
    execute(grid, "blue", new Point(1, 2));
    // grid after painting
    printGrid(grid);
  }


  private static void execute(String[][] grid, String color, Point point) {
    // check if we have gone out of bounds
    if (point.row < 0 || point.row >= grid.length || point.column < 0
        || point.column >= grid[0].length) {
      return;
    }

    String currColor = grid[point.row][point.column];

    // check if this cell has already been painted
    if (currColor.equals(color)) {
      return;
    }

    // we are in bounds and current cell hasn't been colored yet
    grid[point.row][point.column] = color;

    // go through all neighboring cells and color them
    // cells above and below
    execute(grid, color, new Point(point.row + 1, point.column));
    execute(grid, color, new Point(point.row - 1, point.column));
    // cells to the left
    execute(grid, color, new Point(point.row, point.column - 1));
    execute(grid, color, new Point(point.row + 1, point.column - 1));
    execute(grid, color, new Point(point.row - 1, point.column - 1));
    // cells to the right
    execute(grid, color, new Point(point.row, point.column + 1));
    execute(grid, color, new Point(point.row + 1, point.column + 1));
    execute(grid, color, new Point(point.row - 1, point.column + 1));
  }

  private static class Point {

    final int row;
    final int column;

    private Point(final int row, final int column) {
      this.row = row;
      this.column = column;
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

}
