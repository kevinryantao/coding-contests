package hackerrank.tarasbeautifulpermutations;

import java.io.*;
import java.util.*;

public class Solution {

    private static long BILLION_PLUS_7 = 1000000000L + 7L;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numQueries = scanner.nextInt();

        for (int q = 0; q < numQueries; q++) {

            int arraySize = scanner.nextInt();

            int[] array = new int[arraySize];

            for (int i = 0; i < arraySize; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println(solve(array));
        }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    private static long solve(int[] array) {
        int numDuplicates = 0;
        Map<Integer, Integer> histogram = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            Integer count = histogram.get(array[i]);
            if (count == null) {
                count = 1;
            } else {
                count = 2;
                numDuplicates++;
            }
            histogram.put(array[i], count);
        }
        int numUniques = histogram.keySet().size();
        int total = array.length;

        long allPermutations = getPermutations(numDuplicates, total);

        long uglyPermutations = getPermutations(numDuplicates - 1, total - 1);

        for(int i = 0; i < numDuplicates; i ++){
            allPermutations -= uglyPermutations;
            allPermutations = allPermutations % BILLION_PLUS_7;
        }

        if(allPermutations < 0){
            allPermutations += BILLION_PLUS_7;
        }

        return allPermutations;
    }

    private static long getPermutations(int numDuplicates, int total) {
        int[] factors = new int[total];

        for(int i = 1; i <= factors.length; i++){
            if (i % 2 == 0 && (i / 2 <= numDuplicates * 2)){
                factors[i - 1] = i / 2;
            } else {
                factors[i - 1] = i;
            }
        }

        return productModBillion(factors);
    }

    private static long productModBillion(int[] factors){
        long productSoFar = 1;
        for(int i = 0; i < factors.length; i++){
            productSoFar *= factors[i];
            productSoFar = productSoFar % BILLION_PLUS_7;
        }
        return productSoFar;
    }


}