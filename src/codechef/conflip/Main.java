package codechef.conflip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //Carvans

    public static void main(String[] args) throws IOException {
        BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int myNumberOfTestCases = Integer.parseInt(myBufferedReader.readLine());

        for (int i = 0; i < myNumberOfTestCases; i++) {
            int myNumberOfCars = Integer.parseInt(myBufferedReader.readLine());
            int[] myIntCarSpeeds = getCarSpeeds(myBufferedReader);
            int myNumMaxSpeedCars = getNumberOfMaxSpeedCars(myIntCarSpeeds);
            System.out.println(myNumMaxSpeedCars);
        }
    }

    private static int getNumberOfMaxSpeedCars(int[] myIntCarSpeeds) {
        int myNumberOfMaxSpeedCars = 0;
        int myCurrentLowestSpeed = Integer.MAX_VALUE;
        for (int myMaxSpeed : myIntCarSpeeds) {
            if (myMaxSpeed <= myCurrentLowestSpeed) {
                myCurrentLowestSpeed = myMaxSpeed;
                myNumberOfMaxSpeedCars++;
            }
        }
        return myNumberOfMaxSpeedCars;
    }

    private static int[] getCarSpeeds(BufferedReader myBufferedReader) throws IOException {
        String[] myCarSpeeds = myBufferedReader.readLine().split(" ");
        int[] myIntCarSpeeds = new int[myCarSpeeds.length];
        for (int j = 0; j < myCarSpeeds.length; j++) {
            myIntCarSpeeds[j] = Integer.parseInt(myCarSpeeds[j]);
        }
        return myIntCarSpeeds;
    }
}
