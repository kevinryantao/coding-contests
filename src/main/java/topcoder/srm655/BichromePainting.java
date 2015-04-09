package topcoder.srm655;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class BichromePainting
{
    public String isThatPossible(String[] board, int k)
    {
        if(k == 1){
            return "Possible";
        }

        char[][] charBoard = new char[board.length][board.length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length(); j++){
                charBoard[i][j] = board[i].charAt(j);
            }
        }

        Board classBoard = new Board(charBoard, board);

        while(!classBoard.paintMap.isEmpty()){
            List<Coordinate> list = classBoard.findCoordinatesOfNextKByK(k);
            if(list == null){
                return "Impossible";
            }
            for(Coordinate coordinate : list){
                if(classBoard.paintMap.containsKey(coordinate)){
                    classBoard.strBoard[coordinate.x] = createNewString(classBoard.strBoard[coordinate.x], coordinate.y);
                    classBoard.paintMap.remove(coordinate);
                }
            }
        }

        return "Possible";
    }

    private String createNewString(String s, int y) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(i != y){
                stringBuilder.append(s.charAt(i));
            } else {
                stringBuilder.append("*");
            }
        }
        return stringBuilder.toString();
    }


    private static class Board{
        String[] strBoard;
        Map<Coordinate, Character> paintMap = new HashMap<Coordinate, Character>();

        private Board(char[][] board, String[] strBoard){
            this.strBoard = strBoard;
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board.length; j++){
                    paintMap.put(new Coordinate(i,j), board[i][j]);
                }
            }
        }



        private List<Coordinate> findCoordinatesOfNextKByK(int k){
            for(int i = 0; i < strBoard.length; i++) {
                for (int j = 0; j <= strBoard.length - k; j++) {
                    String substring = strBoard[i].substring(j, j + k);
                    if(substring.contains("W") && !substring.contains("B")){
                        List<Coordinate> list = fullCheck(i,j,j+k,"W","B",k);
                        if(list != null){
                            return list;
                        }
                    } else if(substring.contains("B") && !substring.contains("W")){
                        List<Coordinate> list = fullCheck(i,j,j+k,"B","W",k);
                        if(list != null){
                            return list;
                        }
                    }
                }
            }
            return null;
        }

        private List<Coordinate> fullCheck(int row, int colStart, int colEnd, String contains, String doesntContain, int k) {
            int rowsCount = 1;
            for(int i = 1; i < k; i++){ // going downwards
                if(row + i < strBoard.length){
                    String substring = strBoard[row + i].substring(colStart, colEnd);
                    if(!substring.contains(doesntContain)){
                        rowsCount++;
                    } else {
                        break;
                    }
                }
            }
            int rowsLeft = k - rowsCount;
            for(int i = 1; i <= rowsLeft; i++){ // going upwards
                if(row - i >= 0){
                    String substring = strBoard[row - i].substring(colStart, colEnd);
                    if(!substring.contains(doesntContain)){
                        rowsCount++;
                    } else {
                        break;
                    }
                }
            }
            if(rowsCount == k){
                // solved!
                List<Coordinate> list = new ArrayList<Coordinate>();
                for(int i = row - rowsLeft; i < row - rowsLeft + k; i++){
                    for(int j = colStart; j < colEnd; j++){
                        list.add(new Coordinate(i, j));
                    }
                }
                return list;

            } else {
                return null;
            }


        }


    }


    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            if (y != that.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }



    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String[] p0, int p1, boolean hasAnswer, String p2) {
        System.out.print("Test " + testNum + ": [" + "{");
        for (int i = 0; p0.length > i; ++i) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print("\"" + p0[i] + "\"");
        }
        System.out.print("}" + "," + p1);
        System.out.println("]");
        BichromePainting obj;
        String answer;
        obj = new BichromePainting();
        long startTime = System.currentTimeMillis();
        answer = obj.isThatPossible(p0, p1);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + "\"" + p2 + "\"");
        }
        System.out.println("Your answer:");
        System.out.println("\t" + "\"" + answer + "\"");
        if (hasAnswer) {
            res = answer.equals(p2);
        }
        if (!res) {
            System.out.println("DOESN'T MATCH!!!!");
        } else if ((endTime - startTime) / 1000.0 >= 2) {
            System.out.println("FAIL the timeout");
            res = false;
        } else if (hasAnswer) {
            System.out.println("Match :-)");
        } else {
            System.out.println("OK, but is it right?");
        }
        System.out.println("");
        return res;
    }
    public static void main(String[] args) {
        boolean all_right;
        all_right = true;

        String[] p0;
        int p1;
        String p2;

        // ----- test 0 -----
        p0 = new String[]{"BBBW","BWWW","BWWW","WWWW"};
        p1 = 3;
        p2 = "Possible";
        all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = new String[]{"BW","WB"};
        p1 = 2;
        p2 = "Impossible";
        all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = new String[]{"BWBWBB","WBWBBB","BWBWBB","WBWBBB","BBBBBB","BBBBBB"};
        p1 = 2;
        p2 = "Possible";
        all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = new String[]{"BWBWBB","WBWBWB","BWBWBB","WBWBWB","BWBWBB","BBBBBB"};
        p1 = 2;
        p2 = "Impossible";
        all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = new String[]{"BWBWBB","WBWBWB","BWBWBB","WBWBWB","BWBWBB","BBBBBB"};
        p1 = 1;
        p2 = "Possible";
        all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 5 -----
        p0 = new String[]{"BB","BB"};
        p1 = 2;
        p2 = "Possible";
        all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
        // ------------------

        if (all_right) {
            System.out.println("You're a stud (at least on the example cases)!");
        } else {
            System.out.println("Some of the test cases had errors.");
        }
    }

    // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!