package hackerrank.appendanddelete;

/**
 * Created by ktao on 11/6/16.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int k = in.nextInt();

        int sLength = s.length();
        int tLength = t.length();

        System.out.println(process(sLength, tLength, k, s, t));

    }

    private static String process(int sLength, int tLength, int k, String s, String t) {
        if(sLength + tLength <= k){
            return "Yes"; // whole string can be deleted so contents don't matter
        }
        if(Math.abs(sLength - tLength) > k){
            return "No"; // can't reach desired length diff so contents also don't matter
        }

        if(k % 2 == 0){ // if k is even
            if(Math.abs(sLength - tLength) % 2 == 0){ // if diff is even, so it's possible.
                //Now we gotta compare the first x letters.
                int firstXLetters = firstXLetters(sLength, tLength, k);
                if(s.substring(0, firstXLetters).equals(t.substring(0, firstXLetters))){
                    return "Yes";
                }
            }
            return "No";
        }

        if(k % 2 == 1){ // if k is odd
            if(Math.abs(sLength - tLength) % 2 == 1){ // if diff is odd
                int firstXLetters = firstXLetters(sLength, tLength, k);
                if(s.substring(0, firstXLetters).equals(t.substring(0, firstXLetters))){
                    return "Yes";
                }
            }
            return "No";
        }

        return "No";
    }

    private static int firstXLetters(int sLength, int tLength, int k){
        int diff = Math.abs(sLength - tLength);
        int lettersOff = (k - diff) / 2;

        return Math.min(sLength, tLength) - lettersOff;
    }
}
