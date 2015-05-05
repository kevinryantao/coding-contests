package codeforces.round301;

import java.util.Scanner;

/**
 * Created by ktao on 4/30/15.
 *
 * http://codeforces.com/contest/540/problem/A
 */
public class CombinationLock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] starting = new int[n];

        String startingString = scanner.nextLine();

        int[] end = new int[n];

        String endString = scanner.nextLine();

        for(int i = 0; i < n; i++){
            starting[i] = Integer.parseInt(startingString.charAt(i)+"");
            end[i] = Integer.parseInt(endString.charAt(i)+"");
        }

        int movesSoFar = 0;

        for(int i = 0; i < n; i++){
            int abs = Math.abs(starting[i] - end[i]);
            int moves = Math.min(abs, 10 - abs);
            movesSoFar += moves;
        }
        System.out.println(movesSoFar);

    }
}
