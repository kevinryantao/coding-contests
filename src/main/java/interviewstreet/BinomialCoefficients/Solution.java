package interviewstreet.BinomialCoefficients;

import java.math.BigInteger;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Solution {

    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger THREE = BigInteger.valueOf(3);

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int myNumCases = Integer.valueOf(myScanner.nextLine());
        for (int i = 0; i < myNumCases; i++) {
            String[] myLine = myScanner.nextLine().split(" ");
            BigInteger myN = new BigInteger(myLine[0]);
            BigInteger myPrime = new BigInteger(myLine[1]);
            System.out.println(findNumberOfZeroMod(myN,
                                                   myPrime));
        }
    }

    private static BigInteger findNumberOfZeroMod(BigInteger myN, BigInteger myPrime) {
        BigInteger myNotPrimes = myN.mod(myPrime);
        BigInteger myHalfPoint = myN.add(THREE).divide(TWO);
        BigInteger myCount = myN.subtract(myNotPrimes).subtract(myHalfPoint).multiply(TWO);
        if (myN.subtract(myNotPrimes).compareTo(myHalfPoint.subtract(ONE)) >= 0) {
            if (myN.mod(TWO).equals(ZERO)) {
                myCount = myCount.add(ONE);
            } else {
                myCount = myCount.add(TWO);
            }
        }
        return myCount.max(ZERO);
    }
}
