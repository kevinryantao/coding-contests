package hackerrank.weeklychallenges14.numberlist;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/contests/w14/challenges/number-list
 */

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = Integer.parseInt(scanner.nextLine());

        Solution solution = new Solution();

        for (int i = 0 ; i < numTestCases; i++) {
            int[] nAndK = parseIntsFromStringArray(scanner.nextLine().split(" "));
            int[] array = parseIntsFromStringArray(scanner.nextLine().split(" "));
            int n = nAndK[0];
            int k = nAndK[1];

            System.out.println(solution.solve(n, k, array));
        }
    }

    private long solve(int n, int k, int[] array) {

        long sum = 0;
        int indexOfLastValueGreaterThanK = -1;


        for(int i = 0; i < n; i++){
            if(array[i] > k){
                sum += (i + 1);
                indexOfLastValueGreaterThanK = i;
            } else {
                sum += indexOfLastValueGreaterThanK + 1;
            }
        }

        return sum;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}