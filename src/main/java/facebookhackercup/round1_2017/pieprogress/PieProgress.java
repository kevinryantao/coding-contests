package facebookhackercup.round1_2017.pieprogress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PieProgress {

    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("pie_progress.txt"));
        int numberOfCases = Integer.parseInt(scanner.nextLine());
        PrintWriter writer = new PrintWriter("pie_progress_out.txt");

        for(int i = 0; i < numberOfCases; i++){
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int numDays = Integer.parseInt(split[0]);
            int numPies = Integer.parseInt(split[1]);
            int[][] piePricesPerDay = new int[numDays][numPies];

            for(int j = 0; j < numDays; j++){
                int[] prices = parseIntsFromStringArray(scanner.nextLine().split(" "));
                Arrays.sort(prices);
                piePricesPerDay[j] = prices;
            }

            System.out.println("Case #" + (i + 1) + ": " + getMinPrice(piePricesPerDay));
            writer.println("Case #" + (i + 1) + ": " + getMinPrice(piePricesPerDay));
        }
        writer.close();
    }

    private static int getMinPrice(int[][] piePricesPerDay) {
        int runningSum = 0;
        int[] piesBoughtPerDay = new int[piePricesPerDay.length];
        for(int day = 0; day < piePricesPerDay.length; day++){
            int[] candidates = new int[day + 1];
            for(int d = day; d >= 0; d--){
                if(piesBoughtPerDay[d] < piePricesPerDay[day].length){
                    int tax = piesBoughtPerDay[d] * 2 + 1;
                    candidates[d] = (piePricesPerDay[d][piesBoughtPerDay[d]] + tax);
                } else {
                    candidates[d] = 0;
                }
            }

            int lowestPriceDay = -1;
            int lowestPrice = Integer.MAX_VALUE;

            for(int i = 0; i < candidates.length; i++){
                if(candidates[i] > 0){
                    if(candidates[i] < lowestPrice){
                        lowestPrice = candidates[i];
                        lowestPriceDay = i;
                    }
                }
            }
            runningSum += lowestPrice;
            piesBoughtPerDay[lowestPriceDay]++;
        }

        return runningSum;
    }


    private static int[] parseIntsFromStringArray(String[] stringArray) {
        int[] result = new int[stringArray.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(stringArray[i]);
        }
        return result;
    }
}
