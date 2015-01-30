package codeforces.round287;

import java.util.Scanner;

/**
 * Created by ktao on 1/23/15.
 */
public class GuessYourWayOut2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] hAndN = parseIntsFromStringArray(scanner.nextLine().split(" "));
        long height = hAndN[0];
        long exit = hAndN[1]-1;

        String pathToExit = findPathToExit(height, exit);

        long numNodesVisited = pathToExit.length();
        char lastChar = 'R';
        for(int i = 0 ; i < pathToExit.length(); i++){
            char thisChar = pathToExit.charAt(i);
            if(thisChar == lastChar){
                long extraNodes = (long) Math.pow(2, height - i) - 1;
                numNodesVisited += extraNodes;
            }
            lastChar = thisChar;
        }
        System.out.println(numNodesVisited);

    }

    private static String findPathToExit(long height, long exit) {
        StringBuilder stringBuilder = new StringBuilder();
        long currentValue = exit;
        for(int i = 0; i < height; i++){
            if(currentValue % 2 == 0){
                stringBuilder.append('L');
            } else {
                stringBuilder.append('R');
            }
            currentValue = currentValue / 2;
        }
        return stringBuilder.reverse().toString();

    }


    private static long[] parseIntsFromStringArray(String[] goalsString) {
        long[] result = new long[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Long.parseLong(goalsString[i]);
        }
        return result;
    }
}
