package facebookhackercup.sports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by ktao on 1/17/15.
 */
public class Sports {

    private static final int MODULO = 1000000007;

    private int[][] stressFreeMatrix = new int[2001][2001];
    private int[][] stressfulMatrix = new int[2001][2001];

    public static void main(String[] args) throws FileNotFoundException {
        Sports sports = new Sports();

        Scanner scanner = new Scanner(new File("winning_at_sports.txt"));

        PrintWriter writer  = new PrintWriter("sports_out.txt");

        int numCases = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i <= numCases; i++){
            int[] finalScores = parseIntsFromStringArray(scanner.nextLine().split("-"));
            int stressFreeCombos = sports.getStressFreeCombos(finalScores[0],finalScores[1]);
            int stressfulCombos = sports.getStressfulCombos(finalScores[0], finalScores[1]);
            writer.println("Case #"+i+": "+stressFreeCombos+" "+stressfulCombos);
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


    public Sports(){
        // fill up the stress-free matrix
        fillStressFreeMatrix();
        // fill up the stressful matrix
        fillStressfulMatrix();

        // then process input
    }

    public int getStressFreeCombos(int myScore, int oppScore){
        return stressFreeMatrix[myScore][oppScore];
    }


    public int getStressfulCombos(int myScore, int oppScore){
        return stressfulMatrix[myScore][oppScore];
    }


    private void fillStressFreeMatrix() {
        stressFreeMatrix[1][0] = 1;
        for(int myScore = 2; myScore < 2001; myScore++){
            for(int oppScore = 0; oppScore < myScore; oppScore++){
                if(oppScore == 0) {
                    stressFreeMatrix[myScore][oppScore] = 1;
                } else {
                    int total = (stressFreeMatrix[myScore - 1][oppScore] + stressFreeMatrix[myScore][oppScore - 1]) % MODULO;
                    stressFreeMatrix[myScore][oppScore] = total;
                }
            }
        }
    }

    private void fillStressfulMatrix() {
        stressfulMatrix[0][0] = 1;
        for(int myScore = 0; myScore < 2001; myScore++){
            for(int oppScore = 0; oppScore < 2001; oppScore++){
                if(myScore == 0){
                    stressfulMatrix[myScore][oppScore] = 1;
                } else if(oppScore > myScore){
                    int total = (stressfulMatrix[myScore - 1][oppScore] + stressfulMatrix[myScore][oppScore - 1]) % MODULO;
                    stressfulMatrix[myScore][oppScore] = total;
                } else {
                    // in the final scoring stretch
                    stressfulMatrix[myScore][oppScore] = stressfulMatrix[myScore - 1][oppScore];
                }
            }
        }
    }


}
