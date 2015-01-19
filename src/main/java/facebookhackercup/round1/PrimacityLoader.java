package facebookhackercup.round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ktao on 1/17/15.
 */
public class PrimacityLoader {

    int[] primacityArray;


    public PrimacityLoader(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        primacityArray = new int[10000001];
        int maxPrimacityValue = 0;
        while(scanner.hasNextLine()){
            int[] input = parseIntsFromStringArray(scanner.nextLine().split(","));
            primacityArray[input[0]] = input[1];
            if(input[1] > maxPrimacityValue){
                maxPrimacityValue = input[1];
            }
        }
        System.out.println("Max Primacity Value = " + maxPrimacityValue);
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }


    public int getPrimacity(int number){
        if(number >= 0 && number <= 10000000){
            return primacityArray[number];
        }
        System.out.println("ERROR " + number);
        return 0;
    }
}
