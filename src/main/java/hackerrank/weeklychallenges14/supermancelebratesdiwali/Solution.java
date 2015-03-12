package hackerrank.weeklychallenges14.supermancelebratesdiwali;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/contests/w14/challenges/superman-celebrates-diwali
 */

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] params = parseIntsFromStringArray(scanner.nextLine().split(" "));

        int numBuildings = params[0];
        int buildingHeight = params[1];
        int heightDropPenalty = params[2];

        int[][] peoplePerBuilding = new int[numBuildings][buildingHeight + 1];

        for(int i = 0; i < numBuildings; i++){
            int[] peopleArray = parseIntsFromStringArray(scanner.nextLine().split(" "));
            for(int j = 1; j < peopleArray.length; j++){
                peoplePerBuilding[i][peopleArray[j]]++;
            }
        }

        Solution solution = new Solution();

        System.out.println(solution.solve(numBuildings, buildingHeight, heightDropPenalty, peoplePerBuilding));

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    private int solve(int numBuildings, int buildingHeight, int heightDropPenalty, int[][] peoplePerBuilding) {
        int[][] maxPeopleSavedDynamicProgramming = new int[numBuildings][buildingHeight+1];
        int[] maxPeopleSavedAtLevel = new int[buildingHeight + 1];


        for(int h = buildingHeight; h > 0; h--){
            for(int buildingNo = 0; buildingNo < numBuildings; buildingNo++){
                int peopleHere = peoplePerBuilding[buildingNo][h];

                int maxPeopleSavedSoFar = 0;
                if(h != buildingHeight){
                    maxPeopleSavedSoFar = maxPeopleSavedDynamicProgramming[buildingNo][h+1];
                }
                // if there's no one here there's no urgency for superman to rush here from another building
                if(peopleHere > 0 && h + heightDropPenalty <= buildingHeight){
                    maxPeopleSavedSoFar = Math.max(maxPeopleSavedSoFar, maxPeopleSavedAtLevel[h + heightDropPenalty]);
                }
                maxPeopleSavedDynamicProgramming[buildingNo][h] = maxPeopleSavedSoFar + peopleHere;
                maxPeopleSavedAtLevel[h] = Math.max(maxPeopleSavedAtLevel[h], maxPeopleSavedDynamicProgramming[buildingNo][h]);
            }
        }

        int maxPeopleSaved = 0;

        for(int i = 0; i < numBuildings; i++) {
            maxPeopleSaved = Math.max(maxPeopleSavedDynamicProgramming[i][1], maxPeopleSaved);
        }
        return maxPeopleSaved;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}