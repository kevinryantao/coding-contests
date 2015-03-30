package codeforces.round297;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * http://codeforces.com/contest/525/problem/C
 */
public class IlyaAndSticks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSticks = Integer.parseInt(scanner.nextLine());
        int[] sticks = parseIntsFromStringArray(scanner.nextLine().split(" "));

        Arrays.sort(sticks);

        List<Integer> pairs = new ArrayList<Integer>();
        Integer leftover = null;
        for(int i = sticks.length - 1; i >= 0; i--){
            if(leftover == null || leftover - sticks[i] > 1){
                leftover = sticks[i];
            } else if(leftover - sticks[i] <= 1) {
                pairs.add(sticks[i]);
                leftover = null;
            }
        }

        long totalArea = 0;
        for(int i = 0; i < pairs.size() / 2; i++){
            long side1 = pairs.get(2 * i);
            long side2 = pairs.get(2 * i + 1);
            totalArea += side1 * side2;
        }

        System.out.println(totalArea);

    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }


}
