import java.util.*;

public class Mineswepper {
    public static void main(String[] args) {

        Library sl = new Library(); // Create an instance of the Library class
        Random r = new Random(); // Create an instance of the Random class
        Scanner in = new Scanner(System.in); // Create an instance of the Scanner class
        Date d1; // Declare a Date variable
        d1 = new Date(); // Initialize the Date variable

        System.out.print("How many bombs would you like: ");
        int bombschoiceUser = in.nextInt(); // Get the number of bombs from the user
        System.out.println(" ");
        long start = System.currentTimeMillis(); // Record the start time of the game

        // Initialize the game map with hidden squares
        String[][] Map = {
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " },
                { " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ ", " ■ " } };

        int bombs[][] = new int[10][10]; // Initialize the bomb map
        int x;
        int y;

        System.out.println("=========================================");
        System.out.println("============== Minesweeper ==============");
        System.out.println("=========================================");
        System.out.println(" ");
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
        sl.print(Map); // Print the initial game map

        boolean GameWinorLose = true; // Game status flag
        int firstround = 0; // First round flag

        while (GameWinorLose) {
            int ifbombsthere = 0;
            int row;
            int col;

            if (firstround == 0) {
                System.out.println("Please choose what square you would like to choose");
            } else {
                System.out.println(
                        "Please choose what square you would like to choose or put the number 123 in row to put a flag on a square");
            }

            System.out.print("Row: ");
            row = in.nextInt(); // Get the row from the user

            if (row == 123) { // If user chooses to place a flag
                System.out.println("Where would you like to put the flag");
                System.out.print("Row: ");
                int row1 = in.nextInt(); // Get the row for the flag
                System.out.print("coloumn: ");
                int col1 = in.nextInt(); // Get the column for the flag
                Map[row1][col1] = " X "; // Place the flag on the map
                System.out.println("    0   1   2   3   4   5   6   7   8   9");
                sl.print(Map); // Print the updated map
                continue; // Continue to the next iteration of the loop
            }

            System.out.print("coloumn: ");
            col = in.nextInt(); // Get the column from the user

            if (firstround == 0) {
                // Initialize variables to represent neighboring indexes
                int leftCol = col - 1;
                int rightCol = col + 1;
                int aboveRow = row - 1;
                int belowRow = row + 1;

                // Initialize the bomb map with no bombs
                for (int ctr123 = 0; ctr123 < 10; ctr123++) {
                    Arrays.fill(bombs[ctr123], 0);
                }

                // Place bombs randomly on the map
                for (int ctr = 0; ctr < bombschoiceUser; ctr++) {
                    x = r.nextInt(10);
                    y = r.nextInt(10);

                    // Ensure no bombs are placed in the first chosen square and its neighbors
                    if (x == row && y == col || x == row && y == leftCol || x == row && y == rightCol
                            || x == aboveRow && y == col || x == aboveRow && y == leftCol
                            || x == aboveRow && y == rightCol || x == belowRow && y == rightCol
                            || x == belowRow && y == col || x == belowRow && y == leftCol) {
                        ctr--;
                    } else {
                        bombs[x][y] = 1; // Place a bomb
                    }
                }

                // Initialize variables to represent neighboring cells' bomb counts in the array
                int leftBombCount = 0;
                int centerBombCount = 0;
                int rightBombCount = 0;
                int aboveLeftBombCount = 0;
                int aboveCenterBombCount = 0;
                int aboveRightBombCount = 0;
                int belowLeftBombCount = 0;
                int belowCenterBombCount = 0;
                int belowRightBombCount = 0;

                // Check if neighboring cells are within array bounds and update bomb counts
                if (leftCol >= 0) {
                    leftBombCount = sl.checkforbombs(bombs, row, leftCol);
                }
                if (rightCol < bombs[0].length) {
                    rightBombCount = sl.checkforbombs(bombs, row, rightCol);
                }

                if (aboveRow >= 0) {
                    if (leftCol >= 0) {
                        aboveLeftBombCount = sl.checkforbombs(bombs, aboveRow, leftCol);
                    }
                    aboveCenterBombCount = sl.checkforbombs(bombs, aboveRow, col);
                    if (rightCol < bombs[0].length) {
                        aboveRightBombCount = sl.checkforbombs(bombs, aboveRow, rightCol);
                    }
                }

                if (belowRow < bombs.length) {
                    if (leftCol >= 0) {
                        belowLeftBombCount = sl.checkforbombs(bombs, belowRow, leftCol);
                    }
                    belowCenterBombCount = sl.checkforbombs(bombs, belowRow, col);
                    if (rightCol < bombs[0].length) {
                        belowRightBombCount = sl.checkforbombs(bombs, belowRow, rightCol);
                    }
                }

                // Update the bomb count array
                int ifbombsthereArray[] = new int[9];
                ifbombsthereArray[0] = centerBombCount;
                ifbombsthereArray[1] = rightBombCount;
                ifbombsthereArray[2] = leftBombCount;
                ifbombsthereArray[3] = aboveRightBombCount;
                ifbombsthereArray[4] = aboveCenterBombCount;
                ifbombsthereArray[5] = aboveLeftBombCount;
                ifbombsthereArray[6] = belowRightBombCount;
                ifbombsthereArray[7] = belowCenterBombCount;
                ifbombsthereArray[8] = belowLeftBombCount;

                // Update the Map array based on the bomb count array
                String whatever[] = new String[10];

                for (int i = 0; i < 9; i++) {
                    whatever[i] = ifbombsthereArray[i] + "";

                    if (ifbombsthereArray[i] == 0) {
                        whatever[i] = " ";
                    }
                }

                // Update the Map with bomb counts
                Map[row][col] = " " + whatever[0] + " ";
                if (rightCol < bombs[0].length) {
                    Map[row][rightCol] = " " + whatever[1] + " ";
                }
                if (leftCol >= 0) {
                    Map[row][leftCol] = " " + whatever[2] + " ";
                }
                if (aboveRow >= 0) {
                    Map[aboveRow][col] = " " + whatever[4] + " ";
                    if (rightCol < bombs[0].length) {
                        Map[aboveRow][rightCol] = " " + whatever[3] + " ";
                    }
                    if (leftCol >= 0) {
                        Map[aboveRow][leftCol] = " " + whatever[5] + " ";
                    }
                }
                if (belowRow < bombs.length) {
                    Map[belowRow][col] = " " + whatever[7] + " ";
                    if (rightCol < bombs[0].length) {
                        Map[belowRow][rightCol] = " " + whatever[6] + " ";
                    }
                    if (leftCol >= 0) {
                        Map[belowRow][leftCol] = " " + whatever[8] + " ";
                    }
                }

                firstround++; // Increment firstround to prevent this block from executing again
            } // end of first round if statement

            if (bombs[row][col] == 1) { // Check if the chosen square has a bomb
                System.out.println(" ");
                System.out.println("You Lose");
                System.out.println(" ");
                break; // End the game
            }

            ifbombsthere = sl.checkforbombs(bombs, row, col); // Check for bombs around the chosen square

            if (ifbombsthere == 0) { // Update the map based on the bomb count
                Map[row][col] = "   ";
            } else {
                Map[row][col] = " " + ifbombsthere + " ";
            }

            System.out.println("    0   1   2   3   4   5   6   7   8   9");
            sl.print(Map); // Print the updated map

            int checker = 0;
            for (int x1 = 0; x1 < Map.length; x1++) {
                for (int y1 = 0; y1 < Map[0].length; y1++) {
                    if (Map[x1][y1].equals(" ■ ") || Map[x1][y1].equals(" X ")) {
                        checker++;
                    }
                }
            }

            if (checker == bombschoiceUser) { // Check if all bombs are flagged

                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start; // Calculate elapsed time
                long minutes = (timeElapsed / 1000) / 60;
                long seconds = (timeElapsed / 1000) % 60;
                System.out.println("***************************************************");
                System.out.println("You Won in " + minutes + " minutes and " + seconds + " seconds");
                System.out.println("***************************************************");
                break; // End the game
            }

        } // end of while loop

    } // end of main

} // end of class
