package codeforces;

import java.util.Scanner;

public class Cycles {

    public static void main(String[] args) {
        int n;
        Scanner myScanner = new Scanner(System.in);
        n = myScanner.nextInt();
        int gridSize = getGridSize(n);

        boolean[][] myGrid = new boolean[gridSize][gridSize];

    }

    public static int getGridSize(int n) {
        for (int gridSize = 3; gridSize < 100; gridSize++) {
            if (getNChoose3(gridSize) >= n) {
                return gridSize;
            }
        }
        return 3;
    }

    public static int getNChoose3(int n) {
        return n * (n - 1) * (n - 2) / 6;
    }
}
