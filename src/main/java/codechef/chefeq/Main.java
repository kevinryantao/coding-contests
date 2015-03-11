package codechef.chefeq;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ktao on 2/10/15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numTestCases; i++){
            int numPiles = Integer.parseInt(scanner.nextLine());
            int[] coinsPerPile = parseIntsFromStringArray(scanner.nextLine().split(" "));
            System.out.println(process(coinsPerPile));

        }

    }

    private static int process(int[] coinsPerPile) {
        // solution is to find the mode, and find how many times it appears. Then subtract the number of piles by the
        // number of times the mode appears
        Map<Integer, Integer> frequencyCount = new HashMap<Integer, Integer>();
        for(int coins : coinsPerPile){
            Integer frequency = frequencyCount.get(coins);
            if(frequency == null){
                frequency = 0;
            }
            frequency++;
            frequencyCount.put(coins, frequency);
        }

        int maxTimesSameNumberAppears = 0;
        for(Map.Entry<Integer,Integer> entry : frequencyCount.entrySet()){
            int frequency = entry.getValue();
            if(frequency > maxTimesSameNumberAppears){
                maxTimesSameNumberAppears = frequency;
            }
        }

        return coinsPerPile.length - maxTimesSameNumberAppears;
    }


    private static int[] parseIntsFromStringArray(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}
