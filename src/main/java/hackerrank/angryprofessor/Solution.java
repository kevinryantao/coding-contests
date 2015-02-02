package hackerrank.angryprofessor;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int numTestCases = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numTestCases; i++){
            int[] nAndK = parseIntsFromStringArray(scanner.nextLine().split(" "));
            int n = nAndK[0];
            int k = nAndK[1];
            int studentsPresentCount = 0;
            int[] arrivalTimes = parseIntsFromStringArray(scanner.nextLine().split(" "));
            for (int arrivalTime : arrivalTimes) {
                if (arrivalTime <= 0) {
                    studentsPresentCount++;
                }
            }
            if(studentsPresentCount >= k){
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

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