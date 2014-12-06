package codechef.conflip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //Conflip

    public static void main(String[] args) throws IOException {
        BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int myNumberOfTestCases = Integer.parseInt(myBufferedReader.readLine());

        for (int i = 0; i < myNumberOfTestCases; i++) {
            int myNumberOfGames = Integer.parseInt(myBufferedReader.readLine());
            for(int j = 0; j < myNumberOfGames; j++){
                int[] myCoinParams = getCoinParams(myBufferedReader);
                int myAnswer = countCoins(myCoinParams);
                System.out.println(myAnswer);
            }
        }
    }

    private static int countCoins(int[] myCoinParams) {
        int n = myCoinParams[1];
        int i = myCoinParams[0];
        int q = myCoinParams[2];
        if(n % 2 == 0){
            return n / 2;
        }
        if(i == q){
            return n / 2;
        }
        return n / 2 + 1;
    }

    private static int[] getCoinParams(BufferedReader myBufferedReader) throws IOException {
        String[] myCarSpeeds = myBufferedReader.readLine().split(" ");
        int[] myIntCarSpeeds = new int[myCarSpeeds.length];
        for (int j = 0; j < myCarSpeeds.length; j++) {
            myIntCarSpeeds[j] = Integer.parseInt(myCarSpeeds[j]);
        }
        return myIntCarSpeeds;
    }
}
