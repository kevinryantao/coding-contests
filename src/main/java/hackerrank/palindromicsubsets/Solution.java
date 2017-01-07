package hackerrank.palindromicsubsets;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    private static long[][] cache = new long[27][100001];

    private static void precache() {
        for(int numSingleChars = 1; numSingleChars <= 26; numSingleChars++){
            long answer = numSingleChars;
            cache[numSingleChars][0] = answer;
            for(int numCharRepeats = 0; numCharRepeats <= 99999; numCharRepeats++){
                    answer = (answer * 2 + 1) % 1000000007;
                    cache[numSingleChars][numCharRepeats+1] = answer;
            }
        }
    }

    public static void main(String[] args) {

        precache();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        char[] s = in.next().toCharArray();
        for(int a0 = 0; a0 < q; a0++){
            // your code goes here
            int queryType = in.nextInt();
            if(queryType == 1){
                int startingIndex = in.nextInt();
                int endingIndex = in.nextInt();
                int shiftAmount = in.nextInt();
                s = shift(s, startingIndex, endingIndex, shiftAmount);
            }else{
                int startingIndex = in.nextInt();
                int endingIndex = in.nextInt();
                System.out.println(numPalindromicSubsets(s, startingIndex, endingIndex));
            }
        }
    }

    private static char[] shift(char[] startingArray, int startIndex, int endIndex, int shiftAmount){
        shiftAmount = shiftAmount % 26;
        char[] resultArray = startingArray;
        for(int i = startIndex; i <= endIndex; i++){
            int newCharInt = (resultArray[i] - 'a' + shiftAmount) % 26;
            resultArray[i] = (char) (newCharInt + 'a');
        }
        return resultArray;
    }

    private static long numPalindromicSubsets(char[] startingArray, int startIndex, int endIndex){

        int[] characterCounts = new int[26];
        for(int i = startIndex; i <= endIndex; i++){
            characterCounts[startingArray[i] - 'a'] += 1;
        }

        int numSingleChars = 0;
        int numCharRepeats = 0;

        for(int i = 0; i < 26; i ++){
            if(characterCounts[i] > 0){
                numSingleChars++;
                numCharRepeats += characterCounts[i] - 1;
            }
        }

        return getAnswerModBillion(numSingleChars, numCharRepeats);

        // x2 + 1 for each additional char repeat
        // + 1 for each additional singl char

    }

    private static long getAnswerModBillion(int numSingleChars, int numCharRepeats) {

        return cache[numSingleChars][numCharRepeats];
    }

//
//    private static long getAnswerModBillion(int numSingleChars, int numCharRepeats) {
//        if(cache[numSingleChars][numCharRepeats] != 0){
//            return cache[numSingleChars][numCharRepeats];
//        }
//        long answer = numSingleChars;
//        for(int i = 0; i < numCharRepeats; i++){
//            answer = (answer * 2 + 1) % 1000000007;
//            cache[numSingleChars][i+1] = answer;
//        }
//        cache[numSingleChars][numCharRepeats] = answer;
//        return answer;
//    }
}
