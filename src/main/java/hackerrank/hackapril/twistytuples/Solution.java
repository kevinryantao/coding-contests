package hackerrank.hackapril.twistytuples;


/**
 * https://www.hackerrank.com/contests/101hack24/challenges/twisty-tuple
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    SortedMap<Integer, Integer> partialTupleCounts = new TreeMap<Integer, Integer>();

    SortedMap<Integer, Integer> elementCounts = new TreeMap<Integer, Integer>();

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int arraySize = Integer.parseInt(scanner.nextLine());

        int[] arrayElements = parseIntsFromStringArray(scanner.nextLine().split(" "));

        Solution solution = new Solution();

        System.out.println(solution.countTuples(arrayElements));
    }

    public Solution(){

    }

    private long countTuples(int[] arrayElements) {
        long tuplesSoFar = 0;

        for(int i : arrayElements){
            // step 1 is increment tuples so far based on partial tuple counts
            long additionalTuples = countAdditionalTuples(i);
            tuplesSoFar += additionalTuples;

            // step 2 is to increment partial tuple counts based on element counts
            addPartialTuples(i);

            // step 3 is to increment element counts
            addElementCounts(i);
        }
        return tuplesSoFar;
    }

    private void addElementCounts(int element) {
        addToMap(elementCounts, element, 1);
    }

    private void addPartialTuples(int element) {
        for(int firstElement : elementCounts.headMap(element).keySet()){
            addToMap(partialTupleCounts, firstElement, elementCounts.get(firstElement));
        }
    }

    private void addToMap(Map<Integer, Integer> map, int key, int amount){
        int startingValue = 0;
        if(map.containsKey(key)){
            startingValue = map.get(key);
        }
        int finalValue = amount + startingValue;
        map.put(key, finalValue);
    }

    private long countAdditionalTuples(int element) {
        long tupleCount = 0;
        // how many partial tuples are centered lower than element
        for(int partialTupleCenter : partialTupleCounts.tailMap(element).keySet()){
            if(partialTupleCenter > element){
                tupleCount += partialTupleCounts.get(partialTupleCenter);
            }
        }
        return tupleCount;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

}