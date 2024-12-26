import java.util.*;

public class Library {

    // Method to print the game map
    static public void print(String array[][]) {

        int ctr = 0;

        for (int x = 0; x < array.length; x++) {
            System.out.print(ctr + " |");
            ctr++;
            for (int y = 0; y < array[0].length; y++) {
                System.out.print(array[x][y] + "|");
            }
            System.out.println();
        }
    }

    // Method to check for bombs around a given cell
    static public int checkforbombs(int bombs[][], int row, int col) {
        int ifbombsthere = 0;
        int numRows = bombs.length;
        int numCols = bombs[0].length;

        // Check bombs in the same row
        if (col - 1 >= 0 && bombs[row][col - 1] == 1) {
            ifbombsthere++;
        }
        if (col + 1 < numCols && bombs[row][col + 1] == 1) {
            ifbombsthere++;
        }

        // Check bombs in the row before
        if (row - 1 >= 0) {
            if (col - 1 >= 0 && bombs[row - 1][col - 1] == 1) {
                ifbombsthere++;
            }
            if (bombs[row - 1][col] == 1) {
                ifbombsthere++;
            }
            if (col + 1 < numCols && bombs[row - 1][col + 1] == 1) {
                ifbombsthere++;
            }
        }

        // Check bombs in the row after
        if (row + 1 < numRows) {
            if (col - 1 >= 0 && bombs[row + 1][col - 1] == 1) {
                ifbombsthere++;
            }
            if (bombs[row + 1][col] == 1) {
                ifbombsthere++;
            }
            if (col + 1 < numCols && bombs[row + 1][col + 1] == 1) {
                ifbombsthere++;
            }
        }

        return ifbombsthere; // Return the number of bombs around the given cell
    }
}
