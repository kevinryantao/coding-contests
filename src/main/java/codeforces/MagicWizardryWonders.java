package codeforces;

import java.util.Scanner;

public class MagicWizardryWonders {

    public static void main(String[] args) {
        int myNumCards, myFinalCard, myMaxCard;
        Scanner myScanner = new Scanner(System.in);
        String[] myFirstLine = myScanner.nextLine().split(" ");
        myNumCards = Integer.valueOf(myFirstLine[0]);
        myFinalCard = Integer.valueOf(myFirstLine[1]);
        myMaxCard = Integer.valueOf(myFirstLine[2]);

        int[] myCards = new int[myNumCards];
        int myRunningTotal = 0;
        for (int i = 0; i < myNumCards; i++) {
            if (i % 2 == 0) {
                myCards[i] = Math.max(Math.min(myFinalCard - myRunningTotal,
                                               myMaxCard),
                                      1);
                myRunningTotal += myCards[i];
            } else {
                myCards[i] = Math.max(Math.min(myRunningTotal - myFinalCard,
                                               myMaxCard),
                                      1);
                myRunningTotal -= myCards[i];
            }
        }
        if(myRunningTotal == myFinalCard){
            printArray(myCards);
        } else if(Math.abs(myRunningTotal - myFinalCard) <= 1){
            boolean works = false;
            if(myRunningTotal > myFinalCard){
                // add one to an odd
                for(int i = 1; i < myNumCards; i+= 2){
                    if(myCards[i] < myMaxCard){
                        myCards[i]++;
                        works = true;
                        break;
                    }
                }
            } else {
                // add one to an even
                for(int i = 0; i < myNumCards; i+= 2){
                    if(myCards[i] < myMaxCard){
                        myCards[i]++;
                        works = true;
                        break;
                    }
                }
            }
            if(works){
                printArray(myCards);
            } else {
                System.out.println("-1");
            }
        }
        else{
            System.out.println("-1");
        }
    }

    private static void printArray(int[] myCards) {
        for(int i = 0 ; i < myCards.length; i++){
            if(i == myCards.length - 1){
                System.out.print(myCards[i]);
            } else {
                System.out.print(myCards[i]+ " ");
            }
        }

    }
}
