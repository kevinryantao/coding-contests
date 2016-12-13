package hackerrank.gamingarray;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//https://www.hackerrank.com/contests/hourrank-15/challenges/an-interesting-game-1

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numGames = in.nextInt();
        for(int a0 = 0; a0 < numGames; a0++){
            int n = in.nextInt();

            int[] gameBoard = new int[n];

            for(int i = 0; i < n; i++){
                gameBoard[i] = in.nextInt();
            }

            System.out.println(playGame(gameBoard));


        }
    }

    private static String playGame(int[] gameBoard) {
        int maxSoFar = gameBoard[0];
        int numNewMaxes = 1;
        for(int i = 1; i < gameBoard.length; i++){
            if(gameBoard[i] > maxSoFar){
                maxSoFar = gameBoard[i];
                numNewMaxes++;
            }
        }
        if(numNewMaxes % 2 == 1){
            return "BOB";
        } else {
            return "ANDY";
        }
    }
}
