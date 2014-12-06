package codeforces;

import java.util.Scanner;

public class ToAddOrNotToAdd {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String[] myFirstLine = myScanner.nextLine().split(" ");
        int N = Integer.valueOf(myFirstLine[0]);
        int K = Integer.valueOf(myFirstLine[1]);

        String[] mySecondLine = myScanner.nextLine().split(" ");
        int[] myInts = new int[mySecondLine.length];
        int myMaxValue = 0;
        for (int i = 0; i < mySecondLine.length; i++) {
            myInts[i] = Integer.valueOf(mySecondLine[i]);
            if (myInts[i] > myMaxValue) {
                myMaxValue = myInts[i];
            }
        }

        int[] myHistogram = new int[myMaxValue + 1];
        for (int i = 0; i < myInts.length; i++) {
            myHistogram[myInts[i]]++;
        }

        int myMaxOccurrences = 0;
        int myMaxIndex = 0;
        for (int i = 0; i <= myMaxValue; i++) {
            int total = myHistogram[i];
            int myK = K;
            for (int myNum = i - 1; myK >= i - myNum && myNum >= 0; myNum--) {
                int myNew = Math.min(myHistogram[myNum],
                                     myK / (i - myNum));
                myK -= myNew * (i - myNum);
                total += myNew;
            }
            if (total > myMaxOccurrences) {
                myMaxOccurrences = total;
                myMaxIndex = i;
            }
        }
        System.out.println(myMaxOccurrences + " " + myMaxIndex);

    }
}
