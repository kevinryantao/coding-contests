package hackerrank.hackapril.ratrace;

/**
 * Created by ktao on 4/29/15.
 *
 * https://www.hackerrank.com/contests/101hack24/challenges/rat-race
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        int numberOfRats = Integer.parseInt(scanner.nextLine());

        int[] ratSpeeds = parseIntsFromStringArray(scanner.nextLine().split(" "));

        int[] ratDistances = parseIntsFromStringArray(scanner.nextLine().split(" "));

        List<Integer> fastestRats = new ArrayList<Integer>();

        double fastestTime = Integer.MAX_VALUE;

        for(int i = 0; i < numberOfRats; i++){
            double timeToFinish = 1.0 * ratDistances[i] / ratSpeeds[i];
            if(timeToFinish < fastestTime){
                fastestTime = timeToFinish;
                fastestRats = new ArrayList<Integer>();
                fastestRats.add(i+1);
            } else if(timeToFinish == fastestTime){
                fastestRats.add(i+1);
            }
        }

        for(int i : fastestRats){
            System.out.println(i);
        }
    }



    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

}