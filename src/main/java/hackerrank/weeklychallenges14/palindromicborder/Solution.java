package hackerrank.weeklychallenges14.palindromicborder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ktao on 3/13/15.
 */
public class Solution {

    public static final int MODULO = 1000000007;

    public static void main(String[] args) {

        //Scanner scanner = new Scanner(System.in);

        //String input = scanner.nextLine();

        Solution solution = new Solution();

        String input = create10kAs();

        System.out.println(solution.countPalindromicBorders(input));
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    private static String create10kAs() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < 1000; i++){
            stringBuilder.append("ab");
        }
        return stringBuilder.toString();
    }

    private long countPalindromicBorders(String input) {
        Map<String, Integer> palindromeCount = new HashMap<String, Integer>();
        List<Double> currentActivePalindromeCenters = new ArrayList<Double>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            incrementCount(palindromeCount, c);


            List<Double> newActivePalindromeCenters = new ArrayList<Double>();
            for (double palindromeCenter : currentActivePalindromeCenters) {
                int palindromeStartIndex = (int) (2 * palindromeCenter - i);
                if(palindromeStartIndex >= 0){
                    if (c == input.charAt(palindromeStartIndex)) {
                        newActivePalindromeCenters.add(palindromeCenter);
                        incrementCount(palindromeCount, input.substring(palindromeStartIndex, i + 1));
                    }
                }
            }

            if (i > 0) {
                if (c == input.charAt(i - 1)) {
                    newActivePalindromeCenters.add(i - 0.5);
                    incrementCount(palindromeCount, input.substring(i - 1, i + 1));
                }
            }
            if (i > 1) {
                if (c == input.charAt(i - 2)) {
                    newActivePalindromeCenters.add(i - 1d);
                    incrementCount(palindromeCount, input.substring(i - 2, i + 1));
                }
            }

            currentActivePalindromeCenters = newActivePalindromeCenters;
        }



        long sum = 0L;

        for(Map.Entry<String, Integer> entry : palindromeCount.entrySet()){
            long value = entry.getValue();
            if(value > 1){
                long toAdd = value * (value - 1) / 2;
                sum = (sum + toAdd) % MODULO;
            }
        }

        return sum;
    }

    private void incrementCount(Map<String, Integer> palindromeCount, char c) {
        String toIncrement = String.valueOf(c);
        incrementCount(palindromeCount, toIncrement);
    }

    private void incrementCount(Map<String, Integer> palindromeCount, String toIncrement) {
        if (palindromeCount.containsKey(toIncrement)) {
            palindromeCount.put(toIncrement, palindromeCount.get(toIncrement) + 1);
        } else {
            palindromeCount.put(toIncrement, 1);
        }
    }
}
