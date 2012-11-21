package codechef.ddish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //Conflip

    public static void main(String[] args) throws IOException {
        BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int myNumberOfTestCases = Integer.parseInt(myBufferedReader.readLine());

        for (int i = 0; i < myNumberOfTestCases; i++) {
            String[] myLine = myBufferedReader.readLine().split(" ");
            long myL = Long.parseLong(myLine[0]);
            long myR = Long.parseLong(myLine[1]);
            System.out.println(getDeliciousDishes(myL,
                                                  myR,
                                                  0));
        }
    }

    private static long getDeliciousDishes(long myL, long myR, int myStartingDigit) {
        int lengthL = Long.toString(myL).length();
        int lengthR = Long.toString(myR).length();
        //base case
        if (lengthL - 1 == myStartingDigit && lengthR - 1 == myStartingDigit) {
            int numDelicious = 0;
            for (long l = myL; l <= myR; l++) {
                if (isDelicious(l)) {
                    numDelicious++;
                }
            }
            return numDelicious;
        }
        //splitting case : when digits of L != digits of R
        if (lengthL != lengthR) {
            long mySplitPoint = (long) Math.pow(10,
                                                lengthR - 1);
            return getDeliciousDishes(myL,
                                      mySplitPoint - 1,
                                      myStartingDigit) + getDeliciousDishes(mySplitPoint,
                                                                            myR,
                                                                            myStartingDigit);
        }
        //special full case. 100... to 999...
        long myMin = (long) Math.pow(10,
                                            lengthR - 1);
        long myMax = myMin * 10 - 1;
        if(myL == myMin && myR == myMax){
            long numDelicious = 9;
            for(int i = 1; i < lengthL; i++){
                numDelicious *= (10 - i);
            }
            return numDelicious;
        }
        //recursive case. can assume number of digits same between L and R, and also that the first (startingDigit)'s
        // are the same.

        //might need lower split - myL , myL.charAt(myStartingDigit) + 1, then all zeros. Easier to do with arithmetic or charArray?

        long numDelicious = 0;
        double pow = Math.pow(10,
                              lengthL - myStartingDigit - 1);
        long lowerSplit = (long) (pow * Math.ceil( myL / pow));
        if(lowerSplit != myL){
            numDelicious += getDeliciousDishes(myL, lowerSplit, myStartingDigit + 1);
        }
        long upperSplit = (long) (pow * Math.ceil( myR / pow));
        if(upperSplit != myR){
            numDelicious += getDeliciousDishes(myR, upperSplit, myStartingDigit + 1);
        }
        // calculate between lower split and upper split.
        for(long l = lowerSplit; l < upperSplit; l += pow){

        }






        return 0;
    }

    private static boolean isDelicious(long l) {
        String myString = Long.toString(l);
        boolean[] myNumArray = new boolean[10];
        for (int i = 0; i < myString.length(); i++) {
            if (myNumArray[myString.charAt(i) - '0']) {
                return false;
            }
            myNumArray[myString.charAt(i) - '0'] = true;
        }
        return true;
    }
}
