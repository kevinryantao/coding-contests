package codeforces;

import java.util.Scanner;

public class NonSquareEquation {

    public static void main(String[] args){
        long n;
        Scanner myScanner = new Scanner(System.in);
        n = myScanner.nextLong();

        long end = (long) Math.sqrt(n) + 1;
        long start = (long) Math.sqrt(n - end * 9 * Long.toString(end).length()) - 1;
        for(long i = start; i <= end; i++){
            long equation = getEquation(i);
            if(equation == n){
                System.out.println(i);
                return;
            }
        }
        System.out.println("-1");
    }

    public static long getEquation(long aX){
        return (aX * aX + aX * getSumDigits(aX));
    }

    public static long getSumDigits(long aLong){
        long mySum = 0;
        while(aLong > 0){
            mySum += aLong % 10;
            aLong /= 10;
        }
        return mySum;
    }

}
