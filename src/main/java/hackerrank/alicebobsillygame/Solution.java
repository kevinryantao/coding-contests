package hackerrank.alicebobsillygame;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int[] numPrimesCache = new int[100001];

    public static void main(String[] args) {
        fillPrimeCache();
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            System.out.println(runGame(n));
        }
    }

    private static void fillPrimeCache() {
        boolean[] possiblePrimes = new boolean[100001];
        for (int i = 2; i < possiblePrimes.length; i++){
            possiblePrimes[i] = true;
        }

        for (int i = 2; i < possiblePrimes.length; i++){
            if (!possiblePrimes[i]){
                continue;
            }
            for (int j = 2; i * j < possiblePrimes.length; j++){
                possiblePrimes[i*j] = false;
            }
        }

        int numPrimesSoFar = 0;
        for (int i = 2; i < possiblePrimes.length; i++){
            if(possiblePrimes[i]){
                numPrimesSoFar++;
            }
            numPrimesCache[i] = numPrimesSoFar;
        }
    }

    private static String runGame(int n) {
        if(numPrimesLTE(n) % 2 == 0){
            return "Bob";
        } else {
            return "Alice";
        }
    }

    private static int numPrimesLTE(int n) {
        if (n < numPrimesCache.length){
            return numPrimesCache[n];
        } else {
            return numPrimesCache[100000];
        }
    }
}
