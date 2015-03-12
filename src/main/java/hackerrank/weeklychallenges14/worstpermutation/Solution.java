package hackerrank.weeklychallenges14.worstpermutation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Solution to https://www.hackerrank.com/contests/w14/challenges/worst-permutation
 */
public class Solution {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int[] firstLine = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int size = firstLine[0];
        int numberOfSwaps = firstLine[1];

        int[] original = parseIntsFromStringArray(scanner.nextLine().split(" "));

        Solution solution = new Solution();
        System.out.println(format(solution.solve(size, numberOfSwaps, original)));
    }

    private static String format(int[] solution) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i : solution){
            stringBuilder.append(i);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private int[] solve(int size, int numberOfSwaps, int[] original) {
        if(numberOfSwaps >= size){
            int[] solution = new int[size];
            for(int i = 0; i < size; i++){
                solution[i] = size - i;
            }
            return solution;
        }
        Map<Integer, Integer> numberToPosition = new HashMap<Integer, Integer>();
        for(int i = 0; i < size; i++){
            numberToPosition.put(original[i], i);
        }

        int swapsSoFar = 0;

        for(int i = 0; i < size; i++){
            int numberThatShouldBeThere = size - i;
            if (original[i] != numberThatShouldBeThere) {
                int position = numberToPosition.get(numberThatShouldBeThere);
                // do a swap
                original[position] = original[i];
                numberToPosition.put(original[i], position);

                original[i] = numberThatShouldBeThere;
                numberToPosition.put(numberThatShouldBeThere, i);
                swapsSoFar++;
            }
            if(swapsSoFar >= numberOfSwaps){
                break;
            }
        }
        return original;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}
