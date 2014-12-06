package codeforces;

import java.util.Scanner;

public class Team {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int n = Integer.valueOf(myScanner.nextLine());
        int myTotalImplemented = 0;
        for(int i = 0; i < n; i++){
            String[] myLine = myScanner.nextLine().split(" ");
            int myTotalSure = 0;
            for(String myString : myLine){
                if(myString.equals("1")){
                    myTotalSure++;
                }
            }
            if (myTotalSure >= 2){
                myTotalImplemented++;
            }
        }
        System.out.println(myTotalImplemented);
    }
}
