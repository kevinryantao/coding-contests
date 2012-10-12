package codeforces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerfectPermutation {

    public static void main(String[] args){

        int n;
        Scanner myScanner = new Scanner(System.in);
        n = myScanner.nextInt();

        if(n % 2 == 1){
            System.out.println("-1");
        }
        else{
            List<Integer> myList = new ArrayList<Integer>();
            for(int i = 1; i <= n; i++){
                if(i % 2 == 0){
                    myList.add(i - 1);
                } else {
                    myList.add(i + 1);
                }
            }
            String myString = "";
            for(int i = 0; i < myList.size(); i++){
                myString += myList.get(i) + " ";
            }
            myString = myString.trim();
            System.out.println(myString);
        }
    }
}
