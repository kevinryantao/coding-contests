package codeforces;

import java.util.Scanner;

/**
 * Author: ktao
 * Copyright 2012
 */
public class TPrimes {

    public static void main(String[] args) {
        int strength, n;
        Scanner myScanner = new Scanner(System.in);
        n = Integer.valueOf(myScanner.nextLine());
        String[] mySecondLine = myScanner.nextLine().split(" ");
        long[] myNumbers = new long[n];
        for (int i = 0; i < n; i++) {
            myNumbers[i] = Long.valueOf(mySecondLine[i]);
        }
        for (int i = 0; i < n; i++) {
            if (isTPrime(myNumbers[i])) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean isTPrime(long aInteger) {
        //conditions: must be square of a prime number
        long roundedSquareRoot = (long) Math.sqrt(aInteger);
        return roundedSquareRoot * roundedSquareRoot == aInteger && isPrime(roundedSquareRoot);
    }

    public static boolean isPrime(long aInteger) {
        if (aInteger == 1){
            return false;
        }
        if (aInteger == 2){
            return true;
        }
        if (aInteger % 2 == 0) {
            return false;
        }
        long n = (long) Math.sqrt(aInteger);
        for (long i = 3; i <= n; i += 2) {
            if (aInteger % i == 0) {
                return false;
            }
        }
        return true;
    }
}