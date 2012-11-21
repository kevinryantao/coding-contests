package hackerrank.lightsout;

import java.util.*;

public class Solution {

    public static Coordinate getTopLeftMost(boolean[][] board) {
        for (int i = 0; i <= 14; i++) {
            for (int x = 0; x < 8 && x <= i; x++) {
                if (i - x < 8) {
                    if (board[x][i - x]) {
                        return new Coordinate(x,
                                              i - x);
                    }
                }
            }
        }
        // should never get here unless it's all empty
        return null;
    }

    public static int getSimulatedNumberOfSteps(boolean[][] board) {
        int numberOfSteps = 0;
        boolean[][] mySimulatedBoard = board;
        while (true) {
            //step one get the top left most
            Coordinate myTopLeft = getTopLeftMost(mySimulatedBoard);
            if (myTopLeft == null) {
                return numberOfSteps;
            }
            // turn off the top left
            mySimulatedBoard = turnOff(board,
                                       myTopLeft);
            numberOfSteps++;
        }
    }

    public static boolean[][] turnOff(boolean[][] board, Coordinate aCoordinate) {
        int x = aCoordinate.x;
        int y = aCoordinate.y;
        board[x][y] = false;
        if (x < 7) {
            board[x + 1][y] = ! board[x + 1][y];
        }
        if (y < 7) {
            board[x][y + 1] = ! board[x][y + 1];
        }
        return board;
    }

    /* Head ends here */
    /* Complete the function below to print 2 integers separated by a single space which will be your next move */
    static Coordinate nextMove(boolean[][] board) {
        PriorityQueue<Coordinate> myCoordinateList = getCoordinateQueue(board);
        Coordinate myBestCoordinateSoFar = getTopLeftMost(board);
        int myLowestNumberOfOuts = Integer.MAX_VALUE;
        if (myCoordinateList.size() <= 24) {
            for (Coordinate myCoordinate : myCoordinateList) {
                boolean[][] myHypotheticalBoard = deepCopyBooleanArray(board);
                myHypotheticalBoard = turnOff(myHypotheticalBoard,
                                              myCoordinate);
                int mySimulatedNumberOfSteps = getSimulatedNumberOfSteps(myHypotheticalBoard);
                if (mySimulatedNumberOfSteps % 2 == 0) {
                    boolean[][] myHypotheticalBoardCopy = deepCopyBooleanArray(board);
                    myHypotheticalBoardCopy = turnOff(myHypotheticalBoard,
                                                  myCoordinate);
                    int numberOfOuts = numberOfOpponentOuts(myHypotheticalBoardCopy);
                    if (numberOfOuts == 0) {
                        return myCoordinate;
                    } else if (numberOfOuts < myLowestNumberOfOuts) {
                        myBestCoordinateSoFar = myCoordinate;
                        myLowestNumberOfOuts = numberOfOuts;
                    }
                }
            }
        }
        return myBestCoordinateSoFar;
    }

    static int numberOfOpponentOuts(boolean[][] board) {
        PriorityQueue<Coordinate> myCoordinateList = getCoordinateQueue(board);
        int numberOfOpponentOuts = 0;
        if (myCoordinateList.size() <= 25) {
            for (Coordinate myCoordinate : myCoordinateList) {
                boolean[][] myHypotheticalBoard = deepCopyBooleanArray(board);
                myHypotheticalBoard = turnOff(myHypotheticalBoard,
                                              myCoordinate);
                int mySimulatedNumberOfSteps = getSimulatedNumberOfSteps(myHypotheticalBoard);
                if (mySimulatedNumberOfSteps % 2 == 0) {
                    numberOfOpponentOuts++;
                }
            }
        }
        return numberOfOpponentOuts;
    }

    private static PriorityQueue<Coordinate> getCoordinateQueue(boolean[][] board) {
        PriorityQueue<Coordinate> myCoordinateList = new PriorityQueue<Coordinate>(10,
                                                                                   getCoordinateComparator());
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    myCoordinateList.add(new Coordinate(i,
                                                        j));
                }
            }
        }
        return myCoordinateList;
    }

    private static boolean[][] deepCopyBooleanArray(boolean[][] board) {
        boolean[][] myHypotheticalBoard = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                myHypotheticalBoard[i][j] = board[i][j];
            }
        }
        return myHypotheticalBoard;
    }

    private static Comparator<Coordinate> getCoordinateComparator() {
        return new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return o1.x + o1.y - (o2.x + o2.y);
            }
        };
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int aX, int aY) {
            x = aX;
            y = aY;
        }

        public String toString() {
            return x + " " + y;
        }

    }

    /* Tail starts here */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int player;
        boolean board[][] = new boolean[8][8];

        //If player is 1, I'm the first player.
        //If player is 2, I'm the second player.
        player = in.nextInt();

        //Read the board now. The board is a 8x8 array filled with 1 or 0.
        for (int i = 0; i < 8; i++) {
            String myLine = in.next();
            for (int j = 0; j < myLine.length(); j++) {
                board[i][j] = myLine.charAt(j) == '1';
            }
        }
        System.out.println(nextMove(board));
    }
}