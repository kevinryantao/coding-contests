package codeforces.round287;

import java.util.Scanner;

/**
 * Created by ktao on 1/23/15.
 */
public class AmrAndPins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int radius = inputs[0];
        int xStart = inputs[1];
        int yStart = inputs[2];
        int xDestination = inputs[3];
        int yDestination = inputs[4];

        double xDiff = xDestination - xStart;
        double yDiff = yDestination - yStart;

        double distance = Math.sqrt(xDiff*xDiff + yDiff * yDiff);
        System.out.println((int) Math.ceil(distance / (radius * 2)));

    }




    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

}
