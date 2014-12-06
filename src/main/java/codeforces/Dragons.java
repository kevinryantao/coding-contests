package codeforces;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: ktao
 * Copyright 2012
 */
public class Dragons {

    public static void main(String[] args){
        int strength, n;
        Scanner myScanner = new Scanner(System.in);
        String[] myFirstLine = myScanner.nextLine().split(" ");
        strength = Integer.valueOf(myFirstLine[0]);
        n = Integer.valueOf(myFirstLine[1]);
        Dragon[] myDragons = new Dragon[n];
        for(int i = 0; i < n; i++){
            String[] myLine = myScanner.nextLine().split(" ");
            myDragons[i] = new Dragon(Integer.valueOf(myLine[0]), Integer.valueOf(myLine[1]));
        }
        Arrays.sort(myDragons);
        for(int i = 0; i < n; i++){
            if(myDragons[i].aDragonStrength < strength){
                strength += myDragons[i].aRewardStrength;
            }
            else{
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static class Dragon implements Comparable<Dragon>{
        private final int aDragonStrength;
        private final int aRewardStrength;

        public Dragon(int aDragonStrength, int aRewardStrength){
            this.aDragonStrength = aDragonStrength;
            this.aRewardStrength = aRewardStrength;
        }

        @Override
        public int compareTo(Dragon o) {
            if (aDragonStrength > o.aDragonStrength){
                return 1;
            } else if(aDragonStrength == o.aDragonStrength){
                return 0;
            }
            return -1;
        }
    }

}