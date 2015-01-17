package facebookhackercup;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by ktao on 1/13/15.
 */
public class LaserMaze {


    public static void main(String[] args) throws Exception {
        Scanner scanner =  new Scanner(new File("laser_maze_easy.txt"));

        // parse the input
        int numMazes = Integer.parseInt(scanner.nextLine());

        LaserMaze laserMaze = new LaserMaze();

        PrintWriter writer = new PrintWriter("laser_maze_out.txt");

        for (int i = 1; i <= numMazes; i++) {
            int[] dimensions = parseIntsFromStringArray(scanner.nextLine().split(" "));

            char[][] board = new char[dimensions[0]][dimensions[1]];

            for(int row = 0; row < dimensions[0]; row++){
                char[] line = scanner.nextLine().toCharArray();
                for(int col = 0; col < dimensions[1]; col++){
                    board[row][col] = line[col];
                }
            }

            writer.println("Case #" + i + ": " + laserMaze.solve(board));
            System.out.println("Case #" + i + ": " + laserMaze.solve(board));
        }
        writer.close();
    }

    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

    public String solve(char[][] board) throws Exception {
        Queue<MazeData> bfsQueue = new LinkedList<MazeData>();
        bfsQueue.add(new MazeData(board, 0));
        int[][] visited = new int[board.length][board[0].length];
        visit(board, visited);
        int currentStep = 0;
        char[][] oldBoard = board;

        while (!bfsQueue.isEmpty()) {

            MazeData data = bfsQueue.poll();
            if(data.getSteps() > currentStep){
                printProgress(oldBoard, visited, currentStep);
            }

            // check if dead
            if (data.getSteps() > 0 && dead(data.getBoard())) { // skip death check on first step
                continue;
            }
            // check if done
            if (done(data.getBoard())) {
                return data.steps + "";
            }
            char[][] rotatedLasers = rotateLasers(data.getBoard());
            for (Direction direction : Direction.values()) {
                MazeData newMazeData = getNewMazeData(rotatedLasers, visited, data.getSteps(), direction);
                if(newMazeData != null){
                    bfsQueue.add(newMazeData);
                }
            }

            if(data.getSteps() > currentStep){
                currentStep = data.getSteps();
                oldBoard = rotatedLasers;
            }


        }
        return "impossible";
    }

    private void printProgress(char[][] oldBoard, int[][] visited, int currentStep) {
        System.out.println("-------- Printing step " + currentStep + " ------------");
        for(int i = 0; i < oldBoard.length; i++){
            for(int j = 0; j < oldBoard[i].length; j++){
                if(oldBoard[i][j] != '.'){
                    System.out.print(oldBoard[i][j]);
                } else if (visited[i][j] != 0){
                    System.out.print(visited[i][j]);
                } else {
                    System.out.print(oldBoard[i][j]);
                }
            }
            System.out.print("\n");
        }
        System.out.println("-------- Printing step " + currentStep + " ------------");
    }

    private void visit(char[][] board, int[][] visited) throws Exception {
        int playerNS = -100;
        int playerEW = -100;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'S') {
                    playerNS = i;
                    playerEW = j;
                }
            }
        }
        if (playerNS == -100 || playerEW == -100) {
            throw new Exception("Could not find S");
        }
        visited[playerNS][playerEW]++;
    }

    private char[][] deepCloneChars(char[][] board) {
        char[][] newCharArray = new char[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                newCharArray[i][j] = board[i][j];
            }
        }
        return newCharArray;
    }

    private int[][] deepCloneInts(int[][] visited) {
        int[][] newIntArray = new int[visited.length][visited[0].length];
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[0].length; j++){
                newIntArray[i][j] = visited[i][j];
            }
        }
        return newIntArray;
    }


    private char[][] rotateLasers(char[][] board) {
        char[][] clonedBoard = deepCloneChars(board);
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(clonedBoard[i][j] == '^'){
                    clonedBoard[i][j] = '>';
                }else if(clonedBoard[i][j] == '>'){
                    clonedBoard[i][j] = 'V';
                }else if(clonedBoard[i][j] == 'V'){
                    clonedBoard[i][j] = '<';
                }else if(clonedBoard[i][j] == '<'){
                    clonedBoard[i][j] = '^';
                }
            }
        }
        return clonedBoard;
    }

    private MazeData getNewMazeData(char[][] board, int[][] visited, int steps, Direction direction) throws Exception {
        int playerNS = -100;
        int playerEW = -100;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'S') {
                    playerNS = i;
                    playerEW = j;
                }
            }
        }
        if (playerNS == -100 || playerEW == -100) {
            throw new Exception("Could not find S");
        }
        int newNS = playerNS + direction.getNorthSouth();
        int newEW = playerEW + direction.getEastWest();
        // check if out of bounds
        if (newNS < 0 || newNS >= board.length || newEW < 0 || newEW >= board[0].length) {
            return null;
        }
        // check if there's a wall or laser there
        if (board[newNS][newEW] == '#' || isLaser(board[newNS][newEW])) {
            return null;
        }
        // check if you've already been there twice
        if (visited[newNS][newEW] >= 2) {
            return null;
        }
        visited[newNS][newEW]++;
        char[][] newBoard = deepCloneChars(board);
        newBoard[playerNS][playerEW] = '.';
        newBoard[newNS][newEW] = 'S';
        return new MazeData(newBoard, steps + 1);
    }

    private boolean dead(char[][] board) throws Exception {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isLaser(board[i][j])) {
                    Direction laserDirection = getLaserDirection(board[i][j]);
                    int laserPathNS = i + laserDirection.getNorthSouth();
                    int laserPathEW = j + laserDirection.getEastWest();
                    while(laserPathNS >= 0 && laserPathEW >= 0 && laserPathNS < board.length && laserPathEW < board[i].length){
                        if(board[laserPathNS][laserPathEW] == 'S'){
                            return true; // player has been shot by laser
                        } else if(board[laserPathNS][laserPathEW] == '#' || isLaser(board[laserPathNS][laserPathEW])){
                            break; // laser path has been stopped by a wall or laser
                        } else {
                            laserPathNS += laserDirection.getNorthSouth();
                            laserPathEW += laserDirection.getEastWest();
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isLaser(char c) {
        return c == 'V' || c == '<' || c == '>' || c == '^';
    }

    private boolean done(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'G') {
                    return false;
                }
            }
        }
        return true;
    }

    private Direction getLaserDirection(char laser) throws Exception {
        switch (laser) {
            case '^':
                return Direction.NORTH;
            case '<':
                return Direction.WEST;
            case 'V':
                return Direction.SOUTH;
            case '>':
                return Direction.EAST;
        }
        throw new Exception("Not a laser");
    }

    private static enum Direction {
        NORTH(-1, 0),
        EAST(0, 1),
        SOUTH(1, 0),
        WEST(0, -1);

        private int northSouth;
        private int eastWest;

        Direction(int eastWest, int northSouth) {
            this.northSouth = eastWest;
            this.eastWest = northSouth;
        }

        public int getNorthSouth() {
            return northSouth;
        }

        public int getEastWest() {
            return eastWest;
        }
    }

    private static class MazeData {
        private char[][] board;
        private int steps;

        public MazeData(char[][] board,  int steps) {
            this.board = board;
            this.steps = steps;
        }

        public char[][] getBoard() {
            return board;
        }


        public int getSteps() {
            return steps;
        }
    }
}
