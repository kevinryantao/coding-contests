package hackerrank.johnssubwaycommute;

/**
 * Created by ktao on 10/18/16.
 */
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        char[] seats = scanner.nextLine().toCharArray();

        System.out.println(findSeat(seats));
    }

    private static int findSeat(char[] seats) {
        if(seats[seats.length - 1] == 'E'){
            return seats.length - 1;
        }
        if(seats[0] == 'E'){
            return 0;
        }

        List<Integer> openSeats = new ArrayList<Integer>();
        for(int i = 0; i < seats.length; i ++){
            if(seats[i] == 'E'){
                openSeats.add(i);
            }
        }

        int longestChainStart = openSeats.get(1);
        int longestChainLength = 1;

        int lastOpenSeat = openSeats.get(1);

        int currentChainLength = 1;
        int currentChainStart = openSeats.get(1);

        int firstChainOver2Start = -1;


        for(int i : openSeats){
            if(i == lastOpenSeat + 1){
                currentChainLength++;
                lastOpenSeat = i;
            } else {
                if(currentChainLength > longestChainLength) {
                    longestChainLength = currentChainLength;
                    longestChainStart = currentChainStart;
                    if(firstChainOver2Start == -1 && longestChainLength >=2){
                        firstChainOver2Start = longestChainStart;
                    }
                }
                currentChainStart = i;
                currentChainLength = 1;
                lastOpenSeat = i;
            }
        }
        if(currentChainLength > longestChainLength) {
            longestChainLength = currentChainLength;
            longestChainStart = currentChainStart;
        }
        if(longestChainLength == 1){
            return openSeats.get(openSeats.size() - 1);
        }
        if(longestChainLength == 2){
            return longestChainStart + 1;
        }
        if(firstChainOver2Start != -1){
            return firstChainOver2Start + 1;
        }
        return longestChainStart + 1;
    }
}