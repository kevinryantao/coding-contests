package facebookhackercup.round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by ktao on 1/17/15.
 */
public class HomeworkPart2 {

    PrimacityLoader primacityLoader;

    public static void main(String[] args) throws FileNotFoundException {

        HomeworkPart2 homeworkPart2 = new HomeworkPart2();

        System.out.println("Primacities loaded");

        Scanner scanner = new Scanner(new File("homework.txt"));
        int numTestCases = Integer.parseInt(scanner.nextLine());

        PrintWriter results = new PrintWriter("homework_output.txt");
        for(int i = 1; i <= numTestCases; i++){
            int[] input = parseIntsFromStringArray(scanner.nextLine().split(" "));
            results.println("Case #"+i+": "+homeworkPart2.howManyBetween(input[0], input[1], input[2]));
        }
        results.close();
    }

    private int howManyBetween(int min, int max, int primacityValue) {
        if(primacityValue > 8){
            return 0;
        }
        int count = 0;
        for(int i = min; i <= max; i++){
            if(primacityLoader.getPrimacity(i) == primacityValue){
                count++;
            }
        }
        return count;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }


    public HomeworkPart2() throws FileNotFoundException {
        primacityLoader = new PrimacityLoader("primacityCache.csv");
    }
}
