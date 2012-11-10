package rosalind;

import java.util.Scanner;

public class RNATranscription {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String myLine = myScanner.nextLine();
        char[] myCharArray = myLine.toCharArray();
        for (int i = 0; i < myCharArray.length; i++) {
            if(myCharArray[i] == 'T'){
                myCharArray[i] = 'U';
            }
        }
        System.out.println(myCharArray);
    }
}
