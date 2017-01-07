package hackerrank.pickingnumbers;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            array[a_i] = in.nextInt();
        }

        Arrays.sort(array);
        int longestSoFar = 1;

        int currentValue = -2;
        int numCurrentValue = 0;
        int currentChain = 0;

        for(int i = 0; i < n; i++){
            if(array[i] == currentValue){
                currentChain++;
                numCurrentValue++;
            } else if(array[i] == currentValue + 1){
                currentChain = numCurrentValue + 1;
                numCurrentValue = 1;
                currentValue = array[i];
            } else {
                currentValue = array[i];
                currentChain = 1;
                numCurrentValue = 1;
            }
            if(currentChain > longestSoFar){
                longestSoFar = currentChain;
            }
        }

        System.out.println(longestSoFar);
    }
}
