package codechef.rrecipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int LETTERS = 26;
    public static final int MODULO = 10000009;

    public static void main(String[] args) throws IOException {
        BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int myNumberOfTestCases = Integer.parseInt(myBufferedReader.readLine());
        for (int i = 0; i < myNumberOfTestCases; i++) {
            String myRecipe = myBufferedReader.readLine();
            long myNumberOfPossibilities = countPalindrome(myRecipe);
            System.out.println(myNumberOfPossibilities);
        }
    }

    private static long countPalindrome(String myRecipe) {
        char[] myCharRecipe = myRecipe.toCharArray();
        int myNumberOfPairedQuestionMarks = 0;
        for (int i = 0; i < (myCharRecipe.length + 1) / 2; i++) {
            char myFirstChar = myCharRecipe[i];
            char myPairedChar = myCharRecipe[myCharRecipe.length - 1 - i];
            if (myFirstChar == '?' && myPairedChar == '?') {
                myNumberOfPairedQuestionMarks++;
            } else if (myFirstChar == myPairedChar || myFirstChar == '?' || myPairedChar == '?') {
                //pass
            } else {
                return 0;
            }
        }
        return power(LETTERS,
                     myNumberOfPairedQuestionMarks,
                     MODULO);
    }

    public static long power(long aBase, int aExponent, int aModulo) {
        long result = 1;
        for (int i = 0; i < aExponent; i++) {
            result = (result * aBase) % aModulo;
        }
        return result;
    }
}
