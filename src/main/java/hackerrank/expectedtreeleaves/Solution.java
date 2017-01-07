package hackerrank.expectedtreeleaves;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long averageLeavesX2 = n;
        long answer = findFactorialModBillionPlus7Skipping2(averageLeavesX2, n);
        System.out.println(answer);
    }

    private static long findFactorialModBillionPlus7Skipping2(long averageLeavesX2, int factorial) {
        long answerSoFar = averageLeavesX2;
        for(int i = 3; i <= factorial; i++){
            answerSoFar = (answerSoFar * i) % 1000000007;
        }
        return answerSoFar;
    }
}
