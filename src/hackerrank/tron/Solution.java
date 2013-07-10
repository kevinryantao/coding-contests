package hackerrank.tron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /* Head ends here */
    static void nextMove(String player, int[] pos, String[] board) {
        //board is 13x13 with a border around it
        //player is either "r" or "g"
        int playerInt = player.equals("r") ? 0 : 1;

        int[] selfLocation = new int[]{pos[playerInt * 2], pos[playerInt * 2 + 1]};
        int[] opponentLocation = new int[]{pos[(1 - playerInt) * 2], pos[(1 - playerInt) * 2 + 1]};

        if (isInBeginning(selfLocation)) {
            findNextBeginningMove(playerInt, selfLocation, opponentLocation, board);
            return;
        } else if (isIn2ndPhase(selfLocation)) {
            go2ndPhase(playerInt, selfLocation, opponentLocation, board);
            return;
        }
        stayAliveAsLongAsPossible(selfLocation, board);
    }

    static void go2ndPhase(int playerInt, int[] selfLocation, int[] opponentLocation, String[] board) {
        if (playerInt == 0) {
            if (!tryRight(selfLocation, board)) {
                pickOpenDirectionRandomly(selfLocation, board);
            }
        } else {
            if (!tryLeft(selfLocation, board)) {
                pickOpenDirectionRandomly(selfLocation, board);
            }
        }
    }

    static void pickOpenDirectionRandomly(int[] selfLocation, String[] board) {
        List<Integer> directions = new ArrayList<Integer>();
        directions.add(1);
        directions.add(2);
        directions.add(3);
        directions.add(4);
        Collections.shuffle(directions);
        for (int i : directions) {
            if (i == 1) {
                if (tryRight(selfLocation, board)) {
                    return;
                }
            }
            if (i == 2) {
                if (tryLeft(selfLocation, board)) {
                    return;
                }
            }
            if (i == 3) {
                if (tryUp(selfLocation, board)) {
                    return;
                }
            }
            if (i == 4) {
                if (tryDown(selfLocation, board)) {
                    return;
                }
            }
        }
    }

    static boolean isLocationOpen(int[] location, String[] board) {
        return isLocationOpen(location[0], location[1], board);
    }

    static boolean isLocationOpen(int row, int col, String[] board) {
        return board[row].charAt(col) == '-';
    }

    static void stayAliveAsLongAsPossible(int[] selfLocation, String[] board) {
        //pickOpenDirectionRandomly(selfLocation, board);
        List<int[]> myNeighbors = getOpenNeighbors(selfLocation, board);

        int[] leastNeighbor = findNeighborWithLeastNeighbors(myNeighbors, board);

        String direction = findDirectionToMove(leastNeighbor, selfLocation);

        System.out.println(direction);
    }

    private static String findDirectionToMove(int[] leastNeighbor, int[] selfLocation) {
        if(leastNeighbor[0] > selfLocation[0]){
            return "DOWN";
        }
        if(leastNeighbor[1] > selfLocation[1]){
            return "RIGHT";
        }
        if(leastNeighbor[0] < selfLocation[0]){
            return "UP";
        }
        if(leastNeighbor[1] < selfLocation[1]){
            return "LEFT";
        }
        return null;
    }

    private static int[] findNeighborWithLeastNeighbors(List<int[]> myNeighbors, String[] board) {
        int runningMinimumNeighbors = 4;
        int[] coordinateOfMin = myNeighbors.get(0);
        for(int[] position : myNeighbors){
            List<int[]> neighborsNeighbors = getOpenNeighbors(position, board);
            if(neighborsNeighbors.size() < runningMinimumNeighbors && neighborsNeighbors.size() > 0){
                runningMinimumNeighbors = neighborsNeighbors.size();
                coordinateOfMin = position;
            }
        }
        return coordinateOfMin;
    }


    private static List<int[]> getOpenNeighbors(int[] selfLocation, String[] board) {
        List<int[]> myOpenNeighbors = new ArrayList<int[]>();
        if (isLocationOpen(selfLocation[0] + 1, selfLocation[1], board)) {
            myOpenNeighbors.add(new int[]{selfLocation[0] + 1, selfLocation[1]});
        }
        if (isLocationOpen(selfLocation[0] - 1, selfLocation[1], board)) {
            myOpenNeighbors.add(new int[]{selfLocation[0] - 1, selfLocation[1]});
        }
        if (isLocationOpen(selfLocation[0], selfLocation[1] + 1, board)) {
            myOpenNeighbors.add(new int[]{selfLocation[0], selfLocation[1] + 1});
        }
        if (isLocationOpen(selfLocation[0], selfLocation[1] - 1, board)) {
            myOpenNeighbors.add(new int[]{selfLocation[0], selfLocation[1] - 1});
        }
        Collections.shuffle(myOpenNeighbors);
        return myOpenNeighbors;
    }


    static boolean isIn2ndPhase(int[] selfLocation) {
        return selfLocation[0] == 6 || selfLocation[0] == 8;
    }

    static boolean isBoardOpenToRight(int[] selfLocation, String[] board) {
        return board[selfLocation[0]].charAt(selfLocation[1] + 1) == '-';
    }

    static boolean isBoardOpenToLeft(int[] selfLocation, String[] board) {
        return board[selfLocation[0]].charAt(selfLocation[1] - 1) == '-';
    }

    static boolean isBoardOpenToUp(int[] selfLocation, String[] board) {
        return board[selfLocation[0] - 1].charAt(selfLocation[1]) == '-';
    }

    static boolean isBoardOpenToDown(int[] selfLocation, String[] board) {
        return board[selfLocation[0] + 1].charAt(selfLocation[1]) == '-';
    }

    static boolean tryRight(int[] selfLocation, String[] board) {
        if (isBoardOpenToRight(selfLocation, board)) {
            System.out.println("RIGHT");
            return true;
        }
        return false;
    }

    static boolean tryLeft(int[] selfLocation, String[] board) {
        if (isBoardOpenToLeft(selfLocation, board)) {
            System.out.println("LEFT");
            return true;
        }
        return false;
    }

    static boolean tryUp(int[] selfLocation, String[] board) {
        if (isBoardOpenToUp(selfLocation, board)) {
            System.out.println("UP");
            return true;
        }
        return false;
    }

    static boolean tryDown(int[] selfLocation, String[] board) {
        if (isBoardOpenToDown(selfLocation, board)) {
            System.out.println("DOWN");
            return true;
        }
        return false;
    }

    static void findNextBeginningMove(int playerInt, int[] selfLocation, int[] opponentLocation, String[] board) {
        if (playerInt == 0) {
            if (isBoardOpenToRight(selfLocation, board)) {
                System.out.println("RIGHT");
            } else {
                goOppositeOfOpponent(opponentLocation[0]);
            }
        } else {
            if (isBoardOpenToLeft(selfLocation, board)) {
                System.out.println("LEFT");
            } else {
                goOppositeOfOpponent(opponentLocation[0]);
            }
        }
    }

    static void goOppositeOfOpponent(int opponentYAxis) {
        if (opponentYAxis > 7) {
            System.out.println("UP");
        } else {
            System.out.println("DOWN");
        }
    }

    static boolean isInBeginning(int[] ownPosition) {
        if (ownPosition[0] == 7) {
            return true;
        }
        return false;
    }

/* Tail starts here */

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\n");
        String player = in.next();
        String pos = in.next();
        String[] str_pos = pos.split(" ");
        int[] position = new int[4];
        for(int i=0;i<4;i++) {
            position[i] = Integer.parseInt(str_pos[i]);
        }
        String board[] = new String[15];
        for(int i = 0; i < 15; i++) {
            board[i] = in.next();
        }

        nextMove(player,position,board);
    }
}