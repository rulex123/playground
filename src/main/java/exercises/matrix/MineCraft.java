package exercises.matrix;

// actually called mine craft
public class MineCraft {


    public static boolean canCraftTorch(Character[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (!isRowEmpty(grid, i)) {
                int coalIndex = doesRowContainOneMatch(grid, i, Character.valueOf('C'));
                if (coalIndex != -1) {
                    if (isRowEmpty(grid, i + 1)) {
                        return false;
                    } else {
                        int stickIndex = doesRowContainOneMatch(grid, i + 1, Character.valueOf('S'));
                        if (stickIndex != -1 && stickIndex == coalIndex) {
                            return areAllRowsEmpty(i + 1, grid);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }


    private static boolean areAllRowsEmpty(int below, Character[][] grid) {
        if (below == grid.length - 1) // is 'below' the last row of the grid?
            return true;

        for (int i = below + 1; i < grid.length; i++) {
            if (!isRowEmpty(grid, i)) {
                return false;
            }
        }

        return true;
    }


    private static void checkBounds(int index, int upperBound, int lowerBound) {
        if (index < lowerBound || index > upperBound)
            throw new IndexOutOfBoundsException();
    }


    private static int doesRowContainOneMatch(Character[][] grid, int index, Character lookingFor) {
        checkBounds(index, grid.length - 1, 0);

        Character[] row = grid[index];
        int matchCounter = 0;
        int matchIndex = 0;

        for (int i = 0; i < row.length; i++) {
            if (row[i] != null && row[i].equals(lookingFor)) {
                matchCounter++;
                matchIndex = i;
            }
        }

        return matchCounter == 1 ? matchIndex : -1;
    }


    private static boolean isRowEmpty(Character[][] grid, int index) {
        checkBounds(index, grid.length - 1, 0);

        Character[] row = grid[index];
        for (int i = 0; i < row.length; i++) {
            if (row[i] != null) {
                return false;
            }
        }

        return true;
    }

}
