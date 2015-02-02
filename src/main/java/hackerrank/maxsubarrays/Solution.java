package hackerrank.maxsubarrays;

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

        for (int i = 0; i < numTestCases; i++) {
            long[] sizeAndModulo = parseLongsFromStringArray(scanner.nextLine().split(" "));
            long size = sizeAndModulo[0];
            long modulo = sizeAndModulo[1];

            long[] array = parseLongsFromStringArray(scanner.nextLine().split(" "));

            System.out.println(findMaxSubarray(array, modulo));

        }
    }

    private static long findMaxSubarray(long[] array, long modulo) {
        long maxSoFar = 0;
        long maxRunning = 0;
        int indexStart = 0;
        for (int i = 0; i < array.length; i++) {

            long newElement = array[i] % modulo;
            if (newElement == 0) {
                continue;
            }

            long newMaxRunning = (maxRunning + newElement) % modulo;

            if(newMaxRunning < maxRunning){
                // then modify the start index
                long trimmedMax = newMaxRunning;
                for(int j = indexStart; j < i; j++){
                    trimmedMax = trimmedMax - (array[j]) % modulo;
                    if(trimmedMax < 0){
                        trimmedMax = trimmedMax + modulo;
                    }
                    if(trimmedMax > maxRunning){
                        indexStart = j + 1;
                        maxRunning = trimmedMax;
                        break;
                    }
                }
                if(maxRunning != trimmedMax){
                    maxRunning = newMaxRunning;
                }
            } else {
                maxRunning = newMaxRunning;
            }
            if(maxRunning > maxSoFar){
                maxSoFar = maxRunning;
            }
        }
        return maxSoFar;
    }


    private static long[] parseLongsFromStringArray(String[] string) {
        long[] result = new long[string.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Long.parseLong(string[i]);
        }
        return result;
    }
}