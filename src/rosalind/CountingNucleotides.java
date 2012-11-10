package rosalind;

import java.util.Scanner;

public class CountingNucleotides {

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);

        String myLine = myScanner.nextLine();

        int[] myCounts = new int[4];

        for (char c : myLine.toCharArray()) {
            switch (c) {
                case 'A':
                    myCounts[0]++;
                    break;
                case 'C':
                    myCounts[1]++;
                    break;
                case 'G':
                    myCounts[2]++;
                    break;
                case 'T':
                    myCounts[3]++;
                    break;
            }
        }

        System.out.println(myCounts[0] + " " + myCounts[1] + " " + myCounts[2] + " " + myCounts[3]);

    }

}
